package com.example.ahao.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mBtnButton1;
    private Button mBtnButton2;
    private EditText editText3;
    private EditText editText4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnButton1 =findViewById(R.id.btn_enter);
        editText3=findViewById(R.id.et_username);
        editText4=findViewById(R.id.et_password);
        mBtnButton1.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                    case R.id.btn_register1:
                        String inputText3 = editText3.getText().toString();
                        String inputText4 = editText4.getText().toString();
                        break;
                    default:
                        break;
                }
                //new一个对象
                final UrlGetTask urlGetTask=new UrlGetTask(MainActivity.this);
                //设置url
                urlGetTask.setUrl("http://101.132.156.198:8080/Novel/Login?name="+editText3.getText().toString()+"&pass="+editText4.getText().toString()+"");
                urlGetTask.setmSuccess(new UrlGetTask.onSuccessing() {
                    @Override
                    public void success(String s) {
                        urlGetTask.onNewIntent(ReadListViewActivity.class,false);
                    }
                });
                urlGetTask.execute();
            }

        });
        mBtnButton2 =findViewById(R.id.btn_register);
        mBtnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EditTextActivity.class);
                startActivity(intent);

            }
        });
    }
    public void showToast(View view){
        Toast.makeText(this,"李昊出品，必属精品",Toast.LENGTH_LONG).show();
    }
}
