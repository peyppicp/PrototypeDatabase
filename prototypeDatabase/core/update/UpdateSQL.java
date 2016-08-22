package org.prototypeDatabase.core.update;

import org.prototypeDatabase.conditions.sql.From;
import org.prototypeDatabase.conditions.sql.Result;
import org.prototypeDatabase.conditions.sql.Set;
import org.prototypeDatabase.conditions.sql.Where;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.entity.Table;

import java.io.IOException;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class UpdateSQL implements SQLInterface {

    private From from;
    private Set set;
    private Where where;

    public UpdateSQL() {
    }

    public UpdateSQL(From from, Set set, Where where) {
        this.from = from;
        this.set = set;
        this.where = where;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
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
