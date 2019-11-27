package com.indriyanto.rupitesting;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.indriyanto.rupitesting.Api.Apiweather.Coord;
import com.indriyanto.rupitesting.Api.JSONResponse;
import com.indriyanto.rupitesting.Api.RequestInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.indriyanto.rupitesting.BuildConfig.BASE_URL;



public class Tab_1 extends Fragment {

    String lon;
    private ArrayList<Coord> mArrayListWeather;
    TextView txtlan, txtlat,txtdeskripsi,txtmain,txtcity,txtspeed,txtdeg;



    public Tab_1() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =   inflater.inflate(R.layout.fragment_tab_1, container, false);

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

    public void ambilTransaksiUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getweather();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();

                mArrayListWeather = new ArrayList<>(Arrays.asList(jsonResponse.getCoord()));
                double lon = mArrayListWeather.get(0).getLon();
                double lat = mArrayListWeather.get(0).getLat();


               txtlan.setText((int) lon);
               txtlat.setText((int) lat);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
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





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
