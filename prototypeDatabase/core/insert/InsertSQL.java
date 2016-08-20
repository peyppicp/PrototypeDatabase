package org.prototypeDatabase.core.insert;

import org.prototypeDatabase.conditions.ConditionsInterface;
import org.prototypeDatabase.conditions.InsertConditionsInterface;
import org.prototypeDatabase.core.SQLInterface;
import org.prototypeDatabase.values.InsertValuesInterface;
import org.prototypeDatabase.values.ValuesInterface;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class InsertSQL implements SQLInterface{

    private ConditionsInterface conditions;
    private ValuesInterface values;

    public ConditionsInterface getConditions() {
        return conditions;
    }

    public void setConditions(ConditionsInterface conditions) {
        this.conditions = conditions;
    }

    public ValuesInterface getValues() {
        return values;
    }

    public void setValues(ValuesInterface values) {
        this.values = values;
    }

    @Override
    public void execute() {

    }
}
