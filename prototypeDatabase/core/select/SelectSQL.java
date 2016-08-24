package org.prototypeDatabase.core.select;

import com.csvreader.CsvReader;
import org.prototypeDatabase.conditions.PFieldConstants;
import org.prototypeDatabase.conditions.sql.*;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.PField;
import org.prototypeDatabase.entity.Table;
import org.prototypeDatabase.entity.cache.TableCache;
import org.prototypeDatabase.exception.WhereRelationIllegalException;
import org.prototypeDatabase.exception.WhereStatementNotFoundException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class SelectSQL implements SQLInterface {

    private Select select;
    private From from;
    private List<Where> whereList = new LinkedList<>();
    private int where_relation;
    private GroupBy groupBy;
    private OrderBy orderBy;
    private Having having;

    public SelectSQL() {
    }

    public SelectSQL(Select select) {
        this.select = select;
    }

    public SelectSQL(Select select, From from) {
        this.select = select;
        this.from = from;
    }

    public SelectSQL(Select select, From from, List<Where> whereList, int where_relation) {
        this.select = select;
        this.from = from;
        this.whereList = whereList;
        this.where_relation = where_relation;
    }

    @Override
    public Result executeTable(Table table) throws IOException, WhereRelationIllegalException {
        if (whereList.size() == 0 || whereList.isEmpty()) {
            return selectFrom(table);
        }
        if (groupBy == null) {
            return selectFrom(table, where_relation);
        }
        return null;
    }

    @Override
    public Result executeGlobal() throws IOException {
        return null;
    }

    private Result selectFrom(Table table, int symbol) throws IOException, WhereRelationIllegalException {
        if (symbol != PFieldConstants.AND && symbol != PFieldConstants.OR && symbol != PFieldConstants.NOT) {
            throw new WhereRelationIllegalException(symbol + "is illegal here");
        }
        TableCache tableCache = table.getTableCache();
        String[] cache_record = tableCache.getRecord(this);
        Result result = new Result();
        LinkedList<String[]> records = new LinkedList<>();
        if (cache_record != null) {
            records.add(cache_record);
            result.setResultsList(records);
            return result;
        }
        CsvReader reader = null;
        File table_file = table.getTable_file();
        int where_count = whereList.size();
        reader = new CsvReader(new BufferedReader(new InputStreamReader(new FileInputStream(table_file), "UTF-8")), ',');
        reader.readHeaders();
        //读取记录
        while (reader.readRecord()) {
            PField[] fields = select.getFields();
            String[] record = new String[fields.length];
            //设置计数器，用以记录匹配where的字段
            int count = 0;
            for (int i = 0; i < fields.length; i++) {
                record[i] = reader.get(fields[i].getName());
                //遍历where语句
                for (Where where : whereList) {
                    //AND连接符
                    if (symbol == PFieldConstants.AND) {
                        if (fields[i] == where.getpField() && record[i].equals(where.getValue())) {
                            //如果字段与where的字段相同且值也相同，说明record中有一个字段匹配成功
                            //计数器自增
                            count++;
                            //当计数器的值与where的值相同时，证明已找到匹配的结果
                            if (count == where_count) {
                                records.add(record);
                                tableCache.addRecord(this, record);
                            }
                        }
                        //OR连接符
                    } else if (symbol == PFieldConstants.OR) {
                        if (fields[i] == where.getpField() && record[i].equals(where.getValue())) {
                            if (!records.contains(record)) {
                                records.add(record);
                                tableCache.addRecord(this, record);
                            }
                        }
                        //NOT连接符
                    } else if (symbol == PFieldConstants.NOT) {

                    }
                }
            }
        }
        result.setResultsList(records);
        reader.close();
        return result;
    }

    private Result selectFrom(Table table) throws IOException {
        TableCache tableCache = table.getTableCache();
        CsvReader reader = null;
        Result result = new Result();
        LinkedList<String[]> results = new LinkedList<>();
        File table_file = table.getTable_file();
        reader = new CsvReader(new BufferedReader(new InputStreamReader(new FileInputStream(table_file), "UTF-8")), ',');
        reader.readHeaders();
        while (reader.readRecord()) {
            PField[] fields = select.getFields();
            String[] record = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                record[i] = reader.get(fields[i].getName());
            }
            tableCache.addRecord(this, record);
            results.add(record);
        }
        result.setResultsList(results);
        reader.close();
        return result;
    }

    public Select getSelect() {
        return select;
    }

    public void setSelect(Select select) {
        this.select = select;
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

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public Having getHaving() {
        return having;
    }

    public void setHaving(Having having) {
        this.having = having;
    }

    public int getWhere_relation() {
        return where_relation;
    }

    public void setWhere_relation(int where_relation) {
        this.where_relation = where_relation;
    }

    public void addWhere(Where where) {
        if (!whereList.contains(where)) {
            whereList.add(where);
        }
    }

    public void removeWhere(Where where) throws WhereStatementNotFoundException {
        if (whereList.contains(where)) {
            whereList.remove(where);
        } else {
            throw new WhereStatementNotFoundException();
        }
    }
}
