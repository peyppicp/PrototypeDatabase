package org.prototypeDatabase.core.select;

import com.csvreader.CsvReader;
import org.prototypeDatabase.conditions.sql.*;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.PField;
import org.prototypeDatabase.entity.Table;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class SelectSQL implements SQLInterface {

    private Select select;
    private From from;
    private Where where;
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

    public SelectSQL(Select select, From from, Where where) {
        this.select = select;
        this.from = from;
        this.where = where;
    }

    public SelectSQL(Select select, From from, Where where, GroupBy groupBy) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.groupBy = groupBy;
    }

    public SelectSQL(Select select, From from, Where where, GroupBy groupBy, OrderBy orderBy) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.groupBy = groupBy;
        this.orderBy = orderBy;
    }

    public SelectSQL(Select select, From from, Where where, GroupBy groupBy, OrderBy orderBy, Having having) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.groupBy = groupBy;
        this.orderBy = orderBy;
        this.having = having;
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

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
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

    @Override
    public Result executeTable(Table table) throws IOException {
        if (where == null) {
            Result result = selectFrom(table);
            return result;
        }
        if (groupBy == null) {

        }
        return null;
    }

    @Override
    public Result executeGlobal() throws IOException {
        return null;
    }

    public Result selectFrom(Table table) throws IOException {
        CsvReader reader = null;
        Result result = new Result();
        LinkedList<String[]> strings = new LinkedList<>();
        File table_file = table.getTable_file();
        reader = new CsvReader(new BufferedReader(new InputStreamReader(new FileInputStream(table_file), "UTF-8")), ',');
        reader.readHeaders();
        while (reader.readRecord()) {
            PField[] fields = select.getFields();
            String[] record = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                record[i] = reader.get(fields[i].getName());
            }
            strings.add(record);
        }
        result.setResultsList(strings);
        return result;
    }
}
