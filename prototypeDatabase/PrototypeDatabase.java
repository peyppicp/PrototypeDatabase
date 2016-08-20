package org.prototypeDatabase;

import com.csvreader.CsvReader;
import org.junit.Test;
import org.prototypeDatabase.conditions.PFieldConditions;
import org.prototypeDatabase.conditions.PFieldConstants;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.Database;
import org.prototypeDatabase.entity.PField;
import org.prototypeDatabase.entity.Table;
import org.prototypeDatabase.exception.DatabaseNotFoundException;
import org.prototypeDatabase.exception.DropDatabaseFaildException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;


/**
 * Created by Peyppicp on 2016/8/20.
 */
public class PrototypeDatabase {

    private List<Database> databases = new LinkedList<>();
    private String base_path;
    private File base_file;

    public PrototypeDatabase() throws IOException {
        doInit();
    }

    public Database createDataBase(String name) {
        //在base_file下面建立文件夹作为数据库
        File file = new File(base_file, name);
        file.mkdir();
        Database database = new Database();
        database.setDatabase_file(file);
        database.setName(name);
        databases.add(database);
        return database;
    }

    protected void addDatabase(Database database){
        if(!databases.contains(database)){
            databases.add(database);
        }
    }

    protected void removeDatabase(Database database) throws DatabaseNotFoundException {
        if(databases.contains(database)){
            databases.remove(database);
        }else {
            throw new DatabaseNotFoundException();
        }
    }

    public void dropDatabase(Database database) throws DatabaseNotFoundException, DropDatabaseFaildException {
        File database_file = database.getDatabase_file();
        if(database_file.exists()){
            File[] files = database_file.listFiles();
            for(File file:files){
                if (file.isDirectory()){
                    throw new DropDatabaseFaildException("请勿在数据库路径下包含其他文件夹！");
                }else{
                    file.delete();
                }
            }
            database_file.delete();
        }else{
            throw new DatabaseNotFoundException();
        }
        removeDatabase(database);
    }

    public Database getDatabaseByName(String name) throws DatabaseNotFoundException {
        for (Database database : databases) {
            if (database.getName().equals(name)) {
                return database;
            }
        }
        throw new DatabaseNotFoundException();
    }

    public void doInit() throws IOException {
        Properties properties = new Properties();
        String properties_path = System.getProperty("user.dir") + "\\src\\PDatabase.properties";
        properties.load(new BufferedInputStream(new FileInputStream(properties_path)));
        this.base_path = properties.getProperty("path");
        this.base_file = new File(base_path);

        File[] database_files = this.base_file.listFiles();
        if (database_files.length != 0 || database_files != null) {
            //设置数据库
            for (int i = 0; i < database_files.length; i++) {
                File database_file = database_files[i];
                if (database_file.isDirectory()) {
                    Database database = new Database();
                    File[] table_files = database_file.listFiles();
                    if (table_files.length != 0 || table_files != null) {
                        //设置表
                        for (int j = 0; j < table_files.length; j++) {
                            File table_file = table_files[j];
                            if (table_file.isFile()) {
                                Table table = new Table();
//                                CsvReader csvReader = new CsvReader(new BufferedInputStream(new FileInputStream(table_file)), Charset.forName("UTF-8"));
                                CsvReader csvReader = new CsvReader(new BufferedReader(new InputStreamReader(new FileInputStream(table_file), "UTF-8")), ',');
                                csvReader.readHeaders();
                                String[] headers = csvReader.getHeaders();
                                //设置表的字段
                                for (int k = 0; k < headers.length; k++) {
                                    String[] split = headers[k].split("&");
                                    PField pField = new PField();
                                    pField.setName(split[0]);
                                    pField.setType(split[1]);
                                    PFieldConditions pFieldConditions = new PFieldConditions();
                                    if (split.length >= 3 && split[2].equalsIgnoreCase("primary key")) {
                                        pFieldConditions.setIsPrimary(PFieldConstants.PRIMARY_KEY);
                                    }
                                    if (split.length >= 4 && split[3].equalsIgnoreCase("not null")) {
                                        pFieldConditions.setIsNotNull(PFieldConstants.NOT_NULL);
                                    }
                                    if (split.length >= 5 && split[4].equalsIgnoreCase("unique")) {
                                        pFieldConditions.setIsUnique(PFieldConstants.UNQIUE);
                                    }
                                    pField.setConditions(pFieldConditions);
                                    pFieldConditions.setPField(pField);
                                    pField.setTable(table);
                                    table.addPFields(pField);
                                }
                                String file_name = table_file.getName();
                                table.setName(file_name.substring(0,file_name.length()-4));
                                table.setTable_file(table_file);
                                table.setDatabase(database);
                                database.addTables(table);
                                csvReader.close();
                            }
                        }
                    }
                    database.setDatabase_file(database_file);
                    database.setName(database_file.getName());
                    database.setPrototypeDatabase(this);
                    databases.add(database);
                }
            }
        }
    }
}
