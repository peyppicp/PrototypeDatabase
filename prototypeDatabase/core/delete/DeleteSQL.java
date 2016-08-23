package org.prototypeDatabase.core.delete;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.prototypeDatabase.conditions.sql.From;
import org.prototypeDatabase.conditions.sql.Result;
import org.prototypeDatabase.conditions.sql.Where;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.PField;
import org.prototypeDatabase.entity.Table;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class DeleteSQL implements SQLInterface {

    private From from;
    private List<Where> whereList = new LinkedList<>();

    public DeleteSQL(List<Where> whereList) {
        this.whereList = whereList;
    }

    public DeleteSQL(From from, List<Where> whereList) {
        this.from = from;
        this.whereList = whereList;
    }

    public DeleteSQL() {
    }

    @Override
    public Result executeTable(Table table) throws IOException {
        return delete(table);
    }

    @Override
    public Result executeGlobal() throws IOException {
        return null;
    }

    public Result delete(Table table) throws IOException {
        CsvReader reader = null;
        CsvWriter writer = null;
        Result result = new Result();
        File table_file = table.getTable_file();
        LinkedList<String[]> results = new LinkedList<>();
        LinkedList<String[]> records = new LinkedList<>();
        reader = new CsvReader(new BufferedReader(new InputStreamReader(new FileInputStream(table_file), "UTF-8")), ',');
        reader.readHeaders();
        String[] headers = reader.getHeaders();
        while (reader.readRecord()) {
            int i = 0, count = 0;
            List<PField> pFields = table.getPFields();
            String[] strings = new String[pFields.size()];
            for (PField pField : pFields) {
                strings[i] = reader.get(pField.getName());
                for (Where where : whereList) {
                    if (where.getpField() == pField && strings[i].equals(where.getValue())) {
                        count++;
                    }
                }
                i++;
            }
            if (count != whereList.size()) {
                records.add(strings);
            } else {
                results.add(strings);
            }
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

    public List<Where> getWhereList() {
        return whereList;
    }

    public void setWhereList(List<Where> whereList) {
        this.whereList = whereList;
    }
}
