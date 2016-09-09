package org.prototypeDatabase.core.update;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.prototypeDatabase.conditions.sql.From;
import org.prototypeDatabase.conditions.sql.Result;
import org.prototypeDatabase.conditions.sql.Set;
import org.prototypeDatabase.conditions.sql.Where;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.PField;
import org.prototypeDatabase.entity.Table;
import org.prototypeDatabase.entity.cache.TableCache;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class UpdateSQL implements SQLInterface {

    private From from;
    private List<Set> setList = new LinkedList<>();
    private List<Where> whereList = new LinkedList<>();

    public UpdateSQL() {
    }

    public UpdateSQL(List<Set> setList) {
        this.setList = setList;
    }

    public UpdateSQL(List<Set> setList, List<Where> whereList) {
        this.setList = setList;
        this.whereList = whereList;
    }

    public UpdateSQL(From from, List<Set> setList, List<Where> whereList) {
        this.from = from;
        this.setList = setList;
        this.whereList = whereList;
    }

    public Result executeXML(Table table) throws DocumentException, IOException {
        File xml_file = table.getXml_file();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xml_file);
        Element rootElement = document.getRootElement();
        Iterator<Element> iterator = rootElement.elementIterator("record");
        while (iterator.hasNext()) {
            Element record = iterator.next();
            Iterator<Element> values = record.elementIterator("value");
            while (values.hasNext()) {
                Element value = values.next();
                for (Set set : setList) {
                    if (value.attributeValue("name").equals(set.getpField().getName())) {
                        value.setText(set.getValue());
                    }
                }
            }
        }
        XMLWriter xmlWriter = new XMLWriter(new BufferedWriter(new FileWriter(xml_file)), OutputFormat.createCompactFormat());
        xmlWriter.write(document);
        xmlWriter.close();

        return null;
    }

    public Result executeXML(Table table, boolean bool) throws DocumentException, IOException {
        File xml_file = table.getXml_file();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xml_file);
        Element rootElement = document.getRootElement();
        Iterator<Element> iterator = rootElement.elementIterator("record");
        while (iterator.hasNext()) {
            Element record = iterator.next();
            Iterator<Element> values = record.elementIterator("value");
            int i = 0;
            while (values.hasNext()) {
                Element value = values.next();
                for (Where where : whereList) {
                    String name = value.attributeValue("name");
                    String pField_name = where.getpField().getName();
                    String text = value.getText();
                    String text1 = where.getValue();
                    if (name.equals(pField_name) && text.equals(text1)) {
                        i++;
                    }
                    if (i == whereList.size()) {
                        for (Set set : setList) {
                            if (name.equals(set.getpField().getName())) {
                                value.setText(set.getValue());
                            }
                        }
                        i = 0;
                    }
                }
            }
        }
        XMLWriter xmlWriter = new XMLWriter(new BufferedWriter(new FileWriter(xml_file)), OutputFormat.createCompactFormat());
        xmlWriter.write(document);
        xmlWriter.close();

        return null;
    }

    @Override
    public Result executeTable(Table table) throws IOException, DocumentException {
        if (whereList.size() == 0 || whereList.isEmpty()) {
            return executeXML(table);
        }
        return executeXML(table, true);
    }

    @Override
    public Result executeGlobal() throws IOException {
        return null;
    }

    @Deprecated
    public Result UpdateCSV(Table table, boolean bool) throws IOException {
        CsvReader reader = null;
        CsvWriter writer = null;
        File table_file = table.getTable_file();
        TableCache tableCache = table.getTableCache();
        Result result = new Result();
        LinkedList<String[]> results = new LinkedList<>();
        LinkedList<String[]> records = new LinkedList<>();
        reader = new CsvReader(new BufferedReader(new InputStreamReader(new FileInputStream(table_file), "UTF-8")), ',');
        reader.readHeaders();
        String[] headers = reader.getHeaders();
        while (reader.readRecord()) {
            List<PField> pFields = table.getPFields();
            String[] strings = new String[pFields.size()];
            int i = 0, count = 0;
            for (PField pField : pFields) {
                strings[i] = reader.get(pField.getName());
                //寻找满足全部where条件的pField
                for (Where where : whereList) {
                    if (where.getpField() == pField && strings[i].equals(where.getValue())) {
                        count++;
                    }
                }
                //找到了满足条件的pField，则进行set动作
                if (count == whereList.size()) {
                    for (Set set : setList) {
                        if (pField == set.getpField()) {
                            strings[i] = set.getValue();
                        }
                    }
                    tableCache.addRecord(this, strings);
                }
                i++;
//                PFieldConditions conditions = pField.getConditions();
//                if(conditions.getPrimary().equals(PFieldConstants.PRIMARY_KEY)||conditions.getUnique().equals(PFieldConstants.UNQIUE)){
//                    for()
//                }
            }
            records.add(strings);
        }
        //写入数据
        writer = new CsvWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(table_file, false), "UTF-8")), ',');
        writer.writeRecord(headers);
        for (String[] record : records) {
            writer.writeRecord(record);
        }
        writer.flush();
        writer.close();
        result.setResultsList(results);
        return result;
    }

    @Deprecated
    public Result UpdateCSV(Table table) throws IOException {
        CsvReader reader = null;
        CsvWriter writer = null;
        File table_file = table.getTable_file();
        Result result = new Result();
        LinkedList<String[]> results = new LinkedList<>();
        LinkedList<String[]> records = new LinkedList<>();
        reader = new CsvReader(new BufferedReader(new InputStreamReader(new FileInputStream(table_file), "UTF-8")), ',');
        reader.readHeaders();
        String[] headers = reader.getHeaders();
        while (reader.readRecord()) {
            List<PField> pFields = table.getPFields();
            String[] strings = new String[pFields.size()];
            int i = 0;
            for (PField pField : pFields) {
                for (Set set : setList) {
                    if (pField == set.getpField()) {
                        strings[i] = set.getValue();
                        if (!results.contains(strings)) {
                            results.add(strings);
                        }
                    } else {
                        strings[i] = reader.get(pField.getName());
                    }
                    i++;
                }
            }
            records.add(strings);
        }
        writer = new CsvWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(table_file, false), "UTF-8")), ',');
        writer.writeRecord(headers);
        for (String[] record : records) {
            writer.writeRecord(record);
        }
        writer.flush();
        writer.close();
        result.setResultsList(results);
        return result;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public List<Set> getSetList() {
        return setList;
    }

    public void setSetList(List<Set> setList) {
        this.setList = setList;
    }

    public List<Where> getWhereList() {
        return whereList;
    }

    public void setWhereList(List<Where> whereList) {
        this.whereList = whereList;
    }
}
