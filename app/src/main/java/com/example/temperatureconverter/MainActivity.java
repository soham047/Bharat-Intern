package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    boolean flag = false;
    Double t,t2;
    String d1,d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Spinner sp;
        RadioGroup cnvttemp;
        RadioButton stc,stf,stk;
        EditText edttemp;
        Button cnvt;
        TextView showtemp;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=findViewById(R.id.tst);
        cnvttemp = findViewById(R.id.cnvttemp);
        stc= findViewById(R.id.stc);
        stf= findViewById(R.id.stf);
        stk= findViewById(R.id.stk);
        edttemp= findViewById(R.id.edttemp);
        cnvt = findViewById(R.id.cnvt);
        showtemp = findViewById(R.id.showtemp);


        String[] s=new String[]{"° C","° F", "K"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,s);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                d1= adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                flag = true;
            }
        });

        cnvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rt= cnvttemp.getCheckedRadioButtonId();
                if(rt!=-1 && flag==false){
                    String tm = edttemp.getText().toString();
                    t = Double.parseDouble(tm);
                    d2 = ((RadioButton)findViewById(cnvttemp.getCheckedRadioButtonId())).getText().toString();
                    String d3="";
                    if(d1.equals("° F"))
                    {
                        t = (t - 32) * 0.5556;
                    }
                    else if(d1.equals("K"))
                    {
                        t = t - 273.15;
                    }
                    if(d2.equals("Farenheit"))
                    {
                        t2 = ((9*t)/5) + 32;
                        d3="° F";
                    }
                    else if(d2.equals("Kelvin"))
                    {
                        t2 = t + 273.15;
                        d3="K";
                    }
                    else if(d2.equals("Celsius"))
                    {
                        t2=t;
                        d3="° C";
                    }

                    t2 =Double.parseDouble(new DecimalFormat("##.###").format(t2));
                    // showtemp.setText(String.valueOf(t2));
                    String output = "The converted temperature is : " + t2.toString() + " " + d3;
                    showtemp.setText(output);
                    //Toast.makeText(MainActivity.this, "The converted temmperature is : " + t2.toString() + d2, Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, "Fill all the required data.", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}