# PrototypeDatabase
A simple database which support base functions of the database for developers who are at the stage of ripid prototyping.

Guide
-------

<br> The way to init database
```
        PrototypeDatabase instance = PrototypeDatabase.instance();
        Database school = instance.createDataBase("school");
        Table student = school.createTable("student");

        PFieldConditions pFieldConditions = new PFieldConditions();
        pFieldConditions.setType(PFieldConstants.STRING);
        pFieldConditions.setIsPrimary(PFieldConstants.PRIMARY_KEY);
        pFieldConditions.setIsNotNull(PFieldConstants.NOT_NULL);
        student.createPField("name",pFieldConditions);

        PFieldConditions pFieldConditions1 = new PFieldConditions();
        pFieldConditions1.setType(PFieldConstants.STRING);
        pFieldConditions1.setIsNotNull(PFieldConstants.NOT_NULL);
        student.createPField("passwd",pFieldConditions1);

        student.create();

        SQLEngine sqlEngine = student.getSqlEngine();
        sqlEngine.executeTable(new InsertSQL(new Values(new String[]{"pyx","123456"})));
        sqlEngine.executeTable(new InsertSQL(new Values(new String[]{"wl","abc"})));
        sqlEngine.executeTable(new InsertSQL(new Values(new String[]{"lj","meiyoumima"})));
```

TODO
--------

<br>Use another file to manage the fields in the table   `finish`
<br>Implments SQL support   `in progress`
<br>SQL engine   `in progress`
<br>Foreign key   `in progress`
<br>SQL parser   `in progress`

#######Contact me at 840796994@qq.com
