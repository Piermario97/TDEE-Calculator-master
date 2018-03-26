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
    SeekBar valCarbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent intent = getIntent();
        String txtData = intent.getExtras().getString("tdeeval","");
        TextView valTDEEs = (TextView) findViewById(R.id.valTDEEs);
        valTDEEs.setText(txtData);

        valCarbs=(SeekBar)findViewById(R.id.sliCarbs);
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
