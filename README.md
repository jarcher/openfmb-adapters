![Logo of the Project](https://github.com/openfmb/dtech-demo-2016/blob/master/img/openfmb-tm-black_reduced_100.png)

# Description

This repository provides the simulators for ESS, Solar and the Recloser.  When building this repository a single jar is produced with differen entry points to start the different simulators.  Each simulator has a properties files to define the OpenFMB ID information and how the simulator operates. In addition the Island Balancer application can be initiated by the same jar with an entry point.   The Island Balancer application subscribes to the loadpublisher, solar, recloser and ESS to determine the state of the microgrid and the appropriate discharge of the ESS during an microgrid island state.

# Installing / Getting started

NOTE: This project is used in the Dtech Demo.  Refer to [Wiki](https://github.com/openfmb/dtech-demo-2016/wiki) for information on DTech Demo. 

To start the battery simulator
```shell
java -cp openfmb-simulators-0.0.5-SNAPSHOT-jar-with-dependencies.jar  com.greenenergycorp.openfmb.simulator.battery.BatterySimulator
```
To start the solar simulator
```shell
java -cp openfmb-simulators-0.0.5-SNAPSHOT-jar-with-dependencies.jar com.greenenergycorp.openfmb.simulator.solar.SolarSimulator
```
To start the recloser simulator
```shell
java -cp openfmb-simulators-0.0.5-SNAPSHOT-jar-with-dependencies.jar com.greenenergycorp.openfmb.simulator.recloser.RecloserSimulator
```
To start the balancer application
```shell
java -cp openfmb-simulators-0.0.5-SNAPSHOT-jar-with-dependencies.jar com.greenenergycorp.openfmb.simulator.balance.IslandBalancer
```

## Building

In order to build this project you must first build the [openfmb-adapters](https://github.com/openfmb/openfmb-adapters) project to create the XML to MQTT bindings. 

```shell
git clone https://github.com/openfmb/openfmb-simulators.git
cd openfmb-simulators
mvn clean install -Pslf4j-simple
```

The build jar is put in the target directory and needs to be moved to the main directory where the properities files are located. 


## Configuration

The configuration of each simulator and the application resides in the property files.

Configuration for the battery simulator resides [here](https://github.com/openfmb/openfmb-simulators/blob/master/batterysim.properties).

Configuration for the solar simulator resides [here](https://github.com/openfmb/openfmb-simulators/blob/master/solarsim.properties).

Configuration for the recloser simulator resides [here](https://github.com/openfmb/openfmb-simulators/blob/master/reclosersim.properties).

Configuration for the balancer application resider [here](https://github.com/openfmb/openfmb-simulators/blob/master/balancer.properties).

Each properties files defines the following.
```
device.logicalDeviceID
device.mRID
device.name
device.description

# topics to subscribe or publisher too
 
topic.<profile>=
 
# parameters for the simulator including publish rate if appropriate
 
```

# Contributing

Green Energy Corp, Daniel Evans

If you'd like to contribute, please fork the repository and use a feature
branch. Pull requests are warmly welcome.

Please review the [CONTRIBUTING](https://github.com/openfmb/openfmb-simulators/blob/master/CONTRIBUTING.md) file. 

# License

See the [APACHE_FILE_HEADER](https://github.com/openfmb/openfmb-simulators/blob/master/APACHE_FILE_HEADER) file for more info.
