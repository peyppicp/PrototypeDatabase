package org.prototypeDatabase.conditions;

import org.prototypeDatabase.entity.PField;

/**
 * Created by Peyppicp on 2016/8/21.
 */
public class PFieldConditions {

    private String isPrimary;
    private String isForeign;
    private String isNotNull;
    private String isUnique;
    private String autoIncreament;

    private PField pField;
    private PField foreign_key;
    private int onDelete;
    private int onUpdata;

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(String isForeign) {
        this.isForeign = isForeign;
    }

    public String getIsNotNull() {
        return isNotNull;
    }

    public void setIsNotNull(String isNotNull) {
        this.isNotNull = isNotNull;
    }

    public String getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(String isUnique) {
        this.isUnique = isUnique;
    }

    public String getAutoIncreament() {
        return autoIncreament;
    }

    public void setAutoIncreament(String autoIncreament) {
        this.autoIncreament = autoIncreament;
    }

    public PField getPField() {
        return pField;
    }

    public void setPField(PField pField) {
        this.pField = pField;
    }

    public PField getForeign_key() {
        return foreign_key;
    }

    public void setForeign_key(PField foreign_key) {
        this.foreign_key = foreign_key;
    }

    public int getOnDelete() {
        return onDelete;
    }

    public void setOnDelete(int onDelete) {
        this.onDelete = onDelete;
    }

    public int getOnUpdata() {
        return onUpdata;
    }

    public void setOnUpdata(int onUpdata) {
        this.onUpdata = onUpdata;
    }
}
