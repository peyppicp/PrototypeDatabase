package org.prototypeDatabase.conditions;

/**
 * Created by Peyppicp on 2016/8/21.
 */
public class PFieldConstants {

    public final static String NOT_NULL = "not_null";

    public final static String PRIMARY_KEY = "primary_key";

    public final static String FOREIGN_KEY = "foreign_key";

    public final static String UNQIUE = "unique";

    public final static String AUTOINCREAMENT = "auto_increament";

    public final static int CASCADE = 0;
    public final static int RESTRICT = 1;
    public final static int SETNULL = 2;
    public final static int SETDEFAULT = 3;
    public final static int NOACTION = 4;

    public final static String INT = "int";
    public final static String LONG = "long";
    public final static String STRING = "String";
    public final static String BOOLEAN = "boolean";

    public final static int AND = 0;
    public final static int OR = 1;
    public final static int NOT = 2;
}
