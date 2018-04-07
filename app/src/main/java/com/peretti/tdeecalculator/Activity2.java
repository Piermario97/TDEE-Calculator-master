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
    public SeekBar valCarbs, valProte;
    public TextView labelCarbs, labelTDEEs, labelProte, labelFats, valTDEEs, valPeso, valFats;
    public RadioGroup radioFats;
    public Button btnCalcola;
    public int valpeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int valtdee = extras.getInt("tdeeval");
        valpeso = extras.getInt("peso");
        radioFats = (RadioGroup) findViewById(R.id.radioFats);
        valProte = (SeekBar)findViewById(R.id.sliProte);
        valCarbs = (SeekBar)findViewById(R.id.sliCarbs);
        labelCarbs = (TextView) findViewById(R.id.labelCarbs);
        valTDEEs = (TextView) findViewById(R.id.valTDEEs);
        valPeso = (TextView) findViewById(R.id.valPeso);
        valFats = (TextView) findViewById(R.id.valFats);
        labelProte = (TextView) findViewById(R.id.labelProte);
        labelFats = (TextView)findViewById(R.id.labelFats);
        btnCalcola = (Button)findViewById(R.id.btnCalcola);

        Log.i("TDEE15", String.valueOf(valtdee));
        valTDEEs.setText(""+valtdee);
        valPeso.setText(""+valpeso);

        btnCalcola.setOnClickListener(new calcFats());

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

    public class calcFats implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int radioButtonID = radioFats.getCheckedRadioButtonId();
            View radioButton = radioFats.findViewById(radioButtonID);
            int indexRadioGroup = radioFats.indexOfChild(radioButton);
            double[] moltiplicatori = {0.5, 0.75, 1, 1.25, 1.5};
                double fats;
                fats = valpeso*moltiplicatori[indexRadioGroup];
                valFats.setText(""+fats);

        }
    }
}
