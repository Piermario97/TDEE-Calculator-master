package com.peretti.tdeecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button calcola, gainBtn, loseBtn;
    public EditText valorePeso, valoreAltezza, valoreEta;
    public Spinner spinnerAttivita, spinnerSesso;
    public TextView valoreTDEE, valTDEE5, valTDEE10, valTDEE15;
    public Integer gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcola = (Button) findViewById(R.id.calcola);
        gainBtn = (Button) findViewById(R.id.gainBtn);
        loseBtn = (Button) findViewById(R.id.loseBtn);
        spinnerSesso = (Spinner) findViewById(R.id.spinnerSesso);
        spinnerAttivita = (Spinner) findViewById(R.id.spinnerAttivita);

        valoreAltezza = (EditText) findViewById(R.id.valoreAltezza);
        valorePeso = (EditText) findViewById(R.id.valorePeso);
        valoreEta = (EditText) findViewById(R.id.valoreEta);
        valoreTDEE = (TextView) findViewById(R.id.valoreTDEE);
        valTDEE5 = (TextView) findViewById(R.id.valTDEE5);
        valTDEE10 = (TextView) findViewById(R.id.valTDEE10);
        valTDEE15 =  (TextView) findViewById(R.id.valTDEE15);
        gainBtn.setOnClickListener(new setGain());
        loseBtn.setOnClickListener(new setLose());
        calcola.setOnClickListener(new CalcolaTDEE());
    }
    private class setGain implements View.OnClickListener {
        @Override
        public void onClick (View view){
            gl = 1;
        }
    }
    private class setLose implements View.OnClickListener {
        @Override
        public void onClick (View view){
            gl = 2;
        }
    }

    private class CalcolaTDEE implements View.OnClickListener {
        @Override
        public void onClick(View view) {
                double BMR, TDEE, TDEE5, TDEE10, TDEE15;
                TDEE=0;
                TDEE5=0;
                TDEE10=0;
                TDEE15=0;
                String mf = spinnerSesso.getSelectedItem().toString();
                String la = spinnerAttivita.getSelectedItem().toString();
                Integer eta = Integer.parseInt(valoreEta.getText().toString() );
                Integer peso = Integer.parseInt(valorePeso.getText().toString() );
                Integer altezza = Integer.parseInt(valoreAltezza.getText().toString() );
                if (mf.equals("Maschio")){
                    BMR = 66 + (13.7*peso) + (5*altezza) - (6.8*eta);
                } else {
                    BMR = 655 + (9.6 * peso) + (1.8 * altezza) - (4.7 * eta);
                }
                if (la.equals("Sedentario")){TDEE = BMR * 1.2;}
                if (la.equals("Leggermente Attivo")){TDEE = BMR * 1.375;}
                if (la.equals("Moderato")){TDEE = BMR * 1.55;}
                if (la.equals("Molto Allenato")){TDEE = BMR * 1.725;}
                if (la.equals("Estremamente Allenato")){TDEE = BMR * 1.9;}
                if (gl==1) {
                    TDEE5 = TDEE + (TDEE / 100 * 5);
                    TDEE10 = TDEE + (TDEE / 100 * 10);
                    TDEE15 = TDEE + (TDEE / 100 * 15);
                }
                if (gl==3){
                   //
                }
                if (gl==2){
                    TDEE5 = TDEE - (TDEE / 100 * 5);
                    TDEE10 = TDEE - (TDEE / 100 * 10);
                    TDEE15 = TDEE - (TDEE / 100 * 15);
                }
            valoreTDEE.setText(String.format("%.1f", TDEE ) );
            valTDEE5.setText(String.format("%.1f", TDEE5 ) );
            valTDEE5.setBackgroundColor(Color.parseColor("#90EE90"));
            valTDEE10.setText(String.format("%.1f", TDEE10 ) );
            valTDEE10.setBackgroundColor(Color.parseColor("#FFFF00"));
            valTDEE15.setText(String.format("%.1f", TDEE15 ) );
            valTDEE15.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }
}
