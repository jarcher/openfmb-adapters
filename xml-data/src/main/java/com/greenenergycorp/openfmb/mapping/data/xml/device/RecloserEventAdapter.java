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
import com.greenenergycorp.openfmb.xml.Recloser;
import com.greenenergycorp.openfmb.xml.RecloserEventProfile;
import com.greenenergycorp.openfmb.xml.RecloserStatus;
import com.greenenergycorp.openfmb.xml.SwitchStatusKind;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Map;

public class RecloserEventAdapter implements JaxbObjectConverter {

    private final String messageId;
    private final Recloser deviceDescription;

    public final static String isBlockedKey = "isBlocked";
    public final static String switchStatusKey = "SwitchStatus";

    public final static String[] keys = { isBlockedKey, switchStatusKey };

    public RecloserEventAdapter(String messageId, Recloser deviceDescription) {
        this.messageId = messageId;
        this.deviceDescription = deviceDescription;
    }

    public Object convert(Map<ReadingId, MeasValue> readingValues, Map<String, MeasValue> keyValues) throws Exception {

        for (String key: keys) {
            if (!keyValues.containsKey(key)) {
                return null;
            }
        }

        Boolean isBlocked = keyValues.get(isBlockedKey).asBoolean();
        if (isBlocked == null) {
            return null;
        }

        final Long switchStatusInt = keyValues.get(switchStatusKey).asLong();
        if (switchStatusInt == null) {
            return null;
        }
        final SwitchStatusKind statusEnum;
        if (switchStatusInt == 0) {
            statusEnum = SwitchStatusKind.CLOSED;
        } else {
            statusEnum = SwitchStatusKind.OPEN;
        }

        final RecloserEventProfile module = new RecloserEventProfile();

        final long now = System.currentTimeMillis();
        final XMLGregorianCalendar calendarNow = CommonMapping.xmlTimeFor(now);

        module.setLogicalDeviceID(messageId);
        module.setTimestamp(CommonMapping.xmlTimeFor(now));

        module.setRecloser(deviceDescription);

        final RecloserStatus recloserStatus = new RecloserStatus();

        recloserStatus.setIsBlocked(isBlocked);
        recloserStatus.setSwitchStatus(statusEnum);

        recloserStatus.setTimestamp(calendarNow);
        recloserStatus.setValue("");

        recloserStatus.setQualityFlag(new byte[] {0, 0});

        module.setRecloserStatus(recloserStatus);

        return module;
    }
}
