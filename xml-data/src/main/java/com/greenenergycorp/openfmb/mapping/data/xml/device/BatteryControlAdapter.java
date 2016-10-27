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

import com.greenenergycorp.openfmb.mapping.data.xml.MessageObjectObserver;
import com.greenenergycorp.openfmb.mapping.model.ControlMapping;
import com.greenenergycorp.openfmb.xml.BatteryControlProfile;

import java.util.Map;

public class BatteryControlAdapter implements MessageObjectObserver {
    private final Map<String, String> deviceIdToAdapterName;
    private final ControlMapping handlerMap;

    public BatteryControlAdapter(final Map<String, String> deviceIdToAdapterName, final ControlMapping handlerMap) {
        this.deviceIdToAdapterName = deviceIdToAdapterName;
        this.handlerMap = handlerMap;
    }

    public void handle(final Object object) {
        if (object instanceof BatteryControlProfile) {
            final BatteryControlProfile v = (BatteryControlProfile)object;

            if (v.getLogicalDeviceID() != null) {

                final String adapterName = deviceIdToAdapterName.get(v.getLogicalDeviceID());

                if (adapterName != null && v.getBatterySystemControl() != null) {

                    if (v.getBatterySystemControl().getEndDeviceControlType() != null) {
                        AdapterCommon.handleEndDevice(adapterName, handlerMap, v.getBatterySystemControl().getEndDeviceControlType());
                    }

                    AdapterCommon.handleSetpoints(adapterName, handlerMap, v.getBatterySystemControl().getSetPoints());
                }
            }
        }
    }
}
