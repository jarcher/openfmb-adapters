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
import com.greenenergycorp.openfmb.mapping.model.Control;
import com.greenenergycorp.openfmb.mapping.model.ControlHandler;
import com.greenenergycorp.openfmb.mapping.model.ControlMapping;
import com.greenenergycorp.openfmb.xml.EndDeviceControlType;
import com.greenenergycorp.openfmb.xml.SetPoint;

import java.util.List;

public class AdapterCommon {

    public static void handleEndDevice(final String adapterName, final ControlMapping handlerMap, final EndDeviceControlType endDeviceControlType) {
        if (endDeviceControlType.getAction() != null && endDeviceControlType.getType() != null) {
            final Control.EndDeviceControl endDeviceControl =
                    new Control.EndDeviceControl(
                            adapterName,
                            endDeviceControlType.getAction(),
                            endDeviceControlType.getType());

            final List<ControlHandler> controlHandlers = handlerMap.getDeviceControlMap().get(endDeviceControl);
            if (controlHandlers != null) {
                for (ControlHandler handler : controlHandlers) {
                    handler.handle(endDeviceControl);
                }
            }
        }
    }

    public static void handleSetpoints(final String adapterName, final ControlMapping handlerMap, final List<SetPoint> setpoints) {
        for (Object obj : setpoints) {
            SetPoint sp = (SetPoint) obj;

            final Control.SetpointId setpointId = new Control.SetpointId(
                    adapterName,
                    sp.getControlType(),
                    CommonMapping.convertFromXml(sp.getUnit()),
                    CommonMapping.convertFromXml(sp.getMultiplier()));

            final Control.SetpointControl setpointControl = new Control.SetpointControl(setpointId, sp.getValue());

            final List<ControlHandler> controlHandlers = handlerMap.getSetpointControlMap().get(setpointId);
            if (controlHandlers != null) {
                for (ControlHandler handler : controlHandlers) {
                    handler.handle(setpointControl);
                }
            }
        }
    }
}
