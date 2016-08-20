# PrototypeDatabase
A simple database which support base functions of the database for developers who are at the stage of ripid prototyping.

Guide
-------

<br> The way to init database
```
        PrototypeDatabase prototypeDatabase = new PrototypeDatabase();
        Database test_database = prototypeDatabase.createDataBase("test_database");
        Table test_table = test_database.createTable("test_table");
```

TODO
--------

<br>Use another file to manage the fields in the table
<br>Implments SQL support
<br>SQL engine
<br>Foreign key

