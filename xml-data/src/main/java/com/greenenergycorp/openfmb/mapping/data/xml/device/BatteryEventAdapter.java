/**
 * Copyright 2016 Green Energy Corp.
 *
 * Licensed to Green Energy Corp (www.greenenergycorp.com) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. Green Energy
 * Corp licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.greenenergycorp.openfmb.mapping.data.xml.device;

import com.greenenergycorp.openfmb.mapping.data.xml.CommonMapping;
import com.greenenergycorp.openfmb.mapping.data.xml.JaxbObjectConverter;
import com.greenenergycorp.openfmb.mapping.model.MeasValue;
import com.greenenergycorp.openfmb.mapping.model.ReadingId;
import com.greenenergycorp.openfmb.xml.BatteryEventProfile;
import com.greenenergycorp.openfmb.xml.BatteryStatus;
import com.greenenergycorp.openfmb.xml.BatterySystem;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Map;

public class BatteryEventAdapter implements JaxbObjectConverter {

    private final String messageId;
    private final BatterySystem deviceDescription;

    public final static String isChargingKey = "isCharging";
    public final static String isConnectedKey = "isConnected";
    public final static String modeKey = "mode";
    public final static String stateOfChargeKey = "stateOfCharge";

    public final static String[] keys = { isChargingKey, isConnectedKey, modeKey, stateOfChargeKey };

    public BatteryEventAdapter(String messageId, BatterySystem deviceDescription) {
        this.messageId = messageId;
        this.deviceDescription = deviceDescription;
    }

    public Object convert(Map<ReadingId, MeasValue> readingValues, Map<String, MeasValue> keyValues) throws Exception {

        for (String key: keys) {
            if (!keyValues.containsKey(key)) {
                return null;
            }
        }

        final BatteryEventProfile module = new BatteryEventProfile();

        final long now = System.currentTimeMillis();
        final XMLGregorianCalendar calendarNow = CommonMapping.xmlTimeFor(now);

        module.setLogicalDeviceID(messageId);
        module.setTimestamp(calendarNow);

        module.setBatterySystem(deviceDescription);

        Boolean isCharging = keyValues.get(isChargingKey).asBoolean();
        if (isCharging == null) {
            return null;
        }
        Boolean isConnected = keyValues.get(isConnectedKey).asBoolean();
        if (isConnected == null) {
            return null;
        }
        String mode = keyValues.get(modeKey).asString();
        if (mode == null) {
            return null;
        }
        Double stateOfCharge = keyValues.get(stateOfChargeKey).asDouble();
        if (stateOfCharge == null) {
            return null;
        }

        final BatteryStatus batteryStatus = new BatteryStatus();
        batteryStatus.setIsCharging(isCharging);
        batteryStatus.setIsConnected(isConnected);
        batteryStatus.setMode(mode);
        batteryStatus.setStateOfCharge((float)(double)stateOfCharge);

        batteryStatus.setTimestamp(calendarNow);
        batteryStatus.setValue("");

        batteryStatus.setQualityFlag(new byte[] {0, 0});

        module.setBatteryStatus(batteryStatus);

        return module;
    }
}
