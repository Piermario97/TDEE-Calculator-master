package com.peretti.tdeecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    public SeekBar valCarbs;
    public TextView labelCarbs, labelTDEEs, labelProte, valTDEEs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent intent = getIntent();
        String txtData = intent.getExtras().getString("tdeeval","");
        valCarbs = (SeekBar)findViewById(R.id.sliCarbs);
        labelCarbs = (TextView) findViewById(R.id.labelCarbs);
        valTDEEs = (TextView) findViewById(R.id.valTDEEs);
        labelProte = (TextView) findViewById(R.id.labelProte);
        valTDEEs.setText(txtData);


        // perform seek bar change listener event used for getting the progress value
        valCarbs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Activity2.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
