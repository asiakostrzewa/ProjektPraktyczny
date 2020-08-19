package http;

import com.google.gson.Gson;

public class ResponseParser {
    public static <T> T  parseFromString(String content, Class <T> clazz){
        Gson gson = new Gson();
        return gson.fromJson(content, clazz);
    }
}
