package org.prototypeDatabase.core.insert;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.prototypeDatabase.conditions.PFieldConditions;
import org.prototypeDatabase.conditions.PFieldConstants;
import org.prototypeDatabase.conditions.sql.Into;
import org.prototypeDatabase.conditions.sql.Result;
import org.prototypeDatabase.conditions.sql.Values;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.PField;
import org.prototypeDatabase.entity.Table;
import org.prototypeDatabase.entity.cache.TableCache;
import org.prototypeDatabase.exception.OperationNotIllegalException;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class InsertSQL implements SQLInterface {

    private Into into;
    private Values values;

    public InsertSQL() {
    }

    public InsertSQL(Values values) {
        this.values = values;
    }

    public InsertSQL(Into into, Values values) {
        this.into = into;
        this.values = values;
    }

    public Into getInto() {
        return into;
    }

    public void setInto(Into into) {
        this.into = into;
    }

    public Values getValues() {
        return values;
    }

    public void setValues(Values values) {
        this.values = values;
    }

    @Override
    public Result executeTable(Table table) throws IOException, OperationNotIllegalException, DocumentException {
        return executeXML(table);
    }

    public Result executeXML(Table table) throws DocumentException, IOException, OperationNotIllegalException {
        if (into == null) {
            String[] values = this.values.getValues();
            List<PField> pFields = table.getPFields();
            //插入数量判断
            if (values.length != pFields.size()) {
                throw new OperationNotIllegalException("Numbers of values need to be equals to pfields!");
            }

            File xml_file = table.getXml_file();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(xml_file);
            Element rootElement = document.getRootElement();
            int i = 0, count = 0;

//            List<Element> record1 = rootElement.elements("record");
            //遍历数据表，检测是否有重复内容
            Iterator<Element> records = rootElement.elementIterator("record");
            while (records.hasNext()) {
                Element element = records.next();
                for (PField pField : pFields) {
                    PFieldConditions conditions = pField.getConditions();
                    //检查PField相等
                    if (PFieldConstants.UNQIUE == conditions.getUnique() || PFieldConstants.PRIMARY_KEY == conditions.getPrimary()) {
                        Iterator<Element> element_values = element.elementIterator();
                        while (element_values.hasNext()) {
                            Element value = element_values.next();
                            //检查值相等
                            if (value.attributeValue("name").equals(pField.getName())) {
                                String textTrim = value.getText();
                                if (textTrim.equals(values[i])) {
                                    count++;
                                }
                            }
                            i++;
                        }
                        i = 0;
                    }
                }
            }
            if (count == 0) {
                Element record = rootElement.addElement("record");
                for (PField pField : pFields) {
                    Element value = record.addElement("value");
                    value.addAttribute("name", pField.getName());
                    value.setText(values[i]);
                    i++;
                }
            } else {
                throw new OperationNotIllegalException("Field has unique or primary key modifiers");
            }

            XMLWriter xmlWriter = new XMLWriter(new BufferedWriter(new FileWriter(xml_file)), OutputFormat.createCompactFormat());
            xmlWriter.write(document);
            xmlWriter.close();

            //收尾
            TableCache tableCache = table.getTableCache();
            Result result = new Result();
            List<String[]> resultsList = new LinkedList<>();
            tableCache.addRecord(this, values);
            resultsList.add(values);
            result.setResultsList(resultsList);
            return result;
        }
        return null;
    }

    @Override
    public Result executeGlobal() throws IOException {
        return null;
    }

    @Deprecated
    public Result insertCSV(Table table) throws IOException, OperationNotIllegalException {
        if (into == null) {
            TableCache tableCache = table.getTableCache();
            CsvReader reader = new CsvReader(new BufferedReader(new InputStreamReader(new FileInputStream(table.getTable_file()), "UTF-8")), ',');
            reader.readHeaders();
            String[] values = this.values.getValues();
            int count = 0;
            while (reader.readRecord()) {
                List<PField> pFields = table.getPFields();
                int i = 0;
                for (PField pField : pFields) {
                    PFieldConditions conditions = pField.getConditions();
                    //primary key&unqiue
                    String record = reader.get(pField.getName());
                    if (PFieldConstants.PRIMARY_KEY == conditions.getPrimary() || PFieldConstants.UNQIUE == conditions.getUnique()) {
                        if (record.equals(values[i])) {
                            count++;
                        }
                        i++;
                    }
                }
            }
            if (count != 0) {
                throw new OperationNotIllegalException("Field has unique or primary key modifiers");
            }
            reader.close();
            writeTo(table, values);
            tableCache.addRecord(this, values);
        } else {
            throw new OperationNotIllegalException("You are supposed to handle this table in createTable function!");
        }
        return null;
    }

    @Deprecated
    private void writeTo(Table table, String[] values) throws IOException {
        CsvWriter writer = null;
        File table_file = table.getTable_file();
        writer = new CsvWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(table_file, true), "UTF-8")), ',');
        writer.flush();
        writer.close();
    }
}
