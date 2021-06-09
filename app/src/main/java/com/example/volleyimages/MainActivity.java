package com.example.volleyimages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageLoader imageLoader;
    private ArrayList<Heroes> heroesList = new ArrayList<>();
    private RecyclerView recyclerViewHeroes;
    private AdaptadorHeroes adaptadorHeroes;
    private TextView textViewTotalHeroes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewHeroes=findViewById(R.id.reVieCon);
        textViewTotalHeroes=findViewById(R.id.txtTotalHeroes);

        recyclerViewHeroes.setLayoutManager(new LinearLayoutManager(this));
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE}, 1);

        }
        cargarJson();
    }


    private  void cargarJson(){
        String url = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("heroes");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Heroes heroes = new Heroes();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                heroes.setUrlImage(jsonObject.getString("imageurl"));
                                heroes.setName(jsonObject.getString("name"));
                                heroesList.add(heroes);
                            }
                            textViewTotalHeroes.setText(textViewTotalHeroes.getText()+" "+heroesList.size());
                            Log.d("IDEA","Conto");
                            adaptadorHeroes=new AdaptadorHeroes(heroesList,MainActivity.this);
                            Log.d("IDEA","Creo Adaptador");
                            recyclerViewHeroes.setAdapter(adaptadorHeroes);

                            Log.d("IDEA","Mando Adaptador");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });


        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
