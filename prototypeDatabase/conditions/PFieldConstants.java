package org.prototypeDatabase.conditions;

/**
 * Created by Peyppicp on 2016/8/21.
 */
public class PFieldConstants {

    public final static boolean NOT_NULL = true;

    public final static boolean PRIMARY_KEY = true;

    public final static boolean FOREIGN_KEY = true;

    public final static boolean UNQIUE = true;

    public final static boolean AUTOINCREAMENT = true;

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
