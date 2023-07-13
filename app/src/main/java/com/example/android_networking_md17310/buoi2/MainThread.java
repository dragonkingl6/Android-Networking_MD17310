package com.example.android_networking_md17310.buoi2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_networking_md17310.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainThread extends AppCompatActivity {
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
                Thread myThread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap=loadAnh("http://tinypic.com/images/goodbye.jpg");
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                textView.setText("thanh cong");
                            }
                        });
                    }
                });
                myThread.start();
            }
        });
    }
    private Bitmap loadAnh(String link){
        Bitmap bitmap= null;
        try {
            URL url= new URL(link);
            bitmap=BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}