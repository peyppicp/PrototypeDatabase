# PrototypeDatabase
一个轻量级原型数据库，用于快速原型开发阶段。该数据库提供了基本的数据库操作。
A simple database which support base functions of the database for developers who are at the stage of ripid prototyping.

Guide&导航
-------

<br> 初始化数据库
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

        //必须在所有pField被设置完毕之后调用
        //must invoke after all pField setted
        student.create();
        
        //获得SQL处理引擎
        //get sql engine
        SQLEngine sqlEngine = student.getSqlEngine();
        
        //插入数据
        //insert data
        sqlEngine.executeTable(new InsertSQL(new Values(new String[]{"pyx","123456"})));
        sqlEngine.executeTable(new InsertSQL(new Values(new String[]{"wl","abc"})));
        sqlEngine.executeTable(new InsertSQL(new Values(new String[]{"lj","meiyoumima"})));
        
        //查询数据
        //select data
        SelectSQL selectSQL = new SelectSQL(new Select(student.listPFields().toArray(new PField[student.listPFields().size()])));
        Result result = sqlEngine.executeTable(selectSQL);
```

TODO&清单
--------
<br>使用properties文件作为字段属性文件   `finish`
<br>Use properties file to manage the fields in the table   `finish`
<br>实现SQL支持   `in progress`
<br>Implments SQL support   `in progress`
<br>SQL引擎   `in progress`
<br>SQL engine   `in progress`
<br>外键   `in progress`
<br>Foreign key   `in progress`
<br>SQL 解析器   `in progress`
<br>SQL parser   `in progress`

#######联系我840796994@qq.com
#######Contact me at 840796994@qq.com
