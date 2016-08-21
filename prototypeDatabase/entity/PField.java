package org.prototypeDatabase.entity;

import org.prototypeDatabase.conditions.PFieldConditions;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class PField {

    private String name;
    //    private String type;
    private PFieldConditions conditions;
    private Table table; //table在创建PField的时候将自身作为参数传入

    public Table getTable() {
        return table;
    }

//    public String getType() {
//        return type;
////    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PFieldConditions getConditions() {
        return conditions;
    }

    public void setConditions(PFieldConditions conditions) {
        this.conditions = conditions;
    }
}
