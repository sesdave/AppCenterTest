package com.crowforce.terminal.appcentertest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
//import com.microsoft.appcenter.distribute.Distribute;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button checkUpdate=(Button)findViewById(R.id.checkbutton);
        checkUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Distribute.checkForUpdate();yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
                //AppUpdater appUpdater = new AppUpdater(MainActivity.this);
                //appUpdater.start();
               /* new AppUpdater(MainActivity.this)
                        //.setUpdateFrom(UpdateFrom.JSON)
                        .setUpdateFrom(UpdateFrom.XML)
                        .setUpdateXML("https://github.com/sesdave/AppCenterTest/blob/master/app/updatem.xml")
                       // .setUpdateXML("https://drive.google.com/file/d/1vYeUTDDSvWSjGYqVuRQp1yzSY-uJOYZs/view?usp=sharing")
                       // .setUpdateJSON("https://github.com/sesdave/AppCenterTest/blob/master/app/updater.json")
                        //.setUpdateJSON("https://drive.google.com/file/d/1PbpocPlyHaIryovHiS6c84StG1fJSQTu/view?usp=sharing")
                        .setDisplay(Display.DIALOG)
                        .showAppUpdated(true)
                        .start();

                */
              /*  new AppUpdater(MainActivity.this)
                        //.setUpdateFrom(UpdateFrom.GITHUB)
                        //.setGitHubUserAndRepo("javiersantos", "AppUpdater")
                        .setUpdateFrom(UpdateFrom.JSON)
                        .setUpdateJSON("https://raw.githubusercontent.com/javiersantos/AppUpdater/master/app/update.json")
                      //  .setUpdateJSON("https://raw.githubusercontent.com/sesdave/AppCenterTest/master/app/update-changelog.json")
                        .setDisplay(Display.DIALOG)
                        .showAppUpdated(true)
                        .start();

               */
                UpdateApp atualizaApp = new UpdateApp();
                atualizaApp.setContext(getApplicationContext());
                atualizaApp.execute("https://drive.google.com/u/0/uc?id=1TOn6UAJcEG_761qYHlX647AYaN9NHCu2&export=download");
            }
        });

        AppUpdater appUpdater = new AppUpdater(this);
        appUpdater.start();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class UpdateApp extends AsyncTask<String,Void,Void> {
        private Context context;
        public void setContext(Context contextf){
            context = contextf;
        }

        @Override
        protected Void doInBackground(String... arg0) {
            try {
                URL url = new URL(arg0[0]);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();

                String PATH = "/mnt/sdcard/Download/";
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, "update.apk");
                if(outputFile.exists()){
                    outputFile.delete();
                }
                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = c.getInputStream();

                byte[] buffer = new byte[1024];
                int len1 = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                }
                fos.close();
                is.close();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File("/mnt/sdcard/Download/update.apk")), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
                context.startActivity(intent);


            } catch (Exception e) {
                Log.e("UpdateAPP", "Update error! " + e.getMessage());
            }
            return null;
        }
    }
}
