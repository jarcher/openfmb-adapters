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

import com.greenenergycorp.openfmb.mapping.adapter.DeviceAdapter;
import com.greenenergycorp.openfmb.mapping.adapter.MessageObserver;
import com.greenenergycorp.openfmb.mapping.adapter.SimpleMessageDeviceAdapter;
import com.greenenergycorp.openfmb.mapping.config.xml.BaseDescribedDevice;
import com.greenenergycorp.openfmb.mapping.config.xml.PublishersType;
import com.greenenergycorp.openfmb.mapping.config.xml.RecloserDescription;
import com.greenenergycorp.openfmb.mapping.config.xml.ResourceReadingProfile;
import com.greenenergycorp.openfmb.mapping.data.xml.device.*;
import com.greenenergycorp.openfmb.xml.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Loads publishing adapters from XML configuration.
 */
public class XmlDataFormatPublishersLoader {

    /**
     * Loads publishing adapters from XML configuration.
     *
     * @param xml Configuration of OpenFMB publisher adapters.
     * @param marshaller Marshaller for OpenFMB XML data format.
     * @param messageObserver Publishing interface for OpenFMB messages.
     * @return Map of adapter names to device adapters.
     */
    public static Map<String, DeviceAdapter> loadPublishers(final PublishersType xml,
                                                            final OpenFmbXmlMarshaller marshaller,
                                                            final MessageObserver messageObserver) {

        final HashMap<String, DeviceAdapter> map = new HashMap<String, DeviceAdapter>();

        if (xml != null && xml.getElements() != null) {
            for (Object elem : xml.getElements()) {

                if (elem instanceof com.greenenergycorp.openfmb.mapping.config.xml.RecloserEventProfile) {
                    final com.greenenergycorp.openfmb.mapping.config.xml.RecloserEventProfile entry = (com.greenenergycorp.openfmb.mapping.config.xml.RecloserEventProfile)elem;
                    final String name = "RecloserEventProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }
                    final Recloser description = getRecloserDescription(entry, name);

                    final RecloserEventAdapter converter = new RecloserEventAdapter(entry.getLogicalDeviceId(), description);

                    final DeviceAdapter deviceAdapter = adapterFor(converter, messageObserver, marshaller, entry.getTopic(), entry.getLogicalDeviceId());

                    map.put(entry.getAdapterName(), deviceAdapter);

                } else if (elem instanceof com.greenenergycorp.openfmb.mapping.config.xml.RecloserReadingProfile) {
                    final com.greenenergycorp.openfmb.mapping.config.xml.RecloserReadingProfile entry = (com.greenenergycorp.openfmb.mapping.config.xml.RecloserReadingProfile) elem;
                    final String name = "RecloserReadingProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }
                    final Recloser description = getRecloserDescription(entry, name);

                    final RecloserReadingAdapter converter = new RecloserReadingAdapter(entry.getLogicalDeviceId(), description);

                    final DeviceAdapter deviceAdapter = adapterFor(converter, messageObserver, marshaller, entry.getTopic(), entry.getLogicalDeviceId());

                    map.put(entry.getAdapterName(), deviceAdapter);

                } else if (elem instanceof com.greenenergycorp.openfmb.mapping.config.xml.ResourceReadingProfile) {
                    final com.greenenergycorp.openfmb.mapping.config.xml.ResourceReadingProfile entry = (com.greenenergycorp.openfmb.mapping.config.xml.ResourceReadingProfile) elem;
                    final String name = "ResourceReadingProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }
                    final Meter description = getResourceDescription(entry, name);

                    final ResourceReadingAdapter converter = new ResourceReadingAdapter(entry.getLogicalDeviceId(), description);

                    final DeviceAdapter deviceAdapter = adapterFor(converter, messageObserver, marshaller, entry.getTopic(), entry.getLogicalDeviceId());

                    map.put(entry.getAdapterName(), deviceAdapter);

                } else if (elem instanceof com.greenenergycorp.openfmb.mapping.config.xml.BatteryEventProfile) {
                    final com.greenenergycorp.openfmb.mapping.config.xml.BatteryEventProfile entry = (com.greenenergycorp.openfmb.mapping.config.xml.BatteryEventProfile)elem;
                    final String name = "BatteryEventProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }
                    final BatterySystem description = getBatteryDescription(entry, name);

                    final BatteryEventAdapter converter = new BatteryEventAdapter(entry.getLogicalDeviceId(), description);

                    final DeviceAdapter deviceAdapter = adapterFor(converter, messageObserver, marshaller, entry.getTopic(), entry.getLogicalDeviceId());

                    map.put(entry.getAdapterName(), deviceAdapter);

                } else if (elem instanceof com.greenenergycorp.openfmb.mapping.config.xml.BatteryReadingProfile) {
                    final com.greenenergycorp.openfmb.mapping.config.xml.BatteryReadingProfile entry = (com.greenenergycorp.openfmb.mapping.config.xml.BatteryReadingProfile) elem;
                    final String name = "BatteryReadingProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }
                    final BatterySystem description = getBatteryDescription(entry, name);

                    final BatteryReadingAdapter converter = new BatteryReadingAdapter(entry.getLogicalDeviceId(), description);

                    final DeviceAdapter deviceAdapter = adapterFor(converter, messageObserver, marshaller, entry.getTopic(), entry.getLogicalDeviceId());

                    map.put(entry.getAdapterName(), deviceAdapter);

                } else if (elem instanceof com.greenenergycorp.openfmb.mapping.config.xml.LoadReadingProfile) {
                    final com.greenenergycorp.openfmb.mapping.config.xml.LoadReadingProfile entry = (com.greenenergycorp.openfmb.mapping.config.xml.LoadReadingProfile) elem;
                    final String name = "LoadReadingProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }
                    final EnergyConsumer description = getLoadDescription(entry, name);

                    final LoadReadingAdapter converter = new LoadReadingAdapter(entry.getLogicalDeviceId(), description);

                    final DeviceAdapter deviceAdapter = adapterFor(converter, messageObserver, marshaller, entry.getTopic(), entry.getLogicalDeviceId());

                    map.put(entry.getAdapterName(), deviceAdapter);

                } else if (elem instanceof com.greenenergycorp.openfmb.mapping.config.xml.SolarReadingProfile) {
                    final com.greenenergycorp.openfmb.mapping.config.xml.SolarReadingProfile entry = (com.greenenergycorp.openfmb.mapping.config.xml.SolarReadingProfile) elem;
                    final String name = "SolarReadingProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }
                    final SolarInverter description = getSolarDescription(entry, name);

                    final SolarReadingAdapter converter = new SolarReadingAdapter(entry.getLogicalDeviceId(), description);

                    final DeviceAdapter deviceAdapter = adapterFor(converter, messageObserver, marshaller, entry.getTopic(), entry.getLogicalDeviceId());

                    map.put(entry.getAdapterName(), deviceAdapter);

                } else if (elem instanceof com.greenenergycorp.openfmb.mapping.config.xml.SolarEventProfile) {
                    final com.greenenergycorp.openfmb.mapping.config.xml.SolarEventProfile entry = (com.greenenergycorp.openfmb.mapping.config.xml.SolarEventProfile) elem;
                    final String name = "SolarEventProfile";

                    if (entry.getTopic() == null) {
                        throw new IllegalArgumentException(name + " must have topic name");
                    }
                    if (entry.getAdapterName() == null) {
                        throw new IllegalArgumentException(name + " must have adapterName");
                    }
                    if (entry.getLogicalDeviceId() == null) {
                        throw new IllegalArgumentException(name + " must have logicalDeviceId");
                    }
                    final SolarInverter description = getSolarDescription(entry, name);

                    final SolarEventAdapter converter = new SolarEventAdapter(entry.getLogicalDeviceId(), description);

                    final DeviceAdapter deviceAdapter = adapterFor(converter, messageObserver, marshaller, entry.getTopic(), entry.getLogicalDeviceId());

                    map.put(entry.getAdapterName(), deviceAdapter);

                }
            }
        }

        return map;
    }

