package io.ylab.task03.datedmap;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DatedMapImpl implements DatedMap {
    private Map<String, String> valueMap = new HashMap<>();
    private Map<String, Date> dateMap = new HashMap<>();

    @Override
    public void put(String key, String value) {
        this.valueMap.put(key, value);
        this.dateMap.put(key, new Date());
    }

    @Override
    public String get(String key) {
        return this.valueMap.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return this.valueMap.containsKey(key);
    }

    @Override
    public void remove(String key) {
        this.valueMap.remove(key);
        this.dateMap.remove(key);
    }

    @Override
    public Set<String> keySet() {
        return this.valueMap.keySet();
    }

    @Override
    public Date getKeyLastInsertionDate(String key) {
        return this.dateMap.get(key);
    }
}
