package com.learn.googlemapapp.models;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PlaceApi {
    final static String TAG = "PLACE_API";
    ArrayList<String> arrayList = new ArrayList<>();

    public ArrayList<String> autoComplete(String input) {



        HttpURLConnection connection = null;
        StringBuilder jsonResult = new StringBuilder();

        try {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?sensor=false");
            sb.append("&input=" + input); // input=rajkot&key=AIzaSyAoFgSAvxNL1Qk-NG6Vjf9xVXnfrd2K_os
            sb.append("&key=AIzaSyAoFgSAvxNL1Qk-NG6Vjf9xVXnfrd2K_os");
            sb.append("&components=country:in");
            URL url = new URL(sb.toString());

            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = inputStreamReader.read(buff)) != -1)
            {
                jsonResult.append(buff,0,read);
            }

        } catch (Exception e) {
            Log.d(TAG, "" + e);

        }
        finally {
            if (connection!=null)
            {
                connection.disconnect();
            }
        }
        try {


            JSONObject jsonObject = new JSONObject(jsonResult.toString());
            JSONArray predictionItem = jsonObject.getJSONArray("predictions");

            for (int i=0;i<predictionItem.length();i++)
            {
                arrayList.add(predictionItem.getJSONObject(i).getString("description"));
            }
        }
        catch (Exception e)
        {
            Log.d(TAG,""+e);
        }
        return arrayList;

    }


}
