package org.prototypeDatabase.entity;

import com.csvreader.CsvWriter;
import org.prototypeDatabase.conditions.PFieldConditions;
import org.prototypeDatabase.core.SQLEngine;
import org.prototypeDatabase.exception.PFieldNotFoundException;
import org.prototypeDatabase.util.reflect.PFieldReflecter;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class Table {

    private String name;
    private List<PField> pFields = new LinkedList<>();
    private Database database;
    private File table_file;
    private File properties_file;
    private Properties properties = new Properties();
    private SQLEngine sqlEngine = new SQLEngine(this);

    public List<PField> listPFields() {
        return pFields;
    }

    public PField getPFieldByName(String name) throws PFieldNotFoundException {
        for (PField pField : pFields) {
            if (pField.getName().equals(name)) {
                return pField;
            }
        }
        throw new PFieldNotFoundException();
    }

    public void create() throws IOException {
        String[] records = new String[pFields.size()];
        int i = 0;
        for (PField pField : pFields) {
            records[i] = pField.getName();
            i++;
        }
        CsvWriter writer = new CsvWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(table_file, true), "UTF-8")), ',');
        writer.writeRecord(records);
        writer.close();
        properties.store(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(properties_file, true), "UTF-8")), "");
    }

    public void createPField(String name, PFieldConditions conditions) throws IllegalAccessException, IOException {
        PField pField = new PField();
        pField.setName(name);
//        pField.setType(type);
        pField.setConditions(conditions);
        pField.setTable(this);
        conditions.setPField(pField);
        addPFields(pField);

        PFieldReflecter pFieldReflecter = new PFieldReflecter();
        String key = new StringBuffer(this.name).append(".").append(name).toString();
        String value = pFieldReflecter.getValue(conditions);
        properties.setProperty(key, value);
    }

    public void addPFields(PField pField) {
        if (!pFields.contains(pField)) {
            pFields.add(pField);
        }
    }

    public SQLEngine getSqlEngine() {
        return sqlEngine;
    }

    public File getPropertiesFile() {
        return properties_file;
    }

    public void setPropertiesFile(File properties) {
        this.properties_file = properties;
    }

    private SQLEngine getSQLEngine() {
        return sqlEngine;
    }

    public List<PField> getPFields() {
        return pFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getTable_file() {
        return table_file;
    }

    public void setTable_file(File table_file) {
        this.table_file = table_file;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

}
