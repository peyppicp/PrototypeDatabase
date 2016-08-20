package org.prototypeDatabase.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Peyppicp on 2016/8/20.
 */
public class Spilter {

    public static String[] spiltBy(String string, char c) {
        int mark = 0;
        List<String> result = new ArrayList<>();
        for(int i=0;i<string.length();i++){
            if(string.charAt(i)==c){
                result.add(string.substring(mark,i));
//                string.substring(mark,i);
                mark = i+1;
            }
//            if(i == string.length()-1){
////                result.add(string.substring(mark,i+1));
//                string.substring(mark,i+1);
//            }
        }
//        ;
        result.add(string.substring(mark,string.length()));
//        return result.toArray(new String[result.size()]);
        return result.toArray(new String[result.size()]);
    }

    public static String[] test(String str,String str1){
        StringTokenizer stringTokenizer = new StringTokenizer(str, str1);
//        List<String> result = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()){
//            result.add(stringTokenizer.nextToken());
            stringTokenizer.nextToken();
        }
//        return result.toArray(new String[result.size()]);
        return null;
    }
}
