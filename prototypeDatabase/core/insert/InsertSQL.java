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

    public void executeXML(Table table) throws DocumentException, IOException {
        if (into == null) {
            File xml_file = table.getXml_file();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(xml_file);
            Element rootElement = document.getRootElement();
            Element record = rootElement.addElement("record");
            String[] values = this.values.getValues();
            List<PField> pFields = table.getPFields();
            int i = 0;
            for (PField pField : pFields) {
                Element value = record.addElement("value");
                value.addAttribute("name", pField.getName());
                value.setText(values[i]);
                i++;
            }

            XMLWriter xmlWriter = new XMLWriter(new BufferedWriter(new FileWriter(xml_file)), OutputFormat.createCompactFormat());
            xmlWriter.write(document);
            xmlWriter.close();
        }
    }

    @Override
    public Result executeTable(Table table) throws IOException, OperationNotIllegalException {
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

        try {
            executeXML(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Result executeGlobal() throws IOException {
        return null;
    }

    public void writeTo(Table table, String[] values) throws IOException {
        CsvWriter writer = null;
        File table_file = table.getTable_file();
        writer = new CsvWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(table_file, true), "UTF-8")), ',');
        writer.flush();
        writer.close();
    }
}
