package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static String NAME = "name";
    public static String MAINNAME = "mainName";
    public static String ALSOKNOWNAS = "alsoKnownAs";
    public static String ORIGIN = "placeOfOrigin";
    public static String DESCRIPTION = "description";
    public static String IMAGE = "image";
    public static String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich = new Sandwich();
        JSONObject jsonObject = new JSONObject(json);

        try {
            sandwich.setMainName(jsonObject.getJSONObject(NAME).getString(MAINNAME));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setAlsoKnownAs(jsonArrayToList(jsonObject.getJSONObject(NAME).getJSONArray(ALSOKNOWNAS)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setPlaceOfOrigin(jsonObject.getString(ORIGIN));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setDescription(jsonObject.getString(DESCRIPTION));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setImage(jsonObject.getString(IMAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setIngredients(jsonArrayToList(jsonObject.getJSONArray(INGREDIENTS)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    private static List<String> jsonArrayToList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList();
        for (int i = 0; i < jsonArray.length(); list.add(jsonArray.getString(i++))) ;
        return list;
    }
}
