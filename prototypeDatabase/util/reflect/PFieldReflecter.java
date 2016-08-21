package org.prototypeDatabase.util.reflect;

import org.prototypeDatabase.conditions.PFieldConditions;
import org.prototypeDatabase.entity.PField;

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
//    private String isPrimary;
//    private String isForeign;
//    private String isNotNull;
//    private String isUnique;
//    private String autoIncreament;
//
//    private PField pField;
//    private PField foreign_key;
//    private int onDelete;
//    private int onUpdata;

    public void setPFieldConditions(PField pField, String[] value_spilt) {
        PFieldConditions conditions = pField.getConditions();
        conditions.setType(value_spilt[0]);
        conditions.setIsPrimary(value_spilt[1]);
        conditions.setIsForeign(value_spilt[2]);
        conditions.setIsNotNull(value_spilt[3]);
        conditions.setIsUnique(value_spilt[4]);
        conditions.setAutoIncreament(value_spilt[5]);

        conditions.setPField(pField);
        conditions.setOnDelete(Integer.parseInt(value_spilt[8]));
        conditions.setOnUpdata(Integer.parseInt(value_spilt[9]));
    }
}
