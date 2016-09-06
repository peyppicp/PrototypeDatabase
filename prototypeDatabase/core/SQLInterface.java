package org.prototypeDatabase.core;

import org.dom4j.DocumentException;
import org.prototypeDatabase.conditions.sql.Result;
import org.prototypeDatabase.entity.Table;
import org.prototypeDatabase.exception.OperationNotIllegalException;
import org.prototypeDatabase.exception.PFieldNotFoundException;
import org.prototypeDatabase.exception.WhereRelationIllegalException;

import java.io.IOException;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public interface SQLInterface {

    Result executeTable(Table table) throws IOException, OperationNotIllegalException, WhereRelationIllegalException, PFieldNotFoundException, DocumentException;

    Result executeGlobal() throws IOException;
}
