package com.example.android_networking_md17310.buoi2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AnsycTask extends AsyncTask<String, Void, Bitmap> {
    private Interface myDemo21Interface;
    private Context context;

    public AnsycTask(Interface myDemo21Interface, Context context) {
        this.myDemo21Interface = myDemo21Interface;
        this.context = context;
    }

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
        if (bitmap!= null){
            myDemo21Interface.onLoadAnh((bitmap));
        }else {
            myDemo21Interface.onError();
        }
    }
}
