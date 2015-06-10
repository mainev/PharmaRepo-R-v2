/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maine
 */
public abstract class Serializer {

    private static final String pattern = "yyyy-MM-dd'T'HH:mm:ss";

    public static <T> ObservableList<T> deserializeList(String json, Class<T> typeClass) {
        ObservableList<T> observableList = FXCollections.observableArrayList();
        Gson gson = new Gson();
        List<T> datasets = gson.fromJson(json, new ListOfJson(typeClass));
        datasets.forEach(rm -> observableList.add(rm));

        return observableList;
    }

     public static <T> T deserialize(String jsonOutput, Class<T> typeClass) {
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        T data = gson.fromJson(jsonOutput, typeClass);
        return data;
    }

    public static String serialize(Object object) {
        Gson gson = new GsonBuilder().setDateFormat(pattern).create();
        String json = gson.toJson(object);

        return json;
    }

}
