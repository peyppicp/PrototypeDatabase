package org.prototypeDatabase.entity;

import com.csvreader.CsvWriter;
import org.prototypeDatabase.conditions.PFieldConditions;
import org.prototypeDatabase.exception.PFieldNotFoundException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class Table {

    private String name;
    private List<PField> pFields = new LinkedList<>();
    private Database database;
    private File table_file;

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
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(pField.getName()).append("&").append(pField.getType()).append("&").append(pField.getConditions().getIsPrimary())
                    .append("&").append(pField.getConditions().getIsNotNull()).append("&").append(pField.getConditions().getIsUnique());
            records[0] = stringBuffer.toString();
            i++;
        }
        CsvWriter writer = new CsvWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(table_file, true), "UTF-8")), ',');
        writer.writeRecord(records);
        writer.close();
    }

    public void createPField(String name, String type, PFieldConditions conditions) {
        PField pField = new PField();
        pField.setName(name);
        pField.setType(type);
        pField.setConditions(conditions);
        pField.setTable(this);
        conditions.setPField(pField);
        addPFields(pField);
//        CsvWriter writer = new CsvWriter(new BufferedOutputStream(new FileOutputStream(table_file)), '&', Charset.forName("UTF-8"));
//        CsvWriter writer = new CsvWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(table_file, true), "UTF-8")), '&');
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public void addPFields(PField pField) {
        if (!pFields.contains(pField)) {
            pFields.add(pField);
        }
    }
}
