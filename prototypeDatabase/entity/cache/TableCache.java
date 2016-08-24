package org.prototypeDatabase.entity.cache;

import org.prototypeDatabase.core.SQLInterface;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Peyppicp on 2016/8/24.
 */
public class TableCache {

    private Map<SQLInterface, String[]> cache = new WeakHashMap<>();

    public void addRecord(SQLInterface sqlInterface, String[] record) {
        String[] strings = cache.get(sqlInterface);
        if (strings == null) {
            cache.put(sqlInterface, record);
        } else {
            cache.replace(sqlInterface, strings, record);
        }
    }

//    public Map<SQLInterface, String[]> getCache() {
//        return cache;
//    }

//    public void setCache(Map<SQLInterface, String[]> cache) {
//        this.cache = cache;
//    }

    public void clearCache() {
        cache.clear();
    }

    public String[] getRecord(SQLInterface key) {
        String[] strings = null;
        if ((strings = cache.get(key)) != null) {
            return strings;
        }
        return null;
    }
}
