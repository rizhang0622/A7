package com.example.zhangassignments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class A6Activity extends AppCompatActivity {

    private ProgressDialog pDialog;
    public interface MealSearchCallback {
        void onResult(String result);
    }

    public List<Meal> allMeals = new ArrayList<>();
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter;

    private EditText recipeNameEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a6);

        recyclerView = findViewById(R.id.a6_rv_meals);

        recipeNameEt = findViewById(R.id.a6_et_name);
        findViewById(R.id.searchmeal_btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(A6Activity.this, "Loading\nPlease wait");
                searchMealByName(recipeNameEt.getText().toString(), new MealSearchCallback() {
                    @Override
                    public void onResult(String result) {

                        try {
                            JSONObject response = new JSONObject(result);
                            JSONArray mealsJsonArray = response.getJSONArray("meals");


                            for(int i=0; i<mealsJsonArray.length(); i++){
                                JSONObject mealJsonObject = mealsJsonArray.getJSONObject(i);
                                allMeals.add(new Meal(mealJsonObject.getString("strMeal"),
                                        mealJsonObject.getString("strCategory"),mealJsonObject.getString("strInstructions"),
                                        mealJsonObject.getString("strMealThumb")));
                            }


                            recyclerView.setLayoutManager(new LinearLayoutManager(A6Activity.this));


                            mealAdapter = new MealAdapter(A6Activity.this, allMeals);
                            recyclerView.setAdapter(mealAdapter);


                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

            }
        });



    }


    public void searchMealByName(String mealName, MealSearchCallback callback) {
        // Replace spaces with URL encoded character to ensure the URL is valid
        String queryUrl = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + mealName.replace(" ", "%20");

        // Use a background thread to perform network request
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(queryUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuilder builder = new StringBuilder();

                    dismissProgress();
                    if (inputStream == null) {
                        // Nothing to do.
                        return;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                        // But it does make debugging a *lot* easier if you print out the completed
                        // buffer for debugging.
                        builder.append(line).append("\n");
                    }

                    if (builder.length() == 0) {
                        // Stream was empty. No point in parsing.
                        return;
                    }
                    String mealJsonStr = builder.toString();

                    A6Activity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResult(mealJsonStr);
                        }
                    });


                } catch (IOException e) {
                    // Log error
                    e.printStackTrace();
                    dismissProgress();
                } finally {
                    dismissProgress();
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (final IOException e) {
                            // Log error
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }


    public void showProgress(Context context, String message){
        pDialog = new ProgressDialog(context); //Your Activity.this
        pDialog.setMessage(message);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void dismissProgress(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}