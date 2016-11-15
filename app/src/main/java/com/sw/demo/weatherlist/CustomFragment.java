package com.sw.demo.weatherlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

import android.widget.ListView;

import com.sw.demo.weatherlist.model.Weather;

/**
 * Created by SimonWong on 15/11/2016.
 */

public class CustomFragment extends Fragment {

    private ListView lv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.custom_fragment, container, false);
        lv = (ListView) v.findViewById(R.id.list);
        new GetWeatherJSON().execute("http://api.openweathermap.org/data/2.5/forecast?lat=22.2734&lon=114.169&mode=json&APPID=ffae84b0971f42fcaed3fe048dab8533");
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class GetWeatherJSON extends android.os.AsyncTask<String, Void, JSONObject> {

        private final static String TAG = "GetWeatherJSONAsyncTask";

        private Double K2C(Double f) {
            Double value = f - 273.15d;
            long factor = (long) Math.pow(10, 2);
            value = value * factor;
            long tmp = Math.round(value);
            return (double) tmp / factor;
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            JSONObject jObj = null;
            String result;
            StringBuffer sb = new StringBuffer();
            java.io.InputStream is = null;
            try {
                if (strings == null || strings.length <= 0)
                    throw new Exception("Wrong parameter passed");
                java.net.URL url = new java.net.URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(5000);
                urlConnection.setReadTimeout(5000);
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();
                is = new java.io.BufferedInputStream(urlConnection.getInputStream());
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
                String inputLine = "";
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                result = sb.toString();
                android.util.Log.d(TAG, "JSON return = " + result);
                jObj = new JSONObject(result);
            } catch (Exception e) {
                jObj = null;
                android.util.Log.e(TAG, e.toString());
            } finally {
                if (is != null)
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return jObj;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            // generate Weather here
            if (result == null || result.isNull("list")) {
                // indicate failure
                android.util.Log.d(TAG, "result return empty");
            } else {
                try {
                    JSONArray list = result.getJSONArray("list");
                    Weather weather = new Weather();
                    weather.list = new java.util.ArrayList<>();
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject item = list.getJSONObject(i);
                        if (item.has("main") && item.has("dt_txt") && item.getJSONObject("main").has("temp")) {
                            Weather.List record = weather.new List();
                            record.dtTxt = item.getString("dt_txt");
                            Weather.Main recordMain = weather.new Main();
                            recordMain.temp = K2C(item.getJSONObject("main").getDouble("temp"));
                            record.main = recordMain;
                            weather.list.add(record);
                        }
                    }
                    WeatherAdapter mAdapter = new WeatherAdapter(CustomFragment.this.getContext(), weather);
                    lv.setAdapter(mAdapter);
                    android.util.Log.d(TAG, "weather size = " + mAdapter.getCount());
                } catch (JSONException e) {
                    android.util.Log.e(TAG, "JSON Parsing error : " + e.toString());
                }
            }
        }
    }
}
