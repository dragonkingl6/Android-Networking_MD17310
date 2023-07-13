package com.example.android_networking_md17310.buoi3;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_networking_md17310.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Demo31MainActivity extends AppCompatActivity {
    EditText txtT, txtV;
    Button btn;
    TextView tvKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo31_main);
        txtT = findViewById(R.id.editTextTextPersonName);
        txtV = findViewById(R.id.editTextTextPersonName2);
        btn = findViewById(R.id.button);
        tvKQ = findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new POSTAsyncTask().execute();
            }
        });

    }

    class POSTAsyncTask extends AsyncTask<Void, Void, Void> {
        String kq = "";
        String pathPost = "https://duan1cuaducmtph20223.000webhostapp.com/mob403lab3/bai3-post.php";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
//1.chuyen path thanh url
                URL url = new URL(pathPost);
//2. ma hoa tham so
                String param = "canh=" + URLEncoder.encode(txtT.getText().toString(), "utf-8");
//3. Mo ket noi
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//4. Thiet lap cac thuoc tinh cho ket noi
                urlConnection.setDoOutput(true);//lay ve ket qua//
                urlConnection.setRequestMethod("POST");//xac dinh phuong thuc
                urlConnection.setFixedLengthStreamingMode(param.getBytes().length);//do dai tham so
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//5. thiet lap tham so
                PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
                printWriter.print(param);
                printWriter.close();
//6. doc du lieu
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }
//7. tra ve ket qua
                kq = stringBuilder.toString();
                urlConnection.disconnect();//dong ket noi
            } catch (MalformedURLException e) {
                e.printStackTrace();
                tvKQ.setText(e.getMessage());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                tvKQ.setText(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                tvKQ.setText(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            tvKQ.setText(kq);//tra ket qua ve client }
        }

        class GETAsyncTask extends AsyncTask<Void, Void, Void> {
            String ketqua = "";
            String path = "https://duan1cuaducmtph20223.000webhostapp.com/mob403lab3/bai3-get.php";

            @Override
            protected Void doInBackground(Void... voids) {

                path += "?toan=" + txtT.getText().toString() + "&van=" + txtV.getText().toString();
                try {
                    URL url = new URL(path);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((url.openConnection().getInputStream())));
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    ketqua = stringBuilder.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                tvKQ.setText(ketqua);
            }
        }
    }
}