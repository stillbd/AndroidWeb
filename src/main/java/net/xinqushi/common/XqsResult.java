package net.xinqushi.common;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 娣樻窐鍟嗗煄鑷畾涔夊搷搴旂粨鏋�
 */
public class XqsResult {

    // 瀹氫箟jackson瀵硅薄
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 鍝嶅簲涓氬姟鐘舵��
    private Integer status;

    // 鍝嶅簲娑堟伅
    private String msg;

    // 鍝嶅簲涓殑鏁版嵁
    private Object data;

    public static XqsResult build(Integer status, String msg, Object data) {
        return new XqsResult(status, msg, data);
    }

    public static XqsResult ok(Object data) {
        return new XqsResult(data);
    }

    public static XqsResult ok() {
        return new XqsResult(null);
    }

    public XqsResult() {

    }

    public static XqsResult build(Integer status, String msg) {
        return new XqsResult(status, msg, null);
    }

    public XqsResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public XqsResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 灏唈son缁撴灉闆嗚浆鍖栦负TaotaoResult瀵硅薄
     * 
     * @param jsonData json鏁版嵁
     * @param clazz TaotaoResult涓殑object绫诲瀷
     * @return
     */
    public static XqsResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, XqsResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 娌℃湁object瀵硅薄鐨勮浆鍖�
     * 
     * @param json
     * @return
     */
    public static XqsResult format(String json) {
        try {
            return MAPPER.readValue(json, XqsResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object鏄泦鍚堣浆鍖�
     * 
     * @param jsonData json鏁版嵁
     * @param clazz 闆嗗悎涓殑绫诲瀷
     * @return
     */
    public static XqsResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
