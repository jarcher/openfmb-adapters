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
package com.greenenergycorp.openfmb.mapping.data.xml;

import com.greenenergycorp.openfmb.mapping.adapter.MessageIdentifier;
import com.greenenergycorp.openfmb.mapping.adapter.PayloadObserver;
import com.greenenergycorp.openfmb.mapping.config.xml.BaseIdentified;
import com.greenenergycorp.openfmb.mapping.config.xml.BatteryControlProfile;
import com.greenenergycorp.openfmb.mapping.config.xml.RecloserControlProfile;
import com.greenenergycorp.openfmb.mapping.config.xml.SubscribersType;
import com.greenenergycorp.openfmb.mapping.data.xml.device.BatteryControlAdapter;
import com.greenenergycorp.openfmb.mapping.data.xml.device.RecloserControlAdapter;
import com.greenenergycorp.openfmb.mapping.model.ControlMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Loads subscriping OpenFMB XML adapters.
 */
public class XmlDataFormatSubscribersLoader {

    /**
     * Loads subscriping OpenFMB XML adapters.
     *
     * @param xml Configuration of OpenFMB publisher adapters.
     * @param marshaller Marshaller for OpenFMB XML data format.
     * @param controlMapping Callbacks for received control messages.
     * @param controlObservers Result mapping of control observers.
     */
    public static void loadSubscribers(
            final SubscribersType xml,
            final OpenFmbXmlMarshaller marshaller,
            final ControlMapping controlMapping,
            Map<MessageIdentifier, PayloadObserver> controlObservers) {

        final Map<String, String> logicalIdToAdapterName = new HashMap<String, String>();

        if (xml != null && xml.getElements() != null) {

            for (Object elem : xml.getElements()) {
                if (elem instanceof BaseIdentified) {
                    final BaseIdentified entry = (BaseIdentified) elem;
                    final String name = elem.getClass().getSimpleName();

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }

                    logicalIdToAdapterName.put(entry.getLogicalDeviceId(), entry.getAdapterName());
                } else {
                    throw new IllegalArgumentException("Element " + elem + " could not be identified");
                }
            }

            for (Object elem: xml.getElements()) {

                if (elem instanceof RecloserControlProfile) {
                    final RecloserControlProfile entry = (RecloserControlProfile) elem;
                    final String name = "RecloserControlProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }

                    final RecloserControlAdapter adapter = new RecloserControlAdapter(logicalIdToAdapterName, controlMapping);

                    final PayloadUnmarshaller transformer = new PayloadUnmarshaller(marshaller, adapter);

                    final MessageIdentifier mid = new MessageIdentifier(entry.getTopic(), entry.getLogicalDeviceId());

                    controlObservers.put(mid, transformer);

                } else if (elem instanceof BatteryControlProfile) {
                    final BatteryControlProfile entry = (BatteryControlProfile) elem;
                    final String name = "BatteryControlProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }

                    final BatteryControlAdapter adapter = new BatteryControlAdapter(logicalIdToAdapterName, controlMapping);

                    final PayloadUnmarshaller transformer = new PayloadUnmarshaller(marshaller, adapter);

                    final MessageIdentifier mid = new MessageIdentifier(entry.getTopic(), entry.getLogicalDeviceId());

                    controlObservers.put(mid, transformer);
                }
            }

        }
    }
}
