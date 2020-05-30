package com.crowforce.terminal.appcentertest;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    String rarPath;

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
                //atualizaApp.execute("https://www.google.com");


               /* InUpdateApp atualizaApp = new InUpdateApp();
                //atualizaApp.setContext(getApplicationContext());
                atualizaApp.execute();

                */

               // atualizaApp.execute("https://drive.google.com/u/0/uc?id=1TOn6UAJcEG_761qYHlX647AYaN9NHCu2&export=download");
                atualizaApp.execute("https://raw.githubusercontent.com/sesdave/AppCenterTest/master/app/release/app-release.apk");
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
                //URL url = URLEncoder.encode(arg0[0],"UTF-8");
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
               // c.setRequestMethod("GET");
               // c.setRequestProperty("Host", "myhost.com");
               // c.setRequestProperty("Authorization", "Basic " + Base64.encodeToString(toencode, Base64.DEFAULT));
               // c.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; PPC; en-US; rv:1.3.1)");
                //c.setRequestProperty("Accept-Charset", "UTF-8");
                //c.setRequestProperty("Accept","application/json");
               // c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                c.setReadTimeout(40000);
                c.setConnectTimeout(40000);

               // c.setConnectTimeout (5000) ;
                c.setDoOutput(true);
                //c.setDoInput(true);
                c.connect();

                String PATH = "/mnt/sdcard/Download/";
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, "update.apk");
                if(outputFile.exists()){
                    outputFile.delete();
                }
                FileOutputStream fos = new FileOutputStream(outputFile);
                Log.e("action","After fileoutput");
                int restatus=c.getResponseCode();
                Log.e("action","Respond code"+restatus);
                int status = c.getResponseCode();
                InputStream is;
               /* if (status == HttpURLConnection.HTTP_OK) {
                    is = c.getInputStream();
                    Log.e("action","Got Stream Value");
                } else {
                    is = c.getErrorStream();
                    Log.e("action","Got Error from stream");
                }

                */
               // InputStream is = c.getInputStream();
                is = new BufferedInputStream(
                        url.openStream(),
                        8192
                );

                Log.e("action","Started writting stream"+String.valueOf(is));

                byte[] buffer = new byte[1024];
                int len1 = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                }


               // os = new BufferedOutputStream( new FileOutputStream( tmp ) );
                //copyStream( is, os );
                fos.close();
                is.close();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Log.e("action","got to action View");
                intent.setDataAndType(Uri.fromFile(new File("/mnt/sdcard/Download/update.apk")), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
                Log.e("action","About to start Intent");
                context.startActivity(intent);


            } catch (Exception e) {
                Log.e("UpdateAPP", "Update error! " + e.getMessage());
            }
            return null;
        }
    }

    public class InUpdateApp extends AsyncTask<Void,Void,Void> {
       // private Context context;
       /* public void setContext(Context contextf){
            context = contextf;
        }

        */

        @Override
        protected Void doInBackground(Void... arg0) {
            rarPath = "rar/sms.apk";
            AssetManager assetManager = getAssets();

            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(rarPath);
                out = new FileOutputStream("/sdcard/myapk.apk");

                byte[] buffer = new byte[1024];

                int read;
                while((read = in.read(buffer)) != -1) {

                    out.write(buffer, 0, read);

                }

                in.close();
                in = null;

                out.flush();
                out.close();
                out = null;

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setDataAndType(Uri.fromFile(new File("/sdcard/myapk.apk")),
                        "application/vnd.android.package-archive");

                startActivity(intent);

            } catch(Exception e) { e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_LONG).show();}
            return null;
        }
           // return null;
        }

}
