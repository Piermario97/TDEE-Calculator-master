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
    public TextView labelCarbs, labelTDEEs, labelProte, labelFats, valTDEEs, valPeso, valFats, valPro, valCarbs;
    public RadioGroup radioFats, radioPro;
    public Button btnCalcola;
    public int valpeso, valtdee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        valtdee = extras.getInt("tdeeval");
        valpeso = extras.getInt("peso");
        radioFats = (RadioGroup) findViewById(R.id.radioFats);
        radioPro = (RadioGroup) findViewById(R.id.radioPro);
        valCarbs = (TextView)findViewById(R.id.valCarbs);
        labelCarbs = (TextView) findViewById(R.id.labelCarbs);
        valTDEEs = (TextView) findViewById(R.id.valTDEEs);
        valPeso = (TextView) findViewById(R.id.valPeso);
        valFats = (TextView) findViewById(R.id.valFats);
        valPro = (TextView) findViewById(R.id.valPro);
        labelProte = (TextView) findViewById(R.id.labelProte);
        labelFats = (TextView)findViewById(R.id.labelFats);
        btnCalcola = (Button)findViewById(R.id.btnCalcola);

        Log.i("TDEE15", String.valueOf(valtdee));
        valTDEEs.setText(""+valtdee);
        valPeso.setText(""+valpeso);

        btnCalcola.setOnClickListener(new calcFats());
    }

    public class calcFats implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int radioButtonID = radioFats.getCheckedRadioButtonId();
            View radioButton = radioFats.findViewById(radioButtonID);
            int indexRadioGroup = radioFats.indexOfChild(radioButton);

            int radioButtonID2 = radioPro.getCheckedRadioButtonId();
            View radioButton2 = radioPro.findViewById(radioButtonID2);
            int indexRadioGroup2 = radioPro.indexOfChild(radioButton2);

            double[] moltiplicatorip = {0.75, 1, 1.25, 1.5, 2};
            double[] moltiplicatorif = {0.6, 0.8, 1, 1.2, 1.4};
                int valr;

                int fatsg, prog, carbsg;
                int fatscal=0, procal=0, carbscal=0;
                fatsg = (int)(valpeso*moltiplicatorif[indexRadioGroup]);
                prog = (int)(valpeso*moltiplicatorip[indexRadioGroup2]);
                fatscal = fatsg*9;
                procal = prog*4;
                valFats.setText("Grammi grassi: "+fatsg +"      calorie: "+fatscal);
                valPro.setText("Grammi proteine: "+prog+"  calorie: "+procal);

                valr = (valtdee-fatscal-procal);
                carbscal = (valtdee-fatscal-procal);
                carbsg = Math.round(carbscal/4);
                valCarbs.setText("Grammi carboidrati: "+carbsg+"  calorie: "+carbscal);
                Log.i("TDEErimanente", String.valueOf(valr));

        }
    }
}
