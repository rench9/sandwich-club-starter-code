package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView ingredientsIv = findViewById(R.id.image_iv);

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
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
            closeOnError();

        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .centerCrop()
                .fit()
                .error(R.drawable.warning)
                .into(ingredientsIv);

        setTitle(" ");
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView nameTv = findViewById(R.id.name_tv);
        TextView originTv = findViewById(R.id.origin_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);
        TextView alsoKnownTv = findViewById(R.id.also_known_tv);
        TextView alsoKnownLbtTv = findViewById(R.id.also_known_lbl_tv);
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        TextView ingredientsLblTv = findViewById(R.id.ingredients_lbl_tv);

        nameTv.setText(sandwich.getMainName());

        if (sandwich.getPlaceOfOrigin() != null && !sandwich.getPlaceOfOrigin().isEmpty())
            originTv.setText(sandwich.getPlaceOfOrigin());
        else
            originTv.setText("Unknown");

        descriptionTv.setText(sandwich.getDescription());


        if (sandwich.getAlsoKnownAs() != null && !sandwich.getAlsoKnownAs().isEmpty())
            alsoKnownTv.setText("\u25CF  " + TextUtils.join("  \u25CF  ", sandwich.getAlsoKnownAs()));
        else {
            alsoKnownLbtTv.setVisibility(View.GONE);
            alsoKnownTv.setVisibility(View.GONE);
        }

        if (sandwich.getIngredients() != null && !sandwich.getIngredients().isEmpty())
            ingredientsTv.setText("\u25CF  " + TextUtils.join("  \u25CF  ", sandwich.getIngredients()));
        else {
            ingredientsLblTv.setVisibility(View.GONE);
            ingredientsTv.setVisibility(View.GONE);
        }

    }


}
