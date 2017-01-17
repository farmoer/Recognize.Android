package com.farmoer.image.imageproc.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import com.farmoer.image.imageproc.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class SplashActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String path = Environment.getExternalStorageDirectory() + File.separator + getString(R.string.app_name) + File.separator + "tessdata";
        InitialTask initialTask = new InitialTask();
        if(initialTask.getStatus() != AsyncTask.Status.RUNNING){
            initialTask.execute( path );
        }


    }
    private class InitialTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            String path = params[0];
            File directory = new File( path );
            if(!directory.exists()){
                if(!directory.mkdirs())
                    return false;
            }

            String outputFile = path + File.separator + "eng.traineddata";

            if (!(new File( outputFile )).exists())
            {
                InputStream inputStream = getResources().openRawResource(R.raw.eng_traineddata);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                    byte[] buffer = new byte[8192];
                    int count ;
                    int total = inputStream.available();
                    int bytes = 0;

                    while ((count = inputStream.read(buffer)) > 0)
                    {
                        fileOutputStream.write(buffer, 0, count);
                        bytes += count;
                        publishProgress((int) ((bytes / (float) total) * 100));
                    }
                    fileOutputStream.close();
                    inputStream.close();
                }catch (IOException exception){
                    return false;
                }

            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if(result){
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mobile = preferences.getString("mobile", "");
                String token = preferences.getString("token", "");

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim);
            }


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }


}
