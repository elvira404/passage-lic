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
package ru.arsysop.passage.lic.internal.oshi;

import org.osgi.service.component.annotations.Component;

import ru.arsysop.passage.lic.base.BaseConditionEvaluator;
import ru.arsysop.passage.lic.base.LicensingProperties;
import ru.arsysop.passage.lic.oshi.OshiHal;
import ru.arsysop.passage.lic.runtime.ConditionEvaluator;

@Component(property = {
		LicensingProperties.LICENSING_CONDITION_TYPE + '=' + OshiHal.CONDITION_TYPE_HARDWARE })
public class OshiConditionEvaluator extends BaseConditionEvaluator implements ConditionEvaluator {

	@Override
	protected boolean evaluateSegment(String key, String value) {
		return OshiHal.evaluateProperty(key, value);
	}

}
