package com.dyl.demo.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wumingming on 26/04/2017.
 */

public class Convertor {

    public static Object deserializeToMap(JsonElement in) {

        if (in.isJsonArray()) {
            List<Object> list = new ArrayList<>();
            JsonArray arr = in.getAsJsonArray();
            for (JsonElement anArr : arr) {
                list.add(deserializeToMap(anArr));
            }
            return list;
        }

        if (in.isJsonObject()) {
            Map<String, Object> map = new HashMap<>();
            JsonObject obj = in.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entitySet = obj.entrySet();
            for (Map.Entry<String, JsonElement> entry : entitySet) {
                map.put(entry.getKey(), deserializeToMap(entry.getValue()));
            }
            return map;
        }

        if (in.isJsonPrimitive()) {
            JsonPrimitive prim = in.getAsJsonPrimitive();
            if (prim.isBoolean()) {
                return prim.getAsBoolean();
            }
            if (prim.isNumber()) {
                final Number num = prim.getAsNumber();
                // here you can handle double int/long values and return any type you want
                // this solution will transform 3.0 float to long values
                return (Math.ceil(num.doubleValue()) == num.longValue())
                        ? num.longValue() : num.doubleValue();
            }
            if (prim.isString()) {
                return prim.getAsString();
            }
        }

        return null;
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        try {
            Class<? extends Object> class1 = bean.getClass();
            Field[] fields = class1.getFields();
            Map<String, Object> map = new HashMap<>();
            for (Field field : fields) {
                Object value = field.get(bean);
                Class<?> class_ = field.getType();

                if (value == null)
                    continue;
                else if (class_ == List.class) {
                    ArrayList<T> list = (ArrayList<T>) value;
                    if (list.size() == 0)
                        continue;

                    value = mapArray(list);
                } else if (value.getClass() == Integer.class) {
                    Integer intValue = (Integer) value;
                    if (intValue == -1)
                        continue;
                } else if (value.getClass() == Float.class) {
                    Float floatValue = (Float) value;
                    if (floatValue == -1)
                        continue;
                } else if (value.getClass() == String.class) {
                    String string = (String) value;
                    if (TextUtils.isEmpty(string))
                        continue;
                }
                map.put(field.getName(), value);
            }

            return map;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> ArrayList<Map<String, Object>> mapArray(ArrayList<T> list) throws IllegalAccessException {
        ArrayList<Map<String, Object>> newValue = new ArrayList<>();
        for (T obj : list) {
            Class<? extends Object> class1 = obj.getClass();
            Field[] fields = class1.getFields();
            Map<String, Object> map = new HashMap<>();

            for (Field field : fields) {
                Object value = field.get(obj);
                Class<?> class_ = field.getType();

                if (value == null)
                    continue;
                else if (class_ == List.class) {
                    ArrayList<T> listValue = (ArrayList<T>) value;
                    if (listValue.size() == 0)
                        continue;

                    value = mapArray(listValue);
                } else if (value.getClass() == Integer.class) {
                    Integer intValue = (Integer) value;
                    if (intValue == -1)
                        continue;
                } else if (value.getClass() == Float.class) {
                    Float floatValue = (Float) value;
                    if (floatValue == -1)
                        continue;
                } else if (value.getClass() == String.class) {
                    String string = (String) value;
                    if (TextUtils.isEmpty(string))
                        continue;
                }
                map.put(field.getName(), value);
            }

            newValue.add(map);
        }

        return newValue;
    }

    public static <T> T beanToBean(Object bean, Class<T> type) {
        Gson conv = new Gson();return conv.fromJson(conv.toJson(bean), type);
    }
}
