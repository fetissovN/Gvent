package com.nick.gvent.util;


import org.json.simple.JSONObject;

/** Class for making valid json objects
 * @autor Fetissov Mikalai
 * @version 1.0
 */

public class JsonParser {


//    @Deprecated
//    public static synchronized Quiz parseJsonStrToObject(String s) throws IOException {
//        if (s == null){
//            return null;
//        }
//        ObjectMapper mapper = new ObjectMapper();
//        UserDTO theme = mapper.readValue(s, UserDTO.class);
//        ThemeToQuiz toQuiz = new ThemeToQuiz();
//        return toQuiz.convert(theme);
//    }

//    @Deprecated
//    public static synchronized String objectToStringJson(Quiz quiz) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(quiz);
//    }

    /** Creates json status object form String
     * @param message- цена
     * @see JsonParser#makeStatus(String)()
     */
    public static synchronized JSONObject makeStatus(String message){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",message);
        return jsonObject;
    }

    /** Creates json url object form String
     * @param message- цена
     * @see JsonParser#makeUrl(String)()
     */
    public static synchronized JSONObject makeUrl(String message){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url",message);
        return jsonObject;
    }
}