    private static DeviceAdapter adapterFor(final JaxbObjectConverter converter,
                                            final MessageObserver messageObserver,
                                            final OpenFmbXmlMarshaller marshaller,
                                            final String topic,
                                            final String logicalDeviceId) {
        return new SimpleMessageDeviceAdapter(messageObserver, new MarshallingConverterWrapper(converter, marshaller), topic, logicalDeviceId);
    }


    private static Meter getResourceDescription(ResourceReadingProfile entry, String name) {
        if (entry.getMRID() == null) {
            throw new IllegalArgumentException(name + " must have mRID");
        }
        if (entry.getName() == null) {
            throw new IllegalArgumentException(name + " must have name");
        }
        if (entry.getDescription() == null) {
            throw new IllegalArgumentException(name + " must have description");
        }

        if (entry.getPowerSystemResource() == null) {
            throw new IllegalArgumentException(name + " must have PowerSystemResource");
        }

        if (entry.getPowerSystemResource().getMRID() == null) {
            throw new IllegalArgumentException(name + " must have PowerSystemResource mRID");
        }
        if (entry.getPowerSystemResource().getName() == null) {
            throw new IllegalArgumentException(name + " must have PowerSystemResource name");
        }
        if (entry.getPowerSystemResource().getDescription() == null) {
            throw new IllegalArgumentException(name + " must have PowerSystemResource description");
        }

        final PowerSystemResource powerSystemResource = new PowerSystemResource();
        powerSystemResource.setMRID(entry.getPowerSystemResource().getMRID());
        powerSystemResource.setName(entry.getPowerSystemResource().getName());
        powerSystemResource.setDescription(entry.getPowerSystemResource().getDescription());

        final Meter device = new Meter();
        device.setMRID(entry.getMRID());
        device.setName(entry.getName());
        device.setDescription(entry.getDescription());

        device.setPowerSystemResource(powerSystemResource);

        return device;
    }

