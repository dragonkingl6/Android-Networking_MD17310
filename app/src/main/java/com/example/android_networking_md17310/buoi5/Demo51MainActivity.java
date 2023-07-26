package com.example.android_networking_md17310.buoi5;

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

public class Demo51MainActivity extends AppCompatActivity {
    EditText txt1, txt2, txt3, txt4;
     Button btn1, btn2, btn3;
     TextView tvkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo51_main);
        txt1 = findViewById(R.id.pid);
        txt2 = findViewById(R.id.name);
        txt3 = findViewById(R.id.price);
        txt3 = findViewById(R.id.description);
        btn1 = findViewById(R.id.btnDelete);
        btn2 = findViewById(R.id.btnSelect);
        btn3 = findViewById(R.id.btnUpdate);
        tvkq = findViewById(R.id.textView4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteRetrofit();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectRetrofit();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateRetrofit();
            }
        });

    }

    private void DeleteRetrofit() {
        Product p = new Product();
        p.setPid(txt1.getText().toString());
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://duan1cuaducmtph20223.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceDelete interfaceDelete= retrofit.create(InterfaceDelete.class);
        Call<ServerResDeleteData> call= interfaceDelete.deleteData(p.getPid());
        call.enqueue(new Callback<ServerResDeleteData>() {
            @Override
            public void onResponse(Call<ServerResDeleteData> call, Response<ServerResDeleteData> response) {
                    ServerResDeleteData serverResDeleteData= response.body();
                    tvkq.setText(serverResDeleteData.getMessage());
            }

            @Override
            public void onFailure(Call<ServerResDeleteData> call, Throwable t) {
                tvkq.setText(t.getMessage());
            }
        });
    }

    private void UpdateRetrofit() {
        Product p=new Product();
        p.setPid(txt1.getText().toString());
        p.setName(txt2.getText().toString());
        p.setPrice(txt3.getText().toString());
        p.setDescription(txt4.getText().toString());
        //b1. Tao doi tuong Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://duan1cuaducmtph20223.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface + chuan bi ham + thuc thi ham
        InterfaceUpdate interUpdate=retrofit.create(InterfaceUpdate.class);
        Call<ServerResUpdataData> call=interUpdate.updateData(p.getPid(),
                p.getName(),p.getPrice(),p.getDescription());
        call.enqueue(new Callback<ServerResUpdataData>() {
            @Override
            public void onResponse(Call<ServerResUpdataData> call, Response<ServerResUpdataData> response) {
                //lay ve ket qua
                ServerResUpdataData svrResponseUpdate=response.body();
                //dua ket qua len man hinh
                tvkq.setText(svrResponseUpdate.getMessage());
            }

            @Override
            public void onFailure(Call<ServerResUpdataData> call, Throwable t) {
                tvkq.setText(t.getMessage());//ghi ra loi
            }
        });
    }
    String strKQ= "";
    ArrayList<Product> ls;
    private void SelectRetrofit() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://duan1cuaducmtph20223.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceSelect interfaceSelect= retrofit.create(InterfaceSelect.class);
        Call<ServerResSelectData> call= interfaceSelect.selectData();
        call.enqueue(new Callback<ServerResSelectData>() {
            @Override
            public void onResponse(Call<ServerResSelectData> call, Response<ServerResSelectData> response) {
                ServerResSelectData serverResSelectData= response.body();
                ls= new ArrayList<>(Arrays.asList(serverResSelectData.getProducts()));
                strKQ= "";
                for (Product p : ls){
                    strKQ += "Pid"+ p.getPid()
                            +"-"+p.getName()
                            +"-"+p.getPrice()
                            +"-"+p.getDescription() +"\n";
                }
                tvkq.setText(strKQ);
            }

            @Override
            public void onFailure(Call<ServerResSelectData> call, Throwable t) {
                tvkq.setText(t.getMessage());
            }
        });
    }
}