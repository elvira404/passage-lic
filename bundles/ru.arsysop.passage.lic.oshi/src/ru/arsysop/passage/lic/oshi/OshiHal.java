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
package ru.arsysop.passage.lic.oshi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystemVersion;
import ru.arsysop.passage.lic.inspector.HardwareInspector;

public class OshiHal {
	
	public static final String CONDITION_TYPE_HARDWARE = "hardware"; //$NON-NLS-1$
	
	private static SystemInfo staticSI = new SystemInfo();

	private static Set<String> knownProperties = new LinkedHashSet<>();
	
	static {
		knownProperties.add(HardwareInspector.PROPERTY_OS_MANUFACTURER);
		knownProperties.add(HardwareInspector.PROPERTY_OS_FAMILY);
		knownProperties.add(HardwareInspector.PROPERTY_OS_VERSION);
		knownProperties.add(HardwareInspector.PROPERTY_OS_BUILDNUMBER);

		knownProperties.add(HardwareInspector.PROPERTY_SYSTEM_MANUFACTURER);
		knownProperties.add(HardwareInspector.PROPERTY_SYSTEM_MODEL);
		knownProperties.add(HardwareInspector.PROPERTY_SYSTEM_SERIALNUMBER);

		knownProperties.add(HardwareInspector.PROPERTY_BASEBOARD_MANUFACTURER);
		knownProperties.add(HardwareInspector.PROPERTY_BASEBOARD_MODEL);
		knownProperties.add(HardwareInspector.PROPERTY_BASEBOARD_VERSION);
		knownProperties.add(HardwareInspector.PROPERTY_BASEBOARD_SERIALNUMBER);

		knownProperties.add(HardwareInspector.PROPERTY_FIRMWARE_MANUFACTURER);
		knownProperties.add(HardwareInspector.PROPERTY_FIRMWARE_VERSION);
		knownProperties.add(HardwareInspector.PROPERTY_FIRMWARE_RELEASEDATE);
		knownProperties.add(HardwareInspector.PROPERTY_FIRMWARE_NAME);
		knownProperties.add(HardwareInspector.PROPERTY_FIRMWARE_DESCRIPTION);

		knownProperties.add(HardwareInspector.PROPERTY_CPU_VENDOR);
		knownProperties.add(HardwareInspector.PROPERTY_CPU_FAMILY);
		knownProperties.add(HardwareInspector.PROPERTY_CPU_MODEL);
		knownProperties.add(HardwareInspector.PROPERTY_CPU_NAME);
		knownProperties.add(HardwareInspector.PROPERTY_CPU_IDENTIFIER);
		knownProperties.add(HardwareInspector.PROPERTY_CPU_PROCESSORID);
	}
	
	private OshiHal() {
		//block
	}

	public static void dumpOperatingSystem(OutputStream output) throws IOException {
		OperatingSystem os = staticSI.getOperatingSystem();
		dumpProperty(output, HardwareInspector.PROPERTY_OS_MANUFACTURER, os.getManufacturer());
		dumpProperty(output, HardwareInspector.PROPERTY_OS_FAMILY, os.getFamily());
		OperatingSystemVersion version = os.getVersion();
		dumpProperty(output, HardwareInspector.PROPERTY_OS_VERSION, version.getVersion());
		dumpProperty(output, HardwareInspector.PROPERTY_OS_BUILDNUMBER, version.getBuildNumber());
	}

	public static void dumpComputerSystem(OutputStream output) throws IOException {
		HardwareAbstractionLayer hal = staticSI.getHardware();
		ComputerSystem computerSystem = hal.getComputerSystem();
		dumpProperty(output, HardwareInspector.PROPERTY_SYSTEM_MANUFACTURER, computerSystem.getManufacturer());
		dumpProperty(output, HardwareInspector.PROPERTY_SYSTEM_MODEL, computerSystem.getModel());
		dumpProperty(output, HardwareInspector.PROPERTY_SYSTEM_SERIALNUMBER, computerSystem.getSerialNumber());

		Baseboard baseboard = computerSystem.getBaseboard();
		dumpProperty(output, HardwareInspector.PROPERTY_BASEBOARD_MANUFACTURER, baseboard.getManufacturer());
		dumpProperty(output, HardwareInspector.PROPERTY_BASEBOARD_MODEL, baseboard.getModel());
		dumpProperty(output, HardwareInspector.PROPERTY_BASEBOARD_VERSION, baseboard.getVersion());
		dumpProperty(output, HardwareInspector.PROPERTY_BASEBOARD_SERIALNUMBER, baseboard.getSerialNumber());

		Firmware firmware = computerSystem.getFirmware();
		dumpProperty(output, HardwareInspector.PROPERTY_FIRMWARE_MANUFACTURER, firmware.getManufacturer());
		dumpProperty(output, HardwareInspector.PROPERTY_FIRMWARE_VERSION, firmware.getVersion());
		dumpProperty(output, HardwareInspector.PROPERTY_FIRMWARE_RELEASEDATE, firmware.getReleaseDate());
		dumpProperty(output, HardwareInspector.PROPERTY_FIRMWARE_NAME, firmware.getName());
		dumpProperty(output, HardwareInspector.PROPERTY_FIRMWARE_DESCRIPTION, firmware.getDescription());
	
	}

