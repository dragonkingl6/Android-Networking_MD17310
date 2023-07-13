package com.example.android_networking_md17310.buoi2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_networking_md17310.R;

public class MainAsynsTask extends AppCompatActivity implements Interface, View.OnClickListener{


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
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        new AnsycTask(this, this).execute("http://tinypic.com/images/goodbye.jpg");
    }

    @Override
    public void onLoadAnh(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
        textView.setText("Loi khong doc duoc anh");
    }

    ;
}