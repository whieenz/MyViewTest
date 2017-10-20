package com.whieenz.myviewtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PView pView1;
    PView pView2;
    PView pView3;
    PView pView4;
    ThreeStepProgressView tsp;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        pView1 = (PView) findViewById(R.id.pv_1);
        pView2 = (PView) findViewById(R.id.pv_2);
        pView3 = (PView) findViewById(R.id.pv_3);
        pView4 = (PView) findViewById(R.id.pv_4);
        tsp = (ThreeStepProgressView) findViewById(R.id.tsp);
        List<String> strings = new ArrayList<>();
        strings.add("盒子问");
        strings.add("海贼团");
        strings.add("五彩池");
        tsp.setStepInfo(strings);
        tsp.setStepNum(2);
        editText = (EditText) findViewById(R.id.tv_0);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double progress = Double.parseDouble(editText.getText().toString());
                pView1.setProgress(progress);
                pView2.setProgress(progress);
                pView3.setProgress(progress);
                pView4.setProgress(progress);
            }
        });
    }


    public void add(View view){
       pView1.setProgress((float) (pView1.getProgress()+0.1));
       pView2.setProgress((float) (pView2.getProgress()+0.1));
       pView3.setProgress((float) (pView3.getProgress()+0.1));
       pView4.setProgress((float) (pView4.getProgress()+0.1));
    }
    public void sub(View view){
        pView1.setProgress((float) (pView1.getProgress()-0.1));
        pView2.setProgress((float) (pView2.getProgress()-0.1));
        pView3.setProgress((float) (pView3.getProgress()-0.1));
        pView4.setProgress((float) (pView4.getProgress()-0.1));
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
