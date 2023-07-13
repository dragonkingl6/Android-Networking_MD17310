package com.example.android_networking_md17310.buoi2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_networking_md17310.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainLopNoi extends AppCompatActivity {
    ImageView imageView;
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2_main);
        imageView = findViewById(R.id.demo21img);
        button =findViewById(R.id.demo21Btn);
        textView= findViewById(R.id.demo21Tv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Demo22AnsycTask().execute("http://tinypic.com/images/goodbye.jpg");
            }
        });
    }
    class Demo22AnsycTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null){
                imageView.setImageBitmap(bitmap);
            }else {
                textView.setText("co loi");
            }
        }
    }
}