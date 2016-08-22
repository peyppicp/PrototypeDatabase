package org.prototypeDatabase.conditions.sql;

import org.prototypeDatabase.entity.PField;

/**
 * Created by Peyppicp on 2016/8/22.
 */
public class Select {

    private PField[] fields;

    public Select(PField[] fields) {
        this.fields = fields;
    }

    public PField[] getFields() {
        return fields;
    }

    public void setFields(PField[] fields) {
        this.fields = fields;
    }
}
