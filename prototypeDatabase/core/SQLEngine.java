package org.prototypeDatabase.core;

import org.prototypeDatabase.entity.Table;
import org.prototypeDatabase.exception.OperationNotIllegalException;

import java.io.IOException;

/**
 * Created by Peyppicp on 2016/8/22.
 */
public class SQLEngine {

    private Table table;

    public SQLEngine(Table table) {
        this.table = table;
    }

    public SQLEngine() {
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void executeTable(SQLInterface sqlInterface) throws IOException, OperationNotIllegalException {
        sqlInterface.executeTable(table);
    }
}
