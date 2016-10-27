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
package com.greenenergycorp.openfmb.mapping.dnp3;


import com.greenenergycorp.openfmb.mapping.config.MeasTransform;
import com.greenenergycorp.openfmb.mapping.config.TransformXmlLoader;
import com.greenenergycorp.openfmb.mapping.config.XmlEnumConversions;
import com.greenenergycorp.openfmb.mapping.config.xml.*;
import com.greenenergycorp.openfmb.mapping.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads data mapping configuration from XML.
 */
public class DataMappingReader {
    
    public static Dnp3DataMapping load(IndexMapping xml) {

        final Map<Long,Dnp3DataMapping.KeyEntry> binaryKeyMap = new HashMap<Long,Dnp3DataMapping.KeyEntry>();
        final Map<Long,Dnp3DataMapping.KeyEntry> analogKeyMap = new HashMap<Long,Dnp3DataMapping.KeyEntry>();
        final Map<Long,Dnp3DataMapping.KeyEntry> counterKeyMap = new HashMap<Long, Dnp3DataMapping.KeyEntry>();
        final Map<Long, Dnp3DataMapping.KeyEntry> controlStatusKeyMap = new HashMap<Long, Dnp3DataMapping.KeyEntry>();
        final Map<Long, Dnp3DataMapping.KeyEntry> setpointKeyMap = new HashMap<Long, Dnp3DataMapping.KeyEntry>();

        final Map<Long,Dnp3DataMapping.ReadingEntry> binaryReadingMap = new HashMap<Long, Dnp3DataMapping.ReadingEntry>();
        final Map<Long,Dnp3DataMapping.ReadingEntry> analogReadingMap = new HashMap<Long,Dnp3DataMapping.ReadingEntry>();
        final Map<Long,Dnp3DataMapping.ReadingEntry> counterReadingMap = new HashMap<Long,Dnp3DataMapping.ReadingEntry>();
        final Map<Long,Dnp3DataMapping.ReadingEntry> controlStatusReadingMap = new HashMap<Long,Dnp3DataMapping.ReadingEntry>();
        final Map<Long,Dnp3DataMapping.ReadingEntry> setpointReadingMap = new HashMap<Long,Dnp3DataMapping.ReadingEntry>();

        if (xml.getBinaries() != null && xml.getBinaries().getMapping() != null) {
            loadMappings(binaryKeyMap, binaryReadingMap, xml.getBinaries().getMapping());
        }

        if (xml.getAnalogs() != null && xml.getAnalogs().getMapping() != null) {
            loadMappings(analogKeyMap, analogReadingMap, xml.getAnalogs().getMapping());
        }

        if (xml.getCounters() != null && xml.getCounters().getMapping() != null) {
            loadMappings(counterKeyMap, counterReadingMap, xml.getCounters().getMapping());
        }

        if (xml.getControlStatuses() != null && xml.getControlStatuses().getMapping() != null) {
            loadMappings(controlStatusKeyMap, controlStatusReadingMap, xml.getControlStatuses().getMapping());
        }

        if (xml.getSetpointStatuses() != null && xml.getSetpointStatuses().getMapping() != null) {
            loadMappings(setpointKeyMap, setpointReadingMap, xml.getSetpointStatuses().getMapping());
        }

        return new Dnp3DataMapping(binaryKeyMap, analogKeyMap, counterKeyMap, controlStatusKeyMap, setpointKeyMap,
                binaryReadingMap, analogReadingMap, counterReadingMap, controlStatusReadingMap, setpointReadingMap);
    }

    private static void loadMappings(Map<Long,Dnp3DataMapping.KeyEntry> keyMap, Map<Long,Dnp3DataMapping.ReadingEntry> readMap, List<MappingSeq.Mapping> xmlList) {
        for (MappingSeq.Mapping mapping : xmlList) {

            if (mapping.getAdapterName() != null && mapping.getIndex() != null) {
                final String logId = mapping.getAdapterName() + ", index " + mapping.getIndex();
                if (mapping.getKey() != null) {

                    MeasTransform transform = null;
                    if (mapping.getTransform() != null) {
                        transform = TransformXmlLoader.load(mapping.getTransform(), logId);
                    }

                    keyMap.put(mapping.getIndex(), new Dnp3DataMapping.KeyEntry(new DeviceKeyId(mapping.getAdapterName(), mapping.getKey()), transform));

                } else {
                    final UnitSymbol unitXml = mapping.getUnit();
                    if (unitXml == null) {
                        throw new IllegalArgumentException("For " + logId + ", Mapping must have key or all of unit, multiplier, flowDirection, and phases");
                    }
                    final UnitSymbolEnum unit = XmlEnumConversions.convertFromXml(unitXml);
                    if (unit == null) {
                        throw new IllegalArgumentException("For " + logId + ", Unit mapping " + unitXml + " did not match Java enum");
                    }

                    final UnitMultiplier multiplierXml = mapping.getMultiplier();
                    if (multiplierXml == null) {
                        throw new IllegalArgumentException("For " + logId + ", Mapping must have key or all of unit, multiplier, flowDirection, and phases");
                    }
                    final UnitMultiplierEnum multiplier = XmlEnumConversions.convertFromXml(multiplierXml);
                    if (multiplier == null) {
                        throw new IllegalArgumentException("For " + logId + ", Unit multiplier mapping " + multiplierXml + " did not match Java enum");
                    }

                    final FlowDirection flowDirectionXml = mapping.getFlowDirection();
                    if (flowDirectionXml == null) {
                        throw new IllegalArgumentException("For " + logId + ", Mapping must have key or all of unit, multiplier, flowDirection, and phases");
                    }
                    final FlowDirectionEnum flowDirection = XmlEnumConversions.convertFromXml(flowDirectionXml);
                    if (flowDirection == null) {
                        throw new IllegalArgumentException("For " + logId + ", Flow direction mapping " + flowDirectionXml + " did not match Java enum");
                    }

                    final PhaseCode phasesXml = mapping.getPhases();
                    if (phasesXml == null) {
                        throw new IllegalArgumentException("For " + logId + ", Mapping must have key or all of unit, multiplier, flowDirection, and phases");
                    }
                    final PhaseCodeEnum phases = XmlEnumConversions.convertFromXml(phasesXml);
                    if (phases == null) {
                        throw new IllegalArgumentException("For " + logId + ", Phase code mapping " + flowDirectionXml + " did not match Java enum");
                    }

                    final ReadingId readingId = new ReadingId(unit, multiplier, flowDirection, phases, "");

                    MeasTransform transform = null;
                    if (mapping.getTransform() != null) {
                        transform = TransformXmlLoader.load(mapping.getTransform(), logId);
                    }

                    readMap.put(mapping.getIndex(), new Dnp3DataMapping.ReadingEntry(new DeviceReadingId(mapping.getAdapterName(), readingId), transform));
                }
            } else {
                throw new IllegalArgumentException("Mapping must have adapter name and index");
            }
        }
    }
}
