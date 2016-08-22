package org.prototypeDatabase.core.insert;

import org.prototypeDatabase.conditions.sql.Into;
import org.prototypeDatabase.conditions.sql.Result;
import org.prototypeDatabase.conditions.sql.Values;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.Table;
import org.prototypeDatabase.exception.OperationNotIllegalException;
import org.prototypeDatabase.util.file.CsvOperation;

import java.io.IOException;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class InsertSQL implements SQLInterface{

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

    @Override
    public Result executeTable(Table table) throws IOException, OperationNotIllegalException {
        if (into == null) {
            CsvOperation.writeTo(table, values.getValues());
        } else {
            throw new OperationNotIllegalException();
        }
        return null;
    }

    @Override
    public Result executeGlobal() throws IOException {
        return null;
    }
}
