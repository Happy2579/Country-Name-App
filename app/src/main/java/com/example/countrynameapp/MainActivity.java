package com.example.countrynameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.countrynameapp.adapters.CountryAdapter;
import com.example.countrynameapp.model.CountryModel;
import com.example.countrynameapp.model.Result;
import com.example.countrynameapp.service.GetCountryDataService;
import com.example.countrynameapp.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ArrayList<CountryModel> countries;

    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        GETCountries();
    }

    public Object GETCountries() {

        GetCountryDataService getCountryDataService= RetrofitInstance.getService();
        Call<Result> call=getCountryDataService.getResult();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result=response.body();

                if(result!=null && result.getResult()!=null){
                    countries=(ArrayList<CountryModel>) result.getResult();

//                    for(CountryModel c:countries){
//                        //Log.i("TAG",""+c.getName());
//                    }
                    ViewData();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        return countries;
    }

    private void ViewData() {
        recyclerView=findViewById(R.id.recyclerView);
        countryAdapter=new CountryAdapter(countries);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);
    }
}