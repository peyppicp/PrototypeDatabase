package org.prototypeDatabase.entity;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.prototypeDatabase.PrototypeDatabase;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.exception.AlreadyExistDatabaseException;
import org.prototypeDatabase.exception.TableNotFoundException;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class Database {

    private String name;
    private List<Table> tables = new LinkedList<>();
    private File database_file;
    private File properties_file;
    private PrototypeDatabase prototypeDatabase;
    private Properties properties;

    public List<Table> listTables() {
        return tables;
    }

    public void execute(SQLInterface sqlInterface) {

    }

    public Table createTable(String name) throws AlreadyExistDatabaseException, IOException, SAXException {
//        File csv_file = new File(database_file, name + ".csv");
//        if (csv_file.exists()) {
//            throw new AlreadyExistDatabaseException(name + "already exist!");
//        }
//        csv_file.createNewFile();

        File xml_file = new File(database_file, name + ".xml");
        if (xml_file.exists()) {
            throw new AlreadyExistDatabaseException(name + "already exist!");
        }
        xml_file.createNewFile();

        Document document = DocumentHelper.createDocument(DocumentHelper.createElement("records"));
        document.setXMLEncoding("UTF-8");
        document.setName(this.getName());

        XMLWriter xmlWriter = new XMLWriter(new BufferedWriter(new FileWriter(xml_file)), OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        xmlWriter.close();

        Table table = new Table();
        table.setName(name);
//        table.setTable_file(csv_file);
        table.setXml_file(xml_file);
        table.setPropertiesFile(properties_file);
        table.setDatabase(this);
        tables.add(table);
        return table;
    }

    public void dropTable(Table table) throws TableNotFoundException {
        File table_file = table.getXml_file();
        if (table_file.exists()) {
            table_file.delete();
        } else {
            throw new TableNotFoundException(table.getName() + "does not found!");
        }
        removeTable(table);
    }

    public void removeTable(Table table) throws TableNotFoundException {
        if (tables.contains(table)) {
            tables.remove(table);
        } else {
            throw new TableNotFoundException(table.getName() + "does not found!");
        }
    }

    public Table getTableByName(String name) throws TableNotFoundException {
        for (Table table : tables) {
            if (table.getName().equals(name)) {
                return table;
            }
        }
        throw new TableNotFoundException(name + "does not found!");
    }

    public File getDatabase_file() {
        return database_file;
    }

    public void setDatabase_file(File database_file) {
        this.database_file = database_file;
    }

    public void addTables(Table table) {
        if (!tables.contains(table)) {
            tables.add(table);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrototypeDatabase getPrototypeDatabase() {
        return prototypeDatabase;
    }

    public void setPrototypeDatabase(PrototypeDatabase prototypeDatabase) {
        this.prototypeDatabase = prototypeDatabase;
    }

    public File getProperties_file() {
        return properties_file;
    }

    public void setProperties_file(File properties_file) {
        this.properties_file = properties_file;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public File getPropertiesFile() {
        return properties_file;
    }

    public void setPropertiesFile(File properties) {
        this.properties_file = properties;
    }

}