    private static SolarInverter getSolarDescription(BaseDescribedDevice entry, String name) {
        if (entry.getMRID() == null) {
            throw new IllegalArgumentException(name + " must have mRID");
        }
        if (entry.getName() == null) {
            throw new IllegalArgumentException(name + " must have name");
        }
        if (entry.getDescription() == null) {
            throw new IllegalArgumentException(name + " must have description");
        }

        final SolarInverter solar = new SolarInverter();
        solar.setMRID(entry.getMRID());
        solar.setName(entry.getName());
        solar.setDescription(entry.getDescription());
        return solar;
    }


    private static Recloser getRecloserDescription(RecloserDescription entry, String name) {
        if (entry.getMRID() == null) {
            throw new IllegalArgumentException(name + " must have mRID");
        }
        if (entry.getName() == null) {
            throw new IllegalArgumentException(name + " must have name");
        }
        if (entry.getDescription() == null) {
            throw new IllegalArgumentException(name + " must have description");
        }
        if (entry.getNormalOpen() == null) {
            throw new IllegalArgumentException(name + " must have normalOpen");
        }

        final Recloser recloser = new Recloser();
        recloser.setMRID(entry.getMRID());
        recloser.setName(entry.getName());
        recloser.setDescription(entry.getDescription());
        recloser.setNormalOpen(entry.getNormalOpen());
        return recloser;
    }

    private static BatterySystem getBatteryDescription(BaseDescribedDevice entry, String name) {
        if (entry.getMRID() == null) {
            throw new IllegalArgumentException(name + " must have mRID");
        }
        if (entry.getName() == null) {
            throw new IllegalArgumentException(name + " must have name");
        }
        if (entry.getDescription() == null) {
            throw new IllegalArgumentException(name + " must have description");
        }

        final BatterySystem battery = new BatterySystem();
        battery.setMRID(entry.getMRID());
        battery.setName(entry.getName());
        battery.setDescription(entry.getDescription());
        return battery;
    }

    private static EnergyConsumer getLoadDescription(com.greenenergycorp.openfmb.mapping.config.xml.LoadReadingProfile entry, String name) {
        if (entry.getMRID() == null) {
            throw new IllegalArgumentException(name + " must have mRID");
        }
        if (entry.getName() == null) {
            throw new IllegalArgumentException(name + " must have name");
        }
        if (entry.getDescription() == null) {
            throw new IllegalArgumentException(name + " must have description");
        }
        if (entry.getOperatingLimit() == null) {
            throw new IllegalArgumentException(name + " must have operating limit");
        }

        final EnergyConsumer device = new EnergyConsumer();
        device.setMRID(entry.getMRID());
        device.setName(entry.getName());
        device.setDescription(entry.getDescription());
        device.setOperatingLimit(entry.getOperatingLimit());
        return device;
    }
}
