package com.code.demo.backend.properties;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class QueryConditionProperties {
    String split = "_";
    String range = "range";
    String lessThan = "lt";
    String lessEqual = "le";
    String moreThan = "mt";
    String moreEqual = "me";
    String like = "like";
    String in = "in";
    String isNull = "null";
    String notEqual = "notEqual";


    public Map<String, String> getConditionMap() {
        Map<String, String> conditionMap = new HashMap();
        conditionMap.put("split", "_");
        conditionMap.put(getRange(), "range");
        conditionMap.put(getLessThan(), "lessThan");
        conditionMap.put(getLessEqual(), "lessEqual");
        conditionMap.put(getMoreThan(), "moreThan");
        conditionMap.put(getMoreEqual(), "moreEqual");
        conditionMap.put(getLike(), "like");
        conditionMap.put(getIn(), "in");
        conditionMap.put(getIsNull(), "null");
        conditionMap.put(getNotEqual(), "notEqual");
        return conditionMap;
    }
}
