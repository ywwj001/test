package com.redrain.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redrain.common.exception.CustomerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class JSONUtils {
    private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    //遇到未知属性时不抛出JsonMappingException
    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    private JSONUtils() {

    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) {
        String result = "";
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            logger.error("obj2json error: {}", ex);
        }
        return result;
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            logger.error("json2pojo failed, className: ", clazz.getName(), e);
            throw new CustomerException();
        }
    }

    /**
     * json string convert to map
     */
    public static <T> Map<String, Object> json2map(String jsonStr) {
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isEmpty(jsonStr)) {
            return result;
        }
        try {
            result = objectMapper.readValue(jsonStr, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
        });
        Map<String, T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (!StringUtils.isEmpty(jsonArrayStr)) {
            try {
                List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
                });
                for (Map<String, Object> map : list) {
                    result.add(map2pojo(map, clazz));
                }
            } catch (Exception ex) {
                logger.error("json2list failed, className: ", clazz.getClass().getName(), ex);
                throw new CustomerException(ex.getMessage());
            }
        }
        return result;
    }

    /**
     * json array string convert to arraylist
     */
    public static <T> List<T> json2ArrayList(String jsonArrayStr, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        try {
            if (StringUtils.hasText(jsonArrayStr)) {
                result = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
                });
            }
        } catch (Exception ex) {
            logger.error("json2ArrayList failed, className: ", clazz.getClass().getName(), ex);
            throw new CustomerException(ex.getMessage());
        }
        return result;
    }

    /**
     * map convert to javaBean
     */
    @SuppressWarnings("unchecked")
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        if (Objects.isNull(map)) {
            return null;
        }
        return objectMapper.convertValue(map, clazz);
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        Object object = null;
        if (!Objects.isNull(map)) {
            try {
                object = beanClass.getDeclaredConstructor().newInstance();
                Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    int modifiers = field.getModifiers();
                    if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                        continue;
                    }
                    field.setAccessible(true);
                    field.set(object, map.get(field.getName()));
                }
            } catch (Exception ex) {
                logger.error("mapToObject failed, className: ", beanClass.getClass().getName(), ex);
                throw new CustomerException(ex.getMessage());
            }
        }
        return object;
    }

    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                logger.error("objectToMap failed, className: ", obj.getClass().getName(), ", field:", field.getName(), e);
                throw new CustomerException(e.getMessage());
            }
        }
        return map;
    }

    /**
     * map convert to javaBean
     */
    @SuppressWarnings("unchecked")
    public static <T> T obj2pojo(Object object, Class<T> clazz) {
        if (object == null) {
            return null;
        }
        if (object instanceof Map) {
            return objectMapper.convertValue(object, clazz);
        } else if (object instanceof String) {
            return json2pojo((String) object, clazz);
        }
        return json2pojo(obj2json(object), clazz);
    }

}
