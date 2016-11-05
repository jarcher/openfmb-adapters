UNDER CONSTRUCTION

![Logo of the Project](https://github.com/openfmb/dtech-demo-2016/blob/master/img/openfmb-tm-black_reduced_100.png)

# Description

This repository provides the DNP3 and Modbus bindings for the OpenFMB Model (Phase 1 Dec 15, 2015).  These bindings are also used for the simulators and the hmi in the microgrid demo.  Refer to [Wiki](https://github.com/openfmb/dtech-demo-2016/wiki). When buiding this project several targets are created.  The targets are the dnp3-to-mqtt and the modbus-to-mqtt.  This project pulls for outside repos for the DNP3 L2 and Modbus TCP/IP stacks. They are at the following locations. 

https://repo.totalgrid.org/artifactory/totalgrid-release/org/totalgrid/

Each adapter uses a configuration file to mapping the device protocol objects to the equivalent OpenFMB topics.  Sample files can be found [here](https://github.com/openfmb/openfmb-adapters/tree/master/configfiles).  

# Installing / Getting started

In order to use the DNP3 adapter you will need to install a DNP3 L2 master and slave on your system.  The instructions for to that are location [here](https://github.com/openfmb/openfmb-adapters/blob/master/DNP3INSTALL.md).  

The modbus adapter is installed during the build. 

To run the DNP3 Adapter you will need to use the following command.

```
shell
java -cp openfmb-dnp3-to-mqtt-0.0.5-SNAPSHOT-jar-with-dependencies.jar com.greenenergycorp.openfmb.mapping.mqtt.dnp3.Dnp3ToMqttEntry openfmb_dnp3_mqtt.xml
```

Note the config file in this case in is the same directory as the jar and called openfmb_dnp3_mqtt.xml.

To run the DNP3 Test Set you will need to use the following command.

```
shell
dnp3testset-S -F dnp3_slave_default.xml
```
Note: the config file in this case is called dnp3_slave_default.xml

To configure the Modbus adapter to match up with a simulator you will need a Modbus slave device or testset (which is not included in this distribution).  There are three devices that can be implemented with this adapter.  Solar, Load and Battery. A MODBUS slave test set can be found in the community for testing if required.

To run the Modbus Adapte you will need to use the following command.

```
shell
java -cp openfmb-dnp3-to-mqtt-0.0.5-SNAPSHOT-jar-with-dependencies.jar com.greenenergycorp.openfmb.mapping.mqtt.modbus.ModbusToMqttEntry openfmb_modbus_mqtt_battery.xml
```
Note the config file in this case in is the same directory as the jar and called openfmb_modbus_mqtt_battery.xml.

## Dependencies

Please refer to the Wiki pages for the [Demonstration](https://github.com/openfmb/turnkey-dtech-demo-2016/wiki/Simulation-Demonstration) and [Prerequisites](https://github.com/openfmb/turnkey-dtech-demo-2016/wiki/Simulation-Prerequisites) for the demo and these cooresponding repository projects. 

## Building

```shell
git clone https://github.com/openfmb/openfmb-adapters.git
cd openfmb-adaptors
mvn clean install -Pslf4j-simple
```

The build jar(s) are put in the target directories for the dnp3-to-mqtt and the modbus-to-mqtt subprojects and will need to be moved to the main directory where the config files are located. 

## Configuration

There is no configuration required specifically for the project to build.  The adapters will need to be configured to run.  

Refer to this [page](https://github.com/openfmb/openfmb-adapters/blob/master/DNP3TESTSET.md) on basic instructions for the DNP3 Slave Test Set.

Refer to this [page](https://github.com/openfmb/openfmb-adapters/blob/master/OPENFMBMODELING.md) for adapter modeling information that supports both the DNP3 and Modbus adapters.  Sample configuration files for both modbus and dnp3 have been provided. 

# Contributing

Daniel Evans, Green Energy Corp

If you'd like to contribute, please fork the repository and use a feature
branch. Pull requests are warmly welcome.

Please review the [CONTRIBUTING](https://github.com/openfmb/openfmb-adapters/blob/master/CONTRIBUTING.md) file. 

# License

See the [APACHE_FILE_HEADER](https://github.com/openfmb/openfmb-adapters/blob/master/APACHE_FILE_HEADER) file for more info.
