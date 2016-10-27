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
import com.greenenergycorp.openfmb.xml.Meter;
import com.greenenergycorp.openfmb.xml.Reading;
import com.greenenergycorp.openfmb.xml.ResourceReadingProfile;

import java.util.Map;

public class ResourceReadingAdapter implements JaxbObjectConverter {

    private final String messageId;
    private final Meter deviceDescription;

    public ResourceReadingAdapter(String messageId, Meter deviceDescription) {
        this.messageId = messageId;
        this.deviceDescription = deviceDescription;
    }

    public Object convert(Map<ReadingId, MeasValue> readingValues, Map<String, MeasValue> keyValues) throws Exception {
        if (readingValues != null && !readingValues.isEmpty()) {

            final ResourceReadingProfile module = new ResourceReadingProfile();

            final long now = System.currentTimeMillis();

            module.setLogicalDeviceID(messageId);
            module.setTimestamp(CommonMapping.xmlTimeFor(now));

            module.setMeter(deviceDescription);

            for (Map.Entry<ReadingId, MeasValue> entry : readingValues.entrySet()) {

                final Reading reading = CommonMapping.buildReading(entry.getKey(), entry.getValue(), now);
                if (reading != null) {
                    module.getReadings().add(reading);
                }
            }

            return module;

        } else {
            return null;
        }
    }
}
