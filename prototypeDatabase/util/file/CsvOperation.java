package org.prototypeDatabase.util.file;

import com.csvreader.CsvWriter;
import org.prototypeDatabase.entity.Table;

import java.io.*;

/**
 * Created by Peyppicp on 2016/8/22.
 */
public class CsvOperation {

    public static void writeTo(Table table, String[] values) throws IOException {
        CsvWriter writer = null;
        File table_file = table.getTable_file();
        writer = new CsvWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(table_file, true), "UTF-8")), ',');
        writer.writeRecord(values);
        writer.flush();
        writer.close();
    }

//    public static Result readFrom(Table table) throws IOException {
//        CsvReader reader = null;
//        Result result = new Result();
//        LinkedList<String[]> strings = new LinkedList<>();
//        File table_file = table.getTable_file();
//        reader = new CsvReader(new BufferedReader(new InputStreamReader(new FileInputStream(table_file), "UTF-8")), ',');
//        reader.readHeaders();
//        while (reader.readRecord()){
//            String rawRecord = reader.getRawRecord();
//            strings.add(rawRecord.split(","));
//        }
//        result.setResultsList(strings);
//        return result;
//    }
}
