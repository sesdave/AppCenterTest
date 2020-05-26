package com.crowforce.terminal.appcentertest;

import android.os.Bundle;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
//import com.microsoft.appcenter.distribute.Distribute;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

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
                new AppUpdater(MainActivity.this)
                        //.setUpdateFrom(UpdateFrom.JSON)
                        .setUpdateFrom(UpdateFrom.XML)
                        .setUpdateXML("https://github.com/sesdave/AppCenterTest/blob/master/app/updatem.xml")
                       // .setUpdateXML("https://drive.google.com/file/d/1vYeUTDDSvWSjGYqVuRQp1yzSY-uJOYZs/view?usp=sharing")
                       // .setUpdateJSON("https://github.com/sesdave/AppCenterTest/blob/master/app/updater.json")
                        //.setUpdateJSON("https://drive.google.com/file/d/1PbpocPlyHaIryovHiS6c84StG1fJSQTu/view?usp=sharing")
                        .setDisplay(Display.DIALOG)
                        .showAppUpdated(true)
                        .start();
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
}
