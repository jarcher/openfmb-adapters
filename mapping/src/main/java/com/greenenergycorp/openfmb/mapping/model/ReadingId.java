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
 * ID describing an entry in an OpenFMB reading sequence.
 */
public class ReadingId {

    private final UnitSymbolEnum unit;
    private final UnitMultiplierEnum multiplier;
    private final FlowDirectionEnum flowDirection;
    private final PhaseCodeEnum phases;

    private final String name;

    /**
     *
     * @param unit OpenFMB unit.
     * @param multiplier OpenFMB multiplier.
     * @param flowDirection OpenFMB flow direction.
     * @param phases OpenFMB phase.
     * @param name OpenFMB reading name.
     */
    public ReadingId(UnitSymbolEnum unit, UnitMultiplierEnum multiplier, FlowDirectionEnum flowDirection, PhaseCodeEnum phases, String name) {
        this.unit = unit;
        this.multiplier = multiplier;
        this.flowDirection = flowDirection;
        this.phases = phases;
        this.name = name;
    }

    /**
     * @return OpenFMB unit.
     */
    public UnitSymbolEnum getUnit() {
        return unit;
    }

    /**
     * @return OpenFMB multiplier.
     */
    public UnitMultiplierEnum getMultiplier() {
        return multiplier;
    }

    /**
     * @return OpenFMB flow direction.
     */
    public FlowDirectionEnum getFlowDirection() {
        return flowDirection;
    }

    /**
     * @return OpenFMB phase.
     */
    public PhaseCodeEnum getPhases() {
        return phases;
    }

    /**
     * @return OpenFMB reading name.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadingId readingId = (ReadingId) o;

        if (unit != null ? !unit.equals(readingId.unit) : readingId.unit != null) return false;
        if (multiplier != null ? !multiplier.equals(readingId.multiplier) : readingId.multiplier != null) return false;
        if (flowDirection != null ? !flowDirection.equals(readingId.flowDirection) : readingId.flowDirection != null)
            return false;
        if (phases != null ? !phases.equals(readingId.phases) : readingId.phases != null) return false;
        return !(name != null ? !name.equals(readingId.name) : readingId.name != null);

    }

    @Override
    public int hashCode() {
        int result = unit != null ? unit.hashCode() : 0;
        result = 31 * result + (multiplier != null ? multiplier.hashCode() : 0);
        result = 31 * result + (flowDirection != null ? flowDirection.hashCode() : 0);
        result = 31 * result + (phases != null ? phases.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReadingId{" +
                "unit=" + unit +
                ", multiplier=" + multiplier +
                ", flowDirection=" + flowDirection +
                ", phases=" + phases +
                ", name='" + name + '\'' +
                '}';
    }
}
