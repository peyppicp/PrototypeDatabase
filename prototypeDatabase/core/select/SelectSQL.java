package org.prototypeDatabase.core.select;

import org.prototypeDatabase.conditions.sql.*;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.Table;

import java.io.IOException;

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
    public void executeTable(Table table) throws IOException {

    }

    @Override
    public void executeGlobal() throws IOException {

    }
}
