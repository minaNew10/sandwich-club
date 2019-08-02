package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView txtvDescription,txtvIngredients,txtvPlaceOfOrigin,labelPlaceOfOrigin,txtvKnownName,labelKnownName;
    ImageView ingredientsIv;
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static final String TAG = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);

        txtvDescription = findViewById(R.id.description_tv);
        txtvIngredients = findViewById(R.id.ingredients_tv);
        txtvPlaceOfOrigin = findViewById(R.id.origin_tv);
        labelPlaceOfOrigin = findViewById(R.id.origin_label);
        txtvKnownName = findViewById(R.id.also_known_tv);
        labelKnownName = findViewById(R.id.also_known_label);
        Intent intent = getIntent();
        if (intent == null) {

            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        Log.i(TAG, "onCreate: "+ sandwich.getMainName() + " " + sandwich.getAlsoKnownAs() + " " + sandwich.getPlaceOfOrigin() + " "
                + sandwich.getIngredients() + " " + sandwich.getDescription());
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        if(placeOfOrigin.length() > 0)
              txtvPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin()+".");
        else {
            txtvPlaceOfOrigin.setVisibility(View.GONE);
            labelPlaceOfOrigin.setVisibility(View.GONE);
        }
        List<String> knownNames = sandwich.getAlsoKnownAs();
        if(knownNames.size() > 0) {
            String known =  knownNames.toString();
            txtvKnownName.setText(known.substring(1,known.length()-1)+ ".");
        }else {
            txtvKnownName.setVisibility(View.GONE);
            labelKnownName.setVisibility(View.GONE);
        }
        String ing = sandwich.getIngredients().toString();
        txtvIngredients.setText(ing.substring(1,ing.length()-1) + ".");
        txtvDescription.setText(sandwich.getDescription());
    }
}
