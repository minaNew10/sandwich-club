package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String NAME_OBJECT = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN = "alsoKnownAs";
    private static final String ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        if(TextUtils.isEmpty(json))
             return null;

        String mainName = null,placeOfOrigin = null,description = null,image = null;
        List<String> alsoKnownNames = new ArrayList<>(),ingredients = new ArrayList<>();


        try {
            JSONObject rootJsonObject = new JSONObject(json);
            JSONObject nameObject = rootJsonObject.optJSONObject(NAME_OBJECT);
            mainName = nameObject.optString(MAIN_NAME);

            JSONArray knownNames = nameObject.optJSONArray(ALSO_KNOWN);
            if(knownNames.length() > 0){
                for(int i = 0; i < knownNames.length();i++){
                    String name = knownNames.getString(i);
                    alsoKnownNames.add(name);
                }
            }
            placeOfOrigin = rootJsonObject.optString(ORIGIN,"No Data Available");
            description = rootJsonObject.optString(DESCRIPTION,"No Data Available");
            image = rootJsonObject.optString(IMAGE);
            JSONArray ingreArray = rootJsonObject.optJSONArray(INGREDIENTS);
            if(ingreArray.length() > 0){
                for(int i = 0; i < ingreArray.length();i++){
                    String name = ingreArray.optString(i);
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
