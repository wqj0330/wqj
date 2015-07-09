package me.anchora.inpaasmgr.utils;

import java.io.StringWriter;

import static org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JacksonUtils {
    /**
     * Converts the object to a JSON string
     */
    public static String toJSON(Object value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            StringWriter writer = new StringWriter();
            mapper.writeValue(writer, value);
            return writer.toString();
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Converts the JSON string to an Object (either Map or List)
     */
    public static Object fromJSON(String value) {
        return fromJSON(value, Object.class);
    }

    /**
     * Converts the JSON string to a typed object via a TypeReference The main
     * complication is handling of Generic types: if they are used, one has to
     * use TypeReference object, to work around Java Type Erasure.
     * 
     * ex: return JSONUtils.fromJSON(this.answersJson, new
     * TypeReference<List<StanzaAnswer>>(){});
     */
    public static <T> T fromJSON(String value, TypeReference<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(value, type);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Converts the JSON string to a typed object (or Map/List if Object.class
     * is passed in)
     */
    public static <T> T fromJSON(String value, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(value, type);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}