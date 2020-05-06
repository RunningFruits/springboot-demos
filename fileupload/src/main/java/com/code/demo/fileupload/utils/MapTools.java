package com.code.demo.fileupload.utils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapTools {

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> getObjectToMap(Object obj)  {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                continue;
            }
            if (value == null) {
                value = "";
            }
            map.put(fieldName, value);
        }
        return map;
    }

}
