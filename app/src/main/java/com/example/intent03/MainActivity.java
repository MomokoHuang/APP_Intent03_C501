package com.example.intent03;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//使用新的寫法要import的
import androidx.activity.result.ActivityResult; //ActivityResult
import androidx.activity.result.ActivityResultCallback; //ActivityResult的Callback
import androidx.activity.result.ActivityResultLauncher; //ActivityResultLauncher啟動器,啟動合約
import androidx.activity.result.contract.ActivityResultContracts; //合約
import androidx.core.app.ActivityOptionsCompat;

public class MainActivity extends AppCompatActivity {
    //    private static final int SET_RESULT=100 ; //請求碼,自行定義,但不可重複
    EditText input_opd1,input_opd2 ;
    Button btn_op ;
    TextView text_output ;


    //註冊啟動器
    ActivityResultLauncher launcher_1=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){ //RESULT_OK值為-1
                Intent i=result.getData();
                Bundle b=i.getExtras();
                if(b!=null) {
                    Double answer = b.getDouble("CALCULATE_RESULT");
                    text_output.setText("運算結果=" + answer.toString());
                }
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_opd1=findViewById(R.id.edittext_opd1) ;
        input_opd2=findViewById(R.id.edittext_opd2) ;
        text_output=findViewById(R.id.textview_output);
        btn_op=findViewById(R.id.btn_op);

        btn_op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_opd1.getText().length()>0&&input_opd2.getText().length()>0){
                    Intent i=new Intent(MainActivity.this,Activity_OP.class);
                    Bundle b=new Bundle() ;
                    b.putString("OPD1",input_opd1.getText().toString());
                    b.putString("OPD2",input_opd2.getText().toString());
                    i.putExtras(b);
//                    startActivityForResult(i,SET_RESULT); //舊式寫法 使用廣播方式
                    launcher_1.launch(i); //新式寫法,啟動器 啟動Intent
                }else{
                    text_output.setTextColor(0x330000FF);
                    text_output.setText("兩個運算元欄位都要有值才能運算ㄛ~");
                }

            }
        });
    }

    //Ctrl+o 選擇要overwrite的method
//    // protected void onActivityResult(int 請求碼, int 結果碼, @Nullable Intent Intent物件名稱)
//    //super.onActivityResult(requestCode, resultCode, i);<=父物件
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {
//        super.onActivityResult(requestCode, resultCode, i);
//
//        if(requestCode==SET_RESULT){ //請求碼是SET_RESULT(100)才往下做
//            if(resultCode==RESULT_OK){ //RESULT_OK值為-1
//                Bundle b=i.getExtras();
//                Double answer=b.getDouble("CALCULATE_RESULT") ;
//                text_output.setText("運算結果="+answer.toString());
//            }
//        }
//    }

}