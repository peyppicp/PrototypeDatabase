package org.prototypeDatabase.core;

import org.dom4j.DocumentException;
import org.prototypeDatabase.conditions.sql.Result;
import org.prototypeDatabase.entity.Table;
import org.prototypeDatabase.exception.OperationNotIllegalException;
import org.prototypeDatabase.exception.PFieldNotFoundException;
import org.prototypeDatabase.exception.WhereRelationIllegalException;

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

    public Result executeTable(SQLInterface sqlInterface) throws IOException, OperationNotIllegalException, WhereRelationIllegalException, DocumentException, PFieldNotFoundException {
        return sqlInterface.executeTable(table);
    }

    public void executeGlobal(SQLInterface sqlInterface) throws IOException {
        sqlInterface.executeGlobal();
    }
}
