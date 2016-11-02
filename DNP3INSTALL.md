# DNP3 Installation

The DNP3 L2 adapter provided installs the master and slave components on your system.  The package install configuration files for the adapter.

The following figure depicts the logical setup of the DNP3 adapter.  In this configuration the adapter configuration file contains the configuration for each device in the microgrid. The purpose of this was to support integration testing of the adapter with the MQTT broker and HMI.  The configuration file can be manipulated to create a single device interface for the DNP3 test set or an actual device.  

![](https://github.com/openfmb/dtech-demo-2016/blob/master/img/Adapter%20Configuration.png)

Located [here](https://github.com/openfmb/openfmb-adapters/tree/master/configfiles) are the configuration files.

+ [[dnp3_slave_default.xml]] - DNP3 default slave file.
+ [[openfmb_dnp3_mqtt.xml]] - DNP3 default adapter file.
+ [[openfmb_dnp3_mqtt_all.xml]] - DNP3 adapter file with all components of the microgrid represented.
+ [[openfmb_dnp3_mqtt_load.xml]] - DNP3 adapter file with only load of the microgrid represented.

## Installing the DNP3 Libraries

`sudo apt-get update`

`sudo apt-get upgrade`

`sudo apt-get install build-essential git libtool autoconf`

`sudo apt-get install openjdk-8-jdk  (only if this has not be installed before)`

`sudo apt-get install swig2.0`

Note:  if swig2.0 does not install then install **swig**

`sudo apt-get install libboost-all-dev`

`git clone https://github.com/gec/dnp3.git`

`cd dnp3/`

`git checkout do`

`autoreconf -f -i`

`mkdir build`

`cd build/`

### When jni.h can’t be found
`sudo ln -s /usr/lib/jvm/java-1.8.0-openjdk-amd64/include/linux/* /usr/lib/jvm/java-1.8.0-openjdk-amd64/include/`

### Specify boost and Java directories
`../configure --with-boost-libdir=/usr/lib/x86_64-linux-gnu/ --with-java=/usr/lib/jvm/java-1.8.0-openjdk-amd64/include`

`make`

`sudo make install`
