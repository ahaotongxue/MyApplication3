package com.example.ahao.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class EditTextActivity extends AppCompatActivity {
    private  Button mBtnButton3;
    private  EditText editText1;
    private  EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        mBtnButton3 =findViewById(R.id.btn_register1);
        editText1=findViewById(R.id.et_username1);
        editText2=findViewById(R.id.et_password1);
        mBtnButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_register1:
                        String inputText1=editText1.getText().toString();
                        String inputText2=editText2.getText().toString();
                        break;
                        default:
                            break;
                }
                //new一个对象
                final UrlGetTask urlGetTask=new UrlGetTask(EditTextActivity.this);
                //设置url
                urlGetTask.setUrl("http://101.132.156.198:8080/Novel/AddUser?name="+editText1.getText().toString()+"&pass="+editText2.getText().toString()+"");
                urlGetTask.setmSuccess(new UrlGetTask.onSuccessing() {
                    @Override
                    public void success(String s) {
                        urlGetTask.onNewIntent(MainActivity.class,false);
                    }
                });
                urlGetTask.execute();
            }
        });
    }
}
