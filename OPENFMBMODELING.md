The following are references for the adapter xml.  This is not complete but provides some basics so that you can modify the existing xml. This information is valid for both the DNP3-MQTT and Modbus-MQTT adapter xml.


## Enums for OpenFMB

      <xs:simpleType name="UnitMultiplier">
      <xs:annotation>
      <xs:documentation>The unit multipliers defined for the CIM.</xs:documentation>
      </xs:annotation>
            <xs:restriction base="xs:string">
            <xs:enumeration value="centi"/>
            <xs:enumeration value="deci"/>
            <xs:enumeration value="Giga"/>
            <xs:enumeration value="kilo"/>
            <xs:enumeration value="milli"/>
            <xs:enumeration value="Mega"/>
            <xs:enumeration value="micro"/>
            <xs:enumeration value="nano"/>
            <xs:enumeration value="noMultiplier"/>
            <xs:enumeration value="pico"/>
            <xs:enumeration value="Tera"/>      
      </xs:restriction>
      </xs:simpleType>
            <xs:simpleType name="UnitSymbol">
            <xs:restriction base="xs:string">
            <xs:enumeration value="Amp"/>
            <xs:enumeration value="deg"/>
            <xs:enumeration value="degC"/>
            <xs:enumeration value="degF"/>
            <xs:enumeration value="Farad"/>
            <xs:enumeration value="gram"/>
            <xs:enumeration value="hour"/>
            <xs:enumeration value="Henry"/>
            <xs:enumeration value="Hz"/>
            <xs:enumeration value="Joule"/>
            <xs:enumeration value="meter"/>
            <xs:enumeration value="m2"/>
            <xs:enumeration value="m3"/>
            <xs:enumeration value="min"/>
            <xs:enumeration value="mph"/>
            <xs:enumeration value="Newton"/>
            <xs:enumeration value="noUnit"/>
            <xs:enumeration value="ohm"/>
            <xs:enumeration value="Pa"/>
            <xs:enumeration value="rad"/>
            <xs:enumeration value="Siemens"/>
            <xs:enumeration value="V"/>
            <xs:enumeration value="VA"/>
            <xs:enumeration value="VAh"/>
            <xs:enumeration value="VAr"/>
            <xs:enumeration value="VArh"/>
            <xs:enumeration value="W"/>
            <xs:enumeration value="Wh"/>
            <xs:enumeration value="wPerVA"/>
            <xs:enumeration value="WPerM2"/>
      </xs:restriction>
      </xs:simpleType>
            <xs:simpleType name="FlowDirection">
            <xs:restriction base="xs:string">
            <xs:enumeration value="forward"/>
            <xs:enumeration value="lagging"/>
            <xs:enumeration value="leading"/>
            <xs:enumeration value="net"/>
            <xs:enumeration value="noDirection"/>
            <xs:enumeration value="q1minusQ4"/>
            <xs:enumeration value="q1plusQ2"/>
            <xs:enumeration value="q1plusQ3"/>
            <xs:enumeration value="q1plusQ4"/>
            <xs:enumeration value="q2minusQ3"/>
            <xs:enumeration value="q2plusQ3"/>
            <xs:enumeration value="q2plusQ4"/>
            <xs:enumeration value="q3minusQ2"/>
            <xs:enumeration value="q3plusQ4"/>
            <xs:enumeration value="quadrant1"/>
            <xs:enumeration value="quadrant2"/>
            <xs:enumeration value="quadrant3"/>
            <xs:enumeration value="quadrant4"/>
            <xs:enumeration value="reverse"/>
            <xs:enumeration value="total"/>
            <xs:enumeration value="totalByPhase"/>
      </xs:restriction>
      </xs:simpleType>
            <xs:simpleType name="PhaseCode">
            <xs:restriction base="xs:string">
            <xs:enumeration value="A"/>
            <xs:enumeration value="AB"/>
            <xs:enumeration value="ABC"/>
            <xs:enumeration value="ABCN"/>
            <xs:enumeration value="ABN"/>
            <xs:enumeration value="AC"/>
            <xs:enumeration value="ACN"/>
            <xs:enumeration value="AN"/>
            <xs:enumeration value="B"/>
            <xs:enumeration value="BC"/>
            <xs:enumeration value="BCN"/>
            <xs:enumeration value="BN"/>
            <xs:enumeration value="C"/>
            <xs:enumeration value="CN"/>
            <xs:enumeration value="N"/>
            <xs:enumeration value="noPhase"/>
            <xs:enumeration value="s1"/>
            <xs:enumeration value="s12"/>
            <xs:enumeration value="s12N"/>
            <xs:enumeration value="s1N"/>
            <xs:enumeration value="s2"/>
            <xs:enumeration value="s2N"/>
      </xs:restriction>
      </xs:simpleType>

##  OpenFMB Topics for Demo

     > topic.BatteryReadingProfile=openfmb/batterymodule/BatteryReadingProfile
     > topic.BatteryEventProfile=openfmb/batterymodule/BatteryEventProfile
     > topic.BatteryControlProfile=openfmb/batterymodule/BatteryControlProfile

     > topic.RecloserEventProfile=openfmb/reclosermodule/RecloserEventProfile
     > topic.RecloserReadingProfile=openfmb/reclosermodule/RecloserReadingProfile
     > topic.RecloserControlProfile=openfmb/reclosermodule/RecloserControlProfile

     > topic.BatteryReadingProfile=openfmb/batterymodule/BatteryReadingProfile
     > topic.ResourceReadingProfile=openfmb/resourcemodule/ResourceReadingProfile
     > topic.SolarReadingProfile=openfmb/solarmodule/SolarReadingProfile

     > topic.SolarReadingProfile=openfmb/solarmodule/SolarReadingProfile
     > topic.SolarEventProfile=openfmb/solarmodule/SolarEventProfile