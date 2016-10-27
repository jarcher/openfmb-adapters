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
package com.greenenergycorp.openfmb.mapping.config;

import com.greenenergycorp.openfmb.mapping.config.xml.*;
import com.greenenergycorp.openfmb.mapping.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NamedDataMappingLoader {

    public static void loadDataMapping(Map<String, List<KeyUpdateMapping>> keyIdMap, Map<String, List<ReadingUpdateMapping>> readingIdMap, OpenFMBMapping xml) {

        if (xml.getDataMapping() != null && xml.getDataMapping().getMapping() != null) {

            for (OpenFMBMapping.DataMapping.Mapping map: xml.getDataMapping().getMapping()) {

                if (map.getMapFrom() == null) {
                    throw new IllegalArgumentException("Mapping must include name of source point");
                }
                final String measName = map.getMapFrom();

                if (map.getAdapterName() == null) {
                    throw new IllegalArgumentException("For " + measName + ", mapping must have adapter name");
                }
                final String adapterName = map.getAdapterName();

                MeasTransform transform = null;
                if (map.getTransform() != null) {
                    transform = TransformXmlLoader.load(map.getTransform(), measName);
                }

                if (map.getKey() != null) {

                    final KeyUpdateMapping keyUpdateMapping = new KeyUpdateMapping(new DeviceKeyId(adapterName, map.getKey()), transform);

                    final List<KeyUpdateMapping> currentList = keyIdMap.get(measName);
                    if (currentList != null) {
                        currentList.add(keyUpdateMapping);
                    } else {
                        final ArrayList<KeyUpdateMapping> entry = new ArrayList<KeyUpdateMapping>();
                        entry.add(keyUpdateMapping);
                        keyIdMap.put(measName, entry);
                    }

                } else {
                    final ReadingId readingId = loadReadingId(map, measName);

                    final ReadingUpdateMapping readingUpdateMapping = new ReadingUpdateMapping(new DeviceReadingId(adapterName, readingId), transform);
                    final List<ReadingUpdateMapping> currentList = readingIdMap.get(measName);
                    if (currentList != null) {
                        currentList.add(readingUpdateMapping);
                    } else {
                        final ArrayList<ReadingUpdateMapping> entry = new ArrayList<ReadingUpdateMapping>();
                        entry.add(readingUpdateMapping);
                        readingIdMap.put(measName, entry);
                    }
                }
            }
        }
    }

    public static class KeyUpdateMapping {
        private final DeviceKeyId id;
        private final MeasTransform transform;

        public KeyUpdateMapping(DeviceKeyId id, MeasTransform transform) {
            this.id = id;
            this.transform = transform;
        }

        public DeviceKeyId getId() {
            return id;
        }

        public MeasTransform getTransform() {
            return transform;
        }
    }

    public static class ReadingUpdateMapping {
        private final DeviceReadingId id;
        private final MeasTransform transform;

        public ReadingUpdateMapping(DeviceReadingId id, MeasTransform transform) {
            this.id = id;
            this.transform = transform;
        }

        public DeviceReadingId getId() {
            return id;
        }

        public MeasTransform getTransform() {
            return transform;
        }
    }

    public static ReadingId loadReadingId(ReadingDesc mapping, String logId) {

        final UnitSymbol unitXml = mapping.getUnit();
        if (unitXml == null) {
            throw new IllegalArgumentException("For " + logId + ", mapping must have key or all of name, unit, multiplier, flowDirection, and phases");
        }
        final UnitSymbolEnum unit = XmlEnumConversions.convertFromXml(unitXml);
        if (unit == null) {
            throw new IllegalArgumentException("For " + logId + ", unit mapping " + unitXml + " did not match OpenFMB enum");
        }

        final UnitMultiplier multiplierXml = mapping.getMultiplier();
        if (multiplierXml == null) {
            throw new IllegalArgumentException("For " + logId + ", mapping must have key or all of name, unit, multiplier, flowDirection, and phases");
        }
        final UnitMultiplierEnum multiplier = XmlEnumConversions.convertFromXml(multiplierXml);
        if (multiplier == null) {
            throw new IllegalArgumentException("For " + logId + ", unit multiplier mapping " + multiplierXml + " did not match OpenFMB enum");
        }

        final FlowDirection flowDirectionXml = mapping.getFlowDirection();
        if (flowDirectionXml == null) {
            throw new IllegalArgumentException("For " + logId + ", mapping must have key or all of name, unit, multiplier, flowDirection, and phases");
        }
        final FlowDirectionEnum flowDirection = XmlEnumConversions.convertFromXml(flowDirectionXml);
        if (flowDirection == null) {
            throw new IllegalArgumentException("For " + logId + ", flow direction mapping " + flowDirectionXml + " did not match OpenFMB enum");
        }

        final PhaseCode phasesXml = mapping.getPhases();
        if (phasesXml == null) {
            throw new IllegalArgumentException("For " + logId + ", mapping must have key or all of name, unit, multiplier, flowDirection, and phases");
        }
        final PhaseCodeEnum phases = XmlEnumConversions.convertFromXml(phasesXml);
        if (phases == null) {
            throw new IllegalArgumentException("For " + logId + ", phase code mapping " + flowDirectionXml + " did not match OpenFMB enum");
        }

        final String name = mapping.getName();
        if (name == null) {
            throw new IllegalArgumentException("For " + logId + ", mapping must have key or all of name, unit, multiplier, flowDirection, and phases");
        }

        return new ReadingId(unit, multiplier, flowDirection, phases, name);
    }

    public static class NamedXmlMarshaller {

        private final JAXBContext jaxbContext;

        private NamedXmlMarshaller(JAXBContext jaxbContext) {
            this.jaxbContext = jaxbContext;
        }

        public static NamedXmlMarshaller build() throws JAXBException {
            final JAXBContext jaxbContext = JAXBContext.newInstance(OpenFMBMapping.class);
            return new NamedXmlMarshaller(jaxbContext);
        }

        public OpenFMBMapping unmarshal(InputStream is) throws JAXBException {
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            final Object obj = unmarshaller.unmarshal(is);

            return (OpenFMBMapping)(obj);
        }
    }
}
