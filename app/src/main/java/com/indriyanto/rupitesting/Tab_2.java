package com.indriyanto.rupitesting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Tab_2 extends Fragment {

    TextView txtlan, txtlat,txtdeskripsi,txtmain,txtcity,txtspeed,txtdeg;



    public Tab_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_tab_2, container, false);

        txtlan = rootView.findViewById(R.id.txtlan);
        txtlat = rootView.findViewById(R.id.txtlat);
        txtdeskripsi = rootView.findViewById(R.id.txtdeskripsi);
        txtmain = rootView.findViewById(R.id.txtmain);
        txtcity = rootView.findViewById(R.id.txtcity);
        txtdeg = rootView.findViewById(R.id.txtdeg);

        txtspeed = rootView.findViewById(R.id.txtspeed);

        ///ambilTransaksiUser();

        Weather();
        return rootView;


    }


    public void Weather(){
        String url ="https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject main_object = null;
                try {
                    JSONObject  main2_object = response.getJSONObject("main");
                    JSONArray array =  response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main2_object.getDouble("temp"));
                    String main = object.getString("main");
                    String description = object.getString("description");
                    String city = response.getString("name");
                    /////////////////////////////////////////////////
                    JSONObject  wind_object = response.getJSONObject("wind");
                    String speed = String.valueOf(wind_object.getDouble("speed"));
                    String deg = String.valueOf(wind_object.getDouble("deg"));


                    txtdeskripsi.setText(description);
                    txtmain.setText(main);
                    txtcity.setText(city);
                    txtspeed.setText(speed);
                    txtdeg.setText(deg);





                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(jor);

    }






    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
