package org.prototypeDatabase.conditions.sql;

import org.prototypeDatabase.entity.Table;

/**
 * Created by Peyppicp on 2016/8/22.
 */
public class Into {

    private Table table;

    public Into(Table table) {
        this.table = table;
    }

    public Into() {
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
