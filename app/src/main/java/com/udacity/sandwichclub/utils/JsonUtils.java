package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich = new Sandwich();
        JSONObject jsonObject = new JSONObject(json);

        try {
            sandwich.setMainName(jsonObject.getJSONObject("name").getString("mainName"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setAlsoKnownAs(jsonArrayToList(jsonObject.getJSONObject("name").getJSONArray("alsoKnownAs")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setDescription(jsonObject.getString("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setImage(jsonObject.getString("image"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sandwich.setIngredients(jsonArrayToList(jsonObject.getJSONArray("ingredients")));
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
