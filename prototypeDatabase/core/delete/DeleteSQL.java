package org.prototypeDatabase.core.delete;

import org.prototypeDatabase.conditions.sql.From;
import org.prototypeDatabase.conditions.sql.Result;
import org.prototypeDatabase.conditions.sql.Where;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.Table;

import java.io.IOException;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class DeleteSQL implements SQLInterface {

    private From from;
    private Where where;

    public DeleteSQL(From from, Where where) {
        this.from = from;
        this.where = where;
    }

    public DeleteSQL() {
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

    @Override
    public Result executeTable(Table table) throws IOException {
        return null;
    }

    @Override
    public Result executeGlobal() throws IOException {
        return null;
    }
}
