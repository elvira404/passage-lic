/*******************************************************************************
 * Copyright (c) 2018 ArSysOp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package ru.arsysop.passage.lic.transport;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ru.arsysop.passage.lic.runtime.ConditionDescriptor;

public class RequestProducer {

	private static final String REQUEST_ACTION_CONDITIONS_EXTRACT = "extractConditions"; // NLS-$1
	private static final String REQUEST_ACTION_CONDITIONS_EVALUATE = "evaluateConditions"; // NLS-$1
	private static final String CHARSET_UTF_8 = "UTF-8"; // NLS-$1
	private static final String APPLICATION_JSON = "application/json"; // NLS-$1

	public static final String PARAMETER_CONFIGURATION = "configuration";

	private final Logger logger = Logger.getLogger(RequestProducer.class.getName());

	public Map<String, String> initRequestParams(String host, String port, String modeId) {
		Map<String, String> requestAttributes = new HashMap<>();
		requestAttributes.put(RequestParameters.HOST, host);
		requestAttributes.put(RequestParameters.PORT, port);

		requestAttributes.put(RequestParameters.CLIENT_TRUSTED_ID, "12345678");
		requestAttributes.put(RequestParameters.SERVER_ACCESS_MODE_ID, modeId);
		return requestAttributes;
	}

	public Iterable<FloatingConditionDescriptor> extractConditionsRequest(CloseableHttpClient httpClient, HttpHost host,
			Map<String, String> requestAttributes) {
		Iterable<FloatingConditionDescriptor> descriptors = new ArrayList<>();

		try {
			requestAttributes.put(RequestParameters.SERVER_ACTION_ID, REQUEST_ACTION_CONDITIONS_EXTRACT);
			URIBuilder builder = createRequestUriBuilder(requestAttributes);
			TransferObjectDescriptor transferObject = processingExtractConditions(httpClient, host, builder);
			descriptors = transferObject.getDescriptors();
		} catch (Exception e) {
			Logger.getLogger(RequestProducer.class.getName()).info(e.getMessage());
		}
		return descriptors;
	}

	public Iterable<FloatingFeaturePermission> evaluateConditionsRequest(CloseableHttpClient httpClient, HttpHost host,
			Map<String, String> requestAttributes, Iterable<ConditionDescriptor> conditions) {
		Iterable<FloatingFeaturePermission> permissions = new ArrayList<>();
		try {
			requestAttributes.put(RequestParameters.SERVER_ACTION_ID, REQUEST_ACTION_CONDITIONS_EVALUATE);
			URIBuilder builder = createRequestUriBuilder(requestAttributes);
			TransferObjectDescriptor transferObject = processingEvaluateConditions(httpClient, host, builder,
					conditions);
			permissions = transferObject.getPermissions();
		} catch (Exception e) {
			Logger.getLogger(RequestProducer.class.getName()).info(e.getMessage());
		}
		return permissions;
	}

	private TransferObjectDescriptor processingExtractConditions(CloseableHttpClient httpClient, HttpHost host,
			URIBuilder builder) throws URISyntaxException, IOException, ClientProtocolException {

		HttpPost httpPost = new HttpPost(builder.build());
		ResponseHandler<TransferObjectDescriptor> responseHandler = new ResponseHandler<TransferObjectDescriptor>() {

			@Override
			public TransferObjectDescriptor handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				TransferObjectDescriptor transferObject = null;
				HttpEntity entity = response.getEntity();
				ObjectMapper mapper = new ObjectMapper();
				try (InputStream inputContext = entity.getContent()) {
					transferObject = mapper.readValue(inputContext, TransferObjectDescriptor.class);
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
				return transferObject;
			}
		};
		return httpClient.execute(host, httpPost, responseHandler);
	}

	private TransferObjectDescriptor processingEvaluateConditions(CloseableHttpClient httpClient, HttpHost host,
			URIBuilder builder, Iterable<ConditionDescriptor> conditions)
			throws URISyntaxException, ClientProtocolException, IOException {

		HttpPost httpPost = new HttpPost(builder.build());
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		TransferObjectDescriptor transferObject = new TransferObjectDescriptor();
		for (ConditionDescriptor d : conditions) {
			if (d instanceof FloatingConditionDescriptor) {
				transferObject.addDescriptor((FloatingConditionDescriptor) d);
			}
		}

		String objectAsString = mapper.writeValueAsString(transferObject);
		StringEntity entity = new StringEntity(objectAsString, CHARSET_UTF_8);
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-type", APPLICATION_JSON);

		ResponseHandler<TransferObjectDescriptor> responseHandler = new ResponseHandler<TransferObjectDescriptor>() {

			@Override
			public TransferObjectDescriptor handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				TransferObjectDescriptor transferObject = null;
				HttpEntity entity = response.getEntity();
				ObjectMapper mapper = new ObjectMapper();
				try (InputStream inputContext = entity.getContent()) {
					transferObject = mapper.readValue(inputContext, TransferObjectDescriptor.class);

				} catch (Exception e) {
					logger.info(e.getMessage());
				}
				return transferObject;
			}
		};
		return httpClient.execute(host, httpPost, responseHandler);
	}

	private URIBuilder createRequestUriBuilder(Map<String, String> attributes) {
		String host = "";
		String port = "";
		Object hostAttr = attributes.get(RequestParameters.HOST);
		Object portAttr = attributes.get(RequestParameters.PORT);
		if (hostAttr instanceof String) {
			host = (String) hostAttr;
		} else {
			logger.info("Host value undefined.");
			return null;
		}

		if (portAttr instanceof String) {
			port = (String) portAttr;
		} else {
			logger.info("Port value undefined.");
			return null;
		}

		String requestHead = String.format("%s://%s:%s", RequestParameters.PROTOCOL_TYPE_ID, host, port);
		URIBuilder builder;
		try {
			builder = new URIBuilder(requestHead);
			for (Entry<String, String> entry : attributes.entrySet()) {
				if (entry.getKey().equals(RequestParameters.HOST)
						|| entry.getKey().equals(RequestParameters.PORT)) {
					continue;
				}
				builder.setParameter(entry.getKey(), entry.getValue());
			}
			return builder;

		} catch (URISyntaxException e) {
			logger.info(e.getMessage());
		}
		return null;
	}

}
