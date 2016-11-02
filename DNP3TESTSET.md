# Starting the DNP3 Slave Client

For the DNP3 Slave you can run it from the *stage* directory.  This slave configuration file correlates to the adapter configuration file for default devices.

```
shell
dnp3testset -S -F dnp3_slave_default.xml
```

Running the command line start a console window as seen below.  Tying **help** at the prompt provides the basic list of commands. 

![](https://github.com/openfmb/dtech-demo-2016/blob/master/img/DNP3TestSet.png)

Typing the following sets analog 0 in the slave to 56 and flushes it so the master can read it. 

    >show
    >queue ai 0 56
    >flush

+ The **show** command list all the analog and digitals that the slave is providing.
+ The **queue** command accesses the specific measurement and sets a value.
+ The **ai 0** tells the queue command to access analog with index 0.
+ The **56** is the value being set for analog index 0.
+ The **flush** tells the test set to make it available to the master.


all
