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
package com.greenenergycorp.openfmb.mapping.model;


/**
 * Abstract base class for OpenFMB controls associated with a particular mapped device.
 *
 * The concrete types of OpenFMB controls are:
 *
 * - Key controls (values are used by OpenFMB struct fields).
 * - EndDeviceControls (values are used by the OpenFMB EndDeviceControl type)
 * - Setpoint controls (values are used by OpenFMB Setpoints)
 *
 */
public abstract class Control {

    /**
     * @return The device name used to map to a OpenFMB key.
     */
    public abstract String getDevice();

    /**
     * ID for key controls (keys refer to OpenFMB struct fields).
     */
    public static class KeyControlId {
        private final String device;
        private final String key;

        public KeyControlId(String device, String key) {
            this.device = device;
            this.key = key;
        }

        /**
         * @return The device name used to map to a key.
         */
        public String getDevice() {
            return device;
        }

        /**
         * @return The key (struct field) for the value of the control.
         */
        public String getKey() {
            return key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            KeyControlId that = (KeyControlId) o;

            if (device != null ? !device.equals(that.device) : that.device != null) return false;
            return !(key != null ? !key.equals(that.key) : that.key != null);

        }

        @Override
        public int hashCode() {
            int result = device != null ? device.hashCode() : 0;
            result = 31 * result + (key != null ? key.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "KeyControlId{" +
                    "device='" + device + '\'' +
                    ", key='" + key + '\'' +
                    '}';
        }
    }

    /**
     * Key controls with their value.
     */
    public static class KeyControl extends Control {
        private final KeyControlId id;
        private final MeasValue value;

        public KeyControl(KeyControlId id, MeasValue value) {
            this.id = id;
            this.value = value;
        }

        /**
         * @return ID of OpenFMB struct field.
         */
        public KeyControlId getId() {
            return id;
        }

        /**
         * @return Value of OpenFMB struct field.
         */
        public MeasValue getValue() {
            return value;
        }

        /**
         * @return Device name from mapping configuration
         */
        public String getDevice() {
            return id.getDevice();
        }

        @Override
        public String toString() {
            return "KeyControl{" +
                    "id=" + id +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * Control translated from OpenFMB EndDeviceControl struct.
     */
    public static class EndDeviceControl extends Control {
        private final String device;
        private final String action;
        private final String type;

        public EndDeviceControl(String device, String action, String type) {
            this.device = device;
            this.action = action;
            this.type = type;
        }

        /**
         * @return Device name from mapping configuration
         */
        public String getDevice() {
            return device;
        }

        /**
         * @return Action field of EndDeviceControl struct.
         */
        public String getAction() {
            return action;
        }

        /**
         * @return Type field of EndDeviceControl struct.
         */
        public String getType() {
            return type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EndDeviceControl that = (EndDeviceControl) o;

            if (device != null ? !device.equals(that.device) : that.device != null) return false;
            if (action != null ? !action.equals(that.action) : that.action != null) return false;
            return !(type != null ? !type.equals(that.type) : that.type != null);

        }

        @Override
        public int hashCode() {
            int result = device != null ? device.hashCode() : 0;
            result = 31 * result + (action != null ? action.hashCode() : 0);
            result = 31 * result + (type != null ? type.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "EndDeviceControl{" +
                    "device='" + device + '\'' +
                    ", action='" + action + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    /**
     * Control ID translated from OpenFMB SetPoint struct.
     */
    public static class SetpointId {
        private final String device;

        private final String controlType;
        private final UnitSymbolEnum unit;
        private final UnitMultiplierEnum multiplier;

        public SetpointId(String device, String controlType, UnitSymbolEnum unit, UnitMultiplierEnum multiplier) {
            this.device = device;
            this.controlType = controlType;
            this.unit = unit;
            this.multiplier = multiplier;
        }

        /**
         * @return Device name from mapping configuration
         */
        public String getDevice() {
            return device;
        }

        /**
         * @return Value of OpenFMB control type field.
         */
        public String getControlType() {
            return controlType;
        }

        /**
         * @return Value of OpenFMB unit field. See OpenFMB IDL for possible values.
         */
        public UnitSymbolEnum getUnit() {
            return unit;
        }

        /**
         * @return Value of OpenFMB unit multiplier field. See OpenFMB IDL for possible values.
         */
        public UnitMultiplierEnum getMultiplier() {
            return multiplier;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SetpointId that = (SetpointId) o;

            if (device != null ? !device.equals(that.device) : that.device != null) return false;
            if (controlType != null ? !controlType.equals(that.controlType) : that.controlType != null) return false;
            if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
            return !(multiplier != null ? !multiplier.equals(that.multiplier) : that.multiplier != null);

        }

        @Override
        public int hashCode() {
            int result = device != null ? device.hashCode() : 0;
            result = 31 * result + (controlType != null ? controlType.hashCode() : 0);
            result = 31 * result + (unit != null ? unit.hashCode() : 0);
            result = 31 * result + (multiplier != null ? multiplier.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "SetpointId{" +
                    "device='" + device + '\'' +
                    ", controlType='" + controlType + '\'' +
                    ", unit=" + unit +
                    ", multiplier=" + multiplier +
                    '}';
        }
    }

    /**
     * Control translated from OpenFMB SetPoint struct.
     */
    public static class SetpointControl extends Control {

        private final SetpointId id;
        private final float value;

        public SetpointControl(SetpointId id, float value) {
            this.id = id;
            this.value = value;
        }

        /**
         * @return OpenFMB SetPoint ID.
         */
        public SetpointId getId() {
            return id;
        }

        /**
         * @return Payload data of OpenFMB SetPoint struct.
         */
        public float getValue() {
            return value;
        }

        /**
         * @return Device name from mapping configuration
         */
        public String getDevice() {
            return id.getDevice();
        }

        @Override
        public String toString() {
            return "SetpointControl{" +
                    "id=" + id +
                    ", value=" + value +
                    '}';
        }
    }
}
