package com.example.ahao.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LocalActivity extends AppCompatActivity {

    private EditText et_folder;			//输入的文件夹名
    private Button bt_open;				//打开按钮
    private Button bt_clear;			//清除按钮
    private EditText et_filename;		//用于显示文件名
    private EditText et_filecontent;	//用于显示txt文件内容

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        et_folder = (EditText) findViewById(R.id.ET_Folder);
        et_filename = (EditText) findViewById(R.id.ET_FileName);
        et_filecontent = (EditText) findViewById(R.id.ET_FileContent);

        bt_open = (Button) findViewById(R.id.But_Open);
        bt_open.setOnClickListener(new View.OnClickListener(){//打开按钮监听
            public void onClick(View arg0) {
                //若输入的文件夹名为空
                if(et_folder.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "输入为空",Toast.LENGTH_SHORT).show();
                }else{
                    // 获得SD卡根目录路径 "/sdcard"
                    File sdDir = Environment.getExternalStorageDirectory();
                    File path = new File(sdDir+File.separator
                            +et_folder.getText().toString().trim());

                    // 判断SD卡是否存在，并且是否具有读写权限
                    if (Environment.getExternalStorageState().
                            equals(Environment.MEDIA_MOUNTED)) {
                        File[] files = path.listFiles();// 读取文件夹下文件
                        et_filename.setText("");
                        et_filecontent.setText("");

                        et_filename.setText(getFileName(files));
                        et_filecontent.setText(getFileContent(files));
                    }
                }
            }
        });
        bt_clear = (Button) findViewById(R.id.But_Clear);
        bt_clear.setOnClickListener(new View.OnClickListener(){//清除按钮监听
            public void onClick(View arg0) {
                et_folder.setText("");
                et_filename.setText("");
                et_filecontent.setText("");
            }
        });
    }
    //读取指定目录下的所有TXT文件的文件内容
    protected String getFileContent(File[] files) {
        String content  = "";
        if (files != null) {	// 先判断目录是否为空，否则会报空指针
            for (File file : files) {
                //检查此路径名的文件是否是一个目录(文件夹)
                if (file.isDirectory()) {
                    Log.i("zeng", "若是文件目录。继续读1" +
                            file.getName().toString()+ file.getPath().toString());
                    getFileContent(file.listFiles());
                    Log.i("zeng", "若是文件目录。继续读2" +
                            file.getName().toString()+ file.getPath().toString());
                } else {
                    if (file.getName().endsWith(".txt")) {//格式为txt文件
                        try {
                            InputStream instream = new FileInputStream(file);
                            if (instream != null) {
                                InputStreamReader inputreader =
                                        new InputStreamReader(instream, "GBK");
                                BufferedReader buffreader =
                                        new BufferedReader(inputreader);
                                String line="";
                                //分行读取
                                while (( line = buffreader.readLine()) != null) {
                                    content += line + "\n";
                                }
                                instream.close();
                            }
                        }
                        catch (java.io.FileNotFoundException e) {
                            Log.d("TestFile", "The File doesn't not exist.");
                        }
                        catch (IOException e)  {
                            Log.d("TestFile", e.getMessage());
                        }

                    }
                }
            }
        }
        return content ;
    }
    //读取指定目录下的所有TXT文件的文件名
    private String getFileName(File[] files) {
        String str = "";
        if (files != null) {	// 先判断目录是否为空，否则会报空指针
            for (File file : files) {
                if (file.isDirectory()){//检查此路径名的文件是否是一个目录(文件夹)
                    Log.i("zeng", "若是文件目录。继续读1"
                            +file.getName().toString()+file.getPath().toString());
                    getFileName(file.listFiles());
                    Log.i("zeng", "若是文件目录。继续读2"
                            +file.getName().toString()+ file.getPath().toString());
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".txt")) {
                        String s=fileName.substring(0,fileName.lastIndexOf(".")).toString();
                        Log.i("zeng", "文件名txt：：   " + s);
                        str += fileName.substring(0,fileName.lastIndexOf("."))+"\n";
                    }
                }
            }
        }
        return str;
    }
}

