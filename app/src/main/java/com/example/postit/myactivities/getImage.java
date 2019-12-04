package com.example.postit.myactivities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class getImage extends AsyncTask<String, Void, Bitmap> {
    public ImageView imageView;

    public Bitmap downloadImage(String src){
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
            return  myBitmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    protected Bitmap doInBackground(String... urls) {
        Log.d("doInBackground", "starting download of image");
        Bitmap image = downloadImage(urls[0]);
        return image;
    }


    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            imageView.setImageBitmap(result);
        }
        else {

        }

    }
}
