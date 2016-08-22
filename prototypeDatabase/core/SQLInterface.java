package org.prototypeDatabase.core;

import org.prototypeDatabase.conditions.sql.Result;
import org.prototypeDatabase.entity.Table;
import org.prototypeDatabase.exception.OperationNotIllegalException;

import java.io.IOException;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public interface SQLInterface {

    public Result executeTable(Table table) throws IOException, OperationNotIllegalException;

    public Result executeGlobal() throws IOException;
}
