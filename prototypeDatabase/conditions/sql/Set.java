package org.prototypeDatabase.conditions.sql;

import org.prototypeDatabase.entity.PField;

/**
 * Created by Peyppicp on 2016/8/22.
 */
public class Set {

    private PField pField;
    private String value;

    public Set() {
    }

    public Set(PField pField, String value) {
        this.pField = pField;
        this.value = value;
    }

    public PField getpField() {
        return pField;
    }

    public void setpField(PField pField) {
        this.pField = pField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
