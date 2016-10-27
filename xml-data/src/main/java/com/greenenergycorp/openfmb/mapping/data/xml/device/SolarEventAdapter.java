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
import com.greenenergycorp.openfmb.xml.SolarEventProfile;
import com.greenenergycorp.openfmb.xml.SolarInverter;
import com.greenenergycorp.openfmb.xml.SolarInverterStatus;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Map;

public class SolarEventAdapter implements JaxbObjectConverter {

    private final String messageId;
    private final SolarInverter deviceDescription;

    public final static String isConnectedKey = "isConnected";

    public final static String[] keys = { isConnectedKey };

    public SolarEventAdapter(String messageId, SolarInverter deviceDescription) {
        this.messageId = messageId;
        this.deviceDescription = deviceDescription;
    }

    public Object convert(Map<ReadingId, MeasValue> readingValues, Map<String, MeasValue> keyValues) throws Exception {

        for (String key: keys) {
            if (!keyValues.containsKey(key)) {
                return null;
            }
        }

        final SolarEventProfile module = new SolarEventProfile();

        final long now = System.currentTimeMillis();
        final XMLGregorianCalendar calendarNow = CommonMapping.xmlTimeFor(now);

        module.setLogicalDeviceID(messageId);
        module.setTimestamp(CommonMapping.xmlTimeFor(now));

        module.setSolarInverter(deviceDescription);;

        Boolean isConnected = keyValues.get(isConnectedKey).asBoolean();
        if (isConnected == null) {
            return null;
        }

        final SolarInverterStatus solarStatus = new SolarInverterStatus();
        solarStatus.setIsConnected(isConnected);

        solarStatus.setTimestamp(calendarNow);
        solarStatus.setValue("");

        solarStatus.setQualityFlag(new byte[] {0, 0});

        module.setSolarInverterStatus(solarStatus);

        return module;
    }
}
