package org.prototypeDatabase.conditions;

import org.prototypeDatabase.entity.PField;

/**
 * Created by Peyppicp on 2016/8/21.
 */
public class PFieldConditions {

    private String type;
    private boolean isPrimary;
    private boolean isForeign;
    private boolean isNotNull;
    private boolean isUnique;
    private boolean autoIncreament;

    private PField pField;
    private PField foreign_key;
    private int onDelete;
    private int onUpdata;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean flag) {
        if (flag) {
            this.isPrimary = PFieldConstants.PRIMARY_KEY;
        }
    }

    public boolean getForeign() {
        return isForeign;
    }

    public void setForeign(boolean flag) {
        if (flag) {
            this.isForeign = PFieldConstants.FOREIGN_KEY;
        }
    }

    public boolean getNotNull() {
        return isNotNull;
    }

    public void setNotNull(boolean flag) {
        if (flag) {
            this.isNotNull = PFieldConstants.NOT_NULL;
        }
    }

    public boolean getUnique() {
        return isUnique;
    }

    public void setUnique(boolean flag) {
        if (flag) {
            this.isUnique = PFieldConstants.UNQIUE;
        }
    }

    public boolean getAutoIncreament() {
        return autoIncreament;
    }

    public void setAutoIncreament(boolean flag) {
        if (flag) {
            this.autoIncreament = PFieldConstants.AUTOINCREAMENT;
        }
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
