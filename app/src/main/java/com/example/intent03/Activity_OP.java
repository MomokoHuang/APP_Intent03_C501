package com.example.intent03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Activity_OP extends AppCompatActivity {

    Button btn_calc;
    CheckBox checkbox_d;
    RadioButton rdbtn_1,rdbtn_2,rdbtn_3,rdbtn_4;
    double opd_1,opd_2,result  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op);
        btn_calc=findViewById(R.id.btn_calc);
        checkbox_d=findViewById(R.id.checkbox_d);
        rdbtn_1=findViewById(R.id.rdbtn_1);
        rdbtn_2=findViewById(R.id.rdbtn_2);
        rdbtn_3=findViewById(R.id.rdbtn_3);
        rdbtn_4=findViewById(R.id.rdbtn_4);

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //Intent i=Activity_OP.this.getIntent();
              //Bundle b=i.getExtras();
                Bundle b=Activity_OP.this.getIntent().getExtras() ;
                if(b!=null){
                    opd_1=Double.parseDouble(b.getString("OPD1"));
                    opd_2=Double.parseDouble(b.getString("OPD2"));

                    if(rdbtn_1.isChecked()) result=opd_1+opd_2   ;
                    if(rdbtn_2.isChecked()) result=opd_1-opd_2   ;
                    if(rdbtn_3.isChecked()) result=opd_1*opd_2   ;
                    if(rdbtn_4.isChecked()) {
                        result=opd_1/opd_2;
                        if(checkbox_d.isChecked())result=Math.floor(opd_1/opd_2) ;//Math.floor無條件捨去
                    }
                    //回傳算完的結果
                    Intent return_i=new Intent();
                    Bundle return_b=new Bundle();
                    return_b.putDouble("CALCULATE_RESULT",result);
                    return_i.putExtras(return_b);
                    setResult(RESULT_OK,return_i);
                    finish() ;
                }
            }
        });



    }
}