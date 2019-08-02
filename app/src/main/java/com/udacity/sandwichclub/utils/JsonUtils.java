package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if(TextUtils.isEmpty(json))
             return null;

        String mainName = null,placeOfOrigin = null,description = null,image = null;
        List<String> alsoKnownNames = new ArrayList<>(),ingredients = new ArrayList<>();


        try {
            JSONObject rootJsonObject = new JSONObject(json);
            JSONObject nameObject = rootJsonObject.getJSONObject("name");
            if(nameObject.has("mainName")){
                mainName = nameObject.getString("mainName");
            }
            JSONArray knownNames = nameObject.getJSONArray("alsoKnownAs");
            if(knownNames.length() > 0){
                for(int i = 0; i < knownNames.length();i++){
                    String name = knownNames.getString(i);
                    alsoKnownNames.add(name);
                }
            }
            placeOfOrigin = rootJsonObject.getString("placeOfOrigin");
            description = rootJsonObject.getString("description");
            image = rootJsonObject.getString("image");
            JSONArray ingreArray = rootJsonObject.getJSONArray("ingredients");
            if(ingreArray.length() > 0){
                for(int i = 0; i < ingreArray.length();i++){
                    String name = ingreArray.getString(i);
                    ingredients.add(name);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Sandwich sandwich = new Sandwich(mainName,alsoKnownNames,placeOfOrigin,description,image,ingredients);
        return sandwich;
    }
}
