package com.github.steppenwells.client.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SolrSearchResult implements Serializable {
    private Map<String, Object> fieldValueMap = new HashMap<String, Object>();

    public SolrSearchResult() {
    }

    public Map<String, Object> getFieldValueMap() {
        return fieldValueMap;
    }

    public void setFieldValueMap(Map<String, Object> fieldValueMap) {
        this.fieldValueMap = fieldValueMap;
    }

    public void put(String fieldName, Object value) {
        fieldValueMap.put(fieldName, value);
    }

    @Override
    public String toString() {

        return fieldValueMap.toString();
    }
}
