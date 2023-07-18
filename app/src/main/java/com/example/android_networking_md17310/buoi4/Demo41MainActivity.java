package com.example.android_networking_md17310.buoi4;

import androidx.appcompat.app.AppCompatActivity;
import com.example.android_networking_md17310.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demo41MainActivity extends AppCompatActivity {
    EditText editText1, editText2, editText3;
    Button btnI, btnS;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo41_main);
        editText1= findViewById(R.id.editTextTextPersonName1);
        editText2= findViewById(R.id.editTextTextPersonName2);
        editText3= findViewById(R.id.editTextTextPersonName3);
        btnS= findViewById(R.id.button2);
        btnI= findViewById(R.id.button1);
        tv= findViewById(R.id.textView2);
        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRes();
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectRetrofit();
            }
        });
    }
    void insertRes(){
        Product product= new Product();
        product.setName(editText1.getText().toString());
        product.setPrice(editText2.getText().toString());
        product.setDescription(editText2.getText().toString());

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceInsert interfaceInsert= retrofit.create(InterfaceInsert.class);
        Call<ServerResI> call = interfaceInsert.insertData(product.getName(), product.getPrice(),product.getDescription());
        call.enqueue(new Callback<ServerResI>() {
            @Override
            public void onResponse(Call<ServerResI> call, Response<ServerResI> response) {
                ServerResI serverResI = response.body();
                tv.setText(serverResI.getMessage());
            }

            @Override
            public void onFailure(Call<ServerResI> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });
    }
    String kq = "";
    ArrayList<Product> list;
    void selectRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceSelect interfaceSelect = retrofit.create(InterfaceSelect.class);
        Call<ServerResS> call= interfaceSelect.selectData();
        call.enqueue(new Callback<ServerResS>() {
            @Override
            public void onResponse(Call<ServerResS> call, Response<ServerResS> response) {
                ServerResS serverResS= response.body();
                list= new ArrayList<>(Arrays.asList(serverResS.getProducts()));
                kq = "";
                for (Product product: list){
                    kq +="Name: " +product.getName()+"Price: " +product.getPrice()+"Description: " +product.getDescription()+ "\n";

                }
                tv.setText(kq);
            }

            @Override
            public void onFailure(Call<ServerResS> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });

    }
}