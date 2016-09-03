package org.prototypeDatabase.util.reflect;

import org.prototypeDatabase.conditions.PFieldConditions;
import org.prototypeDatabase.entity.PField;
import org.prototypeDatabase.exception.StringLengthIllegalException;

import java.lang.reflect.Field;

/**
 * Created by Peyppicp on 2016/8/21.
 */
public class PFieldReflecter {

    public String getValue(PFieldConditions pFieldConditions) throws IllegalAccessException {
        StringBuffer value = new StringBuffer();
        Field[] declaredFields = pFieldConditions.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            Object object = null;
            if ((object = declaredFields[i].get(pFieldConditions)) != null) {
                if (object instanceof PField) {
                    PField pField = (PField) object;
                    value.append(pField.getName()).append(".");
                } else {
                    value.append(object).append(".");
                }
            } else {
                value.append("").append(".");
            }
        }
        if (value.charAt(value.length() - 1) == '.') {
            value.deleteCharAt(value.length() - 1);
        }
        return value.toString();
    }

//    private String type;
//    private boolean isPrimary;
//    private boolean isForeign;
//    private boolean isNotNull;
//    private boolean isUnique;
//    private boolean autoIncreament;
//
//    private PField pField;
//    private PField foreign_key;
//    private int onDelete;
//    private int onUpdata;

    public void setPFieldConditions(PField pField, String[] value_spilt) throws StringLengthIllegalException {
        PFieldConditions conditions = pField.getConditions();
        conditions.setType(value_spilt[0]);
        conditions.setPrimary(Boolean.parseBoolean(value_spilt[1]));
        conditions.setForeign(Boolean.parseBoolean(value_spilt[2]));
        conditions.setNotNull(Boolean.parseBoolean(value_spilt[3]));
        conditions.setUnique(Boolean.parseBoolean(value_spilt[4]));
        conditions.setAutoIncreament(Boolean.parseBoolean(value_spilt[5]));

        conditions.setPField(pField);
//        外键
//        conditions.setForeign_key();
        conditions.setLength(Integer.parseInt(value_spilt[8]));
        conditions.setOnDelete(Integer.parseInt(value_spilt[9]));
        conditions.setOnUpdata(Integer.parseInt(value_spilt[10]));
    }
}
