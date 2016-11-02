The DNP3 Master can be run with the provide shell scripts in the **stage** directory.

      ./run_dnp_adapter.sh

NOTE:  The shell script includes the configuration file being used by the adapter.  You can modified the shell script for another configuration file or run the adapter directly from the command line with the configuration file of choice.

      java -cp openfmb-dnp3-to-mqtt-0.0.5-SNAPSHOT-jar-with-dependencies.jar com.greenenergycorp.openfmb.mapping.mqtt.dnp3.Dnp3ToMqttEntry openfmb_dnp3_mqtt.xml


## Starting the DNP3 Slave Client

For the DNP3 Slave you can run it from the *stage* directory.  This slave configuration file correlates to the adapter configuration file for all devices.

> dnp3testset -S -F [[dnp3_slave_all.xml]]

The [[DNP3 Test Set Instructions]] are provided for sending analogs and controls via the adapters

Typing the following sets analog 0 in the slave to 56 and flushes it so the master can read it. 

    >show
    >queue ai 0 56
    >flush

+ The **show** command list all the analog and digitals that the slave is providing.
+ The **queue** command accesses the specific measurement and sets a value.
+ The **ai 0** tells the queue command to access analog with index 0.
+ The **56** is the value being set for analog index 0.
+ The **flush** tells the test set to make it available to the master.


