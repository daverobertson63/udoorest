/*
 * 
 */
package com.dhr.quad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// TODO: Auto-generated Javadoc
/**
 * Copyright (C) 2015 Cyril Bosselut <bossone0013@gmail.com>
 * <p>
 * This file is part of NeoJava examples for UDOO
 * <p>
 * NeoJava examples for UDOO is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * <p>
 * This libraries are distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class FileUtils {

	/** The Constant BASE_GPIO_URI. */
	//gpios
	public static final String BASE_GPIO_URI = "/sys/class/gpio";
	
	/** The Constant COMMON_GPIO_URI. */
	public static final String COMMON_GPIO_URI = BASE_GPIO_URI + "/gpio";
	
	/** The Constant EXPORT_GPIO_URI. */
	public static final String EXPORT_GPIO_URI = "/sys/class/gpio/export";
	
	/** The Constant RELEASE_GPIO_URI. */
	public static final String RELEASE_GPIO_URI = "/sys/class/gpio/unexport";
    
	/** The Constant BOARD_NAME_URI. */
	//board info
	public static final String BOARD_NAME_URI = "/etc/hostname";
	
	/** The Constant BOARD_CFG0_URI. */
	public static final String BOARD_CFG0_URI = "/sys/fsl_otp/HW_OCOTP_CFG0";
	
	/** The Constant BOARD_CFG1_URI. */
	public static final String BOARD_CFG1_URI = "/sys/fsl_otp/HW_OCOTP_CFG1";
	
	/** The Constant BOARD_MODEL_URI. */
	public static final String BOARD_MODEL_URI = "/proc/device-tree/model";

	/** The Constant TEMP_URI. */
	// temperature
	public static final String TEMP_URI = "/sys/class/i2c-dev/i2c-1/device/1-0048/temp1_input";

	/** The Constant TEMP_RAW_URI. */
	// barometer
	public static final String TEMP_RAW_URI = "/sys/class/i2c-dev/i2c-1/device/1-0060/iio:device0/in_temp_raw";
	
	/** The Constant TEMP_SCALE_URI. */
	public static final String TEMP_SCALE_URI = "/sys/class/i2c-dev/i2c-1/device/1-0060/iio:device0/in_temp_scale";
	
	/** The Constant PRESS_RAW_URI. */
	public static final String PRESS_RAW_URI = "/sys/class/i2c-dev/i2c-1/device/1-0060/iio:device0/in_pressure_raw";
	
	/** The Constant PRESS_SCALE_URI. */
	public static final String PRESS_SCALE_URI = "/sys/class/i2c-dev/i2c-1/device/1-0060/iio:device0/in_pressure_scale";

	/** The Constant MAGNETOMETER_ACTIVATION_URI. */
	// magnetometer
	public static final String MAGNETOMETER_ACTIVATION_URI = "/sys/class/misc/FreescaleMagnetometer/enable";
	
	/** The Constant MAGNETOMETER_DATA_URI. */
	public static final String MAGNETOMETER_DATA_URI = "/sys/class/misc/FreescaleMagnetometer/data";

	/** The Constant GYROSCOPE_ACTIVATION_URI. */
	// gyroscope
	public static final String GYROSCOPE_ACTIVATION_URI = "/sys/class/misc/FreescaleGyroscope/enable";
	
	/** The Constant GYROSCOPE_DATA_URI. */
	public static final String GYROSCOPE_DATA_URI = "/sys/class/misc/FreescaleGyroscope/data";

	/** The Constant ACCELEROMETER_ACTIVATION_URI. */
	// accelerometer
	public static final String ACCELEROMETER_ACTIVATION_URI = "/sys/class/misc/FreescaleAccelerometer/enable";
	
	/** The Constant ACCELEROMETER_DATA_URI. */
	public static final String ACCELEROMETER_DATA_URI = "/sys/class/misc/FreescaleAccelerometer/data";

	/**
	 * Read file.
	 *
	 * @param uri the uri
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String readFile(String uri) throws Exception {
		File file = new File(uri);
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		String value = br.readLine();
		br.close();
		return value;
	}
}