	public static void dumpCentralProcessor(OutputStream output) throws IOException {
		HardwareAbstractionLayer hal = staticSI.getHardware();
		CentralProcessor processor = hal.getProcessor();
		dumpProperty(output, HardwareInspector.PROPERTY_CPU_VENDOR, processor.getVendor());
		dumpProperty(output, HardwareInspector.PROPERTY_CPU_FAMILY, processor.getFamily());
		dumpProperty(output, HardwareInspector.PROPERTY_CPU_MODEL, processor.getModel());
		dumpProperty(output, HardwareInspector.PROPERTY_CPU_NAME, processor.getName());
		dumpProperty(output, HardwareInspector.PROPERTY_CPU_IDENTIFIER, processor.getIdentifier());
		dumpProperty(output, HardwareInspector.PROPERTY_CPU_PROCESSORID, processor.getProcessorID());
	}

	private static void dumpProperty(OutputStream output, String name, String value) throws IOException {
		output.write(name.getBytes());
		output.write('=');
		output.write(value.getBytes());
		output.write('\n');
	}

	public static String extractProperty(String name) {
		if (name == null) {
			return null;
		}
		OperatingSystem os = staticSI.getOperatingSystem();
		HardwareAbstractionLayer hardware = staticSI.getHardware();
		ComputerSystem computerSystem = hardware.getComputerSystem();
		Baseboard baseboard = computerSystem.getBaseboard();
		Firmware firmware = computerSystem.getFirmware();
		CentralProcessor processor = hardware.getProcessor();

		switch (name) {
		case HardwareInspector.PROPERTY_OS_MANUFACTURER:
			return os.getManufacturer();
		case HardwareInspector.PROPERTY_OS_FAMILY:
			return os.getFamily();
		case HardwareInspector.PROPERTY_OS_VERSION:
			return os.getVersion().getVersion();
		case HardwareInspector.PROPERTY_OS_BUILDNUMBER:
			return os.getVersion().getBuildNumber();

		case HardwareInspector.PROPERTY_SYSTEM_MANUFACTURER:
			return computerSystem.getManufacturer();
		case HardwareInspector.PROPERTY_SYSTEM_MODEL:
			return computerSystem.getModel();
		case HardwareInspector.PROPERTY_SYSTEM_SERIALNUMBER:
			return computerSystem.getSerialNumber();

		case HardwareInspector.PROPERTY_BASEBOARD_MANUFACTURER:
			return baseboard.getManufacturer();
		case HardwareInspector.PROPERTY_BASEBOARD_MODEL:
			return baseboard.getModel();
		case HardwareInspector.PROPERTY_BASEBOARD_VERSION:
			return baseboard.getVersion();
		case HardwareInspector.PROPERTY_BASEBOARD_SERIALNUMBER:
			return baseboard.getSerialNumber();

		case HardwareInspector.PROPERTY_FIRMWARE_MANUFACTURER:
			return firmware.getManufacturer();
		case HardwareInspector.PROPERTY_FIRMWARE_VERSION:
			return firmware.getVersion();
		case HardwareInspector.PROPERTY_FIRMWARE_RELEASEDATE:
			return firmware.getReleaseDate();
		case HardwareInspector.PROPERTY_FIRMWARE_NAME:
			return firmware.getName();
		case HardwareInspector.PROPERTY_FIRMWARE_DESCRIPTION:
			return firmware.getDescription();

		case HardwareInspector.PROPERTY_CPU_VENDOR:
			return processor.getVendor();
		case HardwareInspector.PROPERTY_CPU_FAMILY:
			return processor.getFamily();
		case HardwareInspector.PROPERTY_CPU_MODEL:
			return processor.getModel();
		case HardwareInspector.PROPERTY_CPU_NAME:
			return processor.getName();
		case HardwareInspector.PROPERTY_CPU_IDENTIFIER:
			return processor.getIdentifier();
		case HardwareInspector.PROPERTY_CPU_PROCESSORID:
			return processor.getProcessorID();

		default:
			return null;
		}
	}

	public static boolean evaluateProperty(String name, String actual) {
		if (actual == null) {
			return false;
		}
		if (actual.equals(String.valueOf('*'))) {
			return true;
		}
		String expected = extractProperty(name);
		return Objects.equals(expected, actual);
	}

	public static Iterable<String> getKnownProperties() {
		return new ArrayList<>(knownProperties);
	}

}
