# Starting the DNP3 Slave Client

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


