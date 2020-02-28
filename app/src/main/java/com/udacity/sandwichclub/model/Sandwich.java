package com.udacity.sandwichclub.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.R;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getAlsoKnownAs() {
        if (alsoKnownAs.size() > 0) {
            String knownAsString = alsoKnownAs.toString();
            return knownAsString.substring(1, knownAsString.length() - 1) + ".";
        } else {
            return "No Data Available";
        }
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        if (placeOfOrigin.length() > 0)
            return placeOfOrigin + ".";
        else {
            return "No Data Available";
        }
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngredients() {
        String ing = ingredients.toString();
        return ing.substring(1,ing.length()-1) + ".";
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @BindingAdapter("sandwichImage")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .noFade()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.download)
                .into(imageView);
    }


}
