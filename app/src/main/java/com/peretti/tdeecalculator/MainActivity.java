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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public Button calcola, gainBtn, loseBtn;
    public EditText valorePeso, valoreAltezza, valoreEta;
    public Spinner spinnerAttivita, spinnerSesso;
    public TextView valoreTDEE, valTDEE5, valTDEE10, valTDEE15;
    public Integer gl;
    public RadioGroup radioGroup;
    public TextView labelTDEE, labelTDEE5, labelTDEE10, labelTDEE15, labelKcal;
    public double TDEE15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcola = (Button) findViewById(R.id.calcola);
        spinnerSesso = (Spinner) findViewById(R.id.spinnerSesso);
        spinnerAttivita = (Spinner) findViewById(R.id.spinnerAttivita);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup2);
        labelTDEE = (TextView) findViewById(R.id.labelTDEE);
        labelTDEE5 = (TextView) findViewById(R.id.labelTDEE5);
        labelTDEE10 = (TextView) findViewById(R.id.labelTDEE10);
        labelTDEE15 = (TextView) findViewById(R.id.labelTDEE15);
        labelKcal = (TextView) findViewById(R.id.labelKcal);
        labelTDEE.setVisibility(View.INVISIBLE);
        labelTDEE5.setVisibility(View.INVISIBLE);
        labelTDEE10.setVisibility(View.INVISIBLE);
        labelTDEE15.setVisibility(View.INVISIBLE);
        labelKcal.setVisibility(View.INVISIBLE);

        valoreAltezza = (EditText) findViewById(R.id.valoreAltezza);
        valorePeso = (EditText) findViewById(R.id.valorePeso);
        valoreEta = (EditText) findViewById(R.id.valoreEta);
        valoreTDEE = (TextView) findViewById(R.id.valoreTDEE);
        valTDEE5 = (TextView) findViewById(R.id.valTDEE5);
        valTDEE10 = (TextView) findViewById(R.id.valTDEE10);
        valTDEE15 =  (TextView) findViewById(R.id.valTDEE15);

        calcola.setOnClickListener(new CalcolaTDEE());

        valTDEE15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2(view);
            }
        });

        valTDEE10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2(view);
            }
        });

        valTDEE5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2(view);
            }
        });
        valoreTDEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2(view);
            }
        });
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
    public void openActivity2(View view){
        int val = Integer.parseInt(((TextView) view).getText().toString());
        int peso = Integer.parseInt(valorePeso.getText().toString());
        Intent intent = new Intent(this, Activity2.class);
        Bundle extras = new Bundle();

        extras.putInt("tdeeval",val);
        extras.putInt("peso",peso);
        intent.putExtras(extras);
        startActivity(intent);
    }

    private class CalcolaTDEE implements View.OnClickListener {
        @Override
        public void onClick(View view) {
                double BMR, TDEE,TDEE5, TDEE10;
                int rTD, rTD5, rTD10, rTD15;
                TDEE=0;
                TDEE5=0;
                TDEE10=0;
                TDEE15=0;
                String mf = spinnerSesso.getSelectedItem().toString();
                String la = spinnerAttivita.getSelectedItem().toString();
                Integer eta = Integer.parseInt(valoreEta.getText().toString() );
                Integer peso = Integer.parseInt(valorePeso.getText().toString() );
                Integer altezza = Integer.parseInt(valoreAltezza.getText().toString());

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int indexRadioGroup = radioGroup.indexOfChild(radioButton);

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


                if (indexRadioGroup==1) {
                    TDEE5 = TDEE + (TDEE / 100 * 5);
                    TDEE10 = TDEE + (TDEE / 100 * 10);
                    TDEE15 = TDEE + (TDEE / 100 * 15);
                    labelTDEE5.setText("Ipercalorica al 5%");
                    labelTDEE10.setText("Ipercalorica al 10%");
                    labelTDEE15.setText("Ipercalorica al 15%");
                }
//                if (gl==0){
                   //
  //              }
                if (indexRadioGroup==0){
                    TDEE5 = TDEE - (TDEE / 100 * 5);
                    TDEE10 = TDEE - (TDEE / 100 * 10);
                    TDEE15 = TDEE - (TDEE / 100 * 15);
                    labelTDEE5.setText("Ipocalorica al 5%");
                    labelTDEE10.setText("Ipocalorica al 10%");
                    labelTDEE15.setText("Ipocalorica al 15%");
                }
            labelTDEE.setVisibility(View.VISIBLE);
            labelTDEE5.setVisibility(View.VISIBLE);
            labelTDEE10.setVisibility(View.VISIBLE);
            labelTDEE15.setVisibility(View.VISIBLE);
            labelKcal.setVisibility(View.VISIBLE);

            rTD = (int) Math.round(TDEE);
            rTD5 = (int) Math.round(TDEE5);
            rTD10 = (int) Math.round(TDEE10);
            rTD15 = (int) Math.round(TDEE15);

            valoreTDEE.setText(""+rTD);
            valTDEE5.setText(""+rTD5);
            valTDEE5.setBackgroundColor(Color.parseColor("#90EE90"));
            valTDEE10.setText(""+rTD10);
            valTDEE10.setBackgroundColor(Color.parseColor("#FFFF00"));
            valTDEE15.setText(""+rTD15);
            valTDEE15.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }
}
