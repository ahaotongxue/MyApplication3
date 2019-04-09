package com.example.ahao.myapplication.temp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ahao on 2019/3/2.
 */
public class UrlGetTask extends AsyncTask<String,Integer,String> {
    public static final int FAILD_INTERNET=0;
    public static final int FAILD_LOAD=1;
    private ProgressDialog progressBar;
    private String onInternetfailmsg;
    private static Context context;
    private boolean log;
    private String url;
    private onSuccessing mSuccess;
    private onFailing mFail;
    @Override
    protected void onPreExecute() {
        if(progressBar!=null)
        progressBar.show();
        super.onPreExecute();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProgressBar(ProgressDialog progressBar) {
        this.progressBar = progressBar;
    }

    public void setOnInternetfailmsg(String onInternetfailmsg) {
        this.onInternetfailmsg = onInternetfailmsg;
    }

    public static void setContext(Context context) {
        UrlGetTask.context = context;
    }

    public void setLog(boolean log) {
        this.log = log;
    }

    public void showProgressBar(boolean show, String msg) {
        if(show)
        {
            progressBar=new ProgressDialog(context);
            progressBar.setCancelable(false);
            progressBar.setMessage(msg+"......");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        else
        {
            progressBar=null;
        }
    }

    public static String jsonTwoForOne(String parent, String child){
        String s="";
        try {
            JSONObject myJsonObject = new JSONObject(parent);
            s=myJsonObject.getString(child);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }
    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String tempResult = null;
            String result = null;
            while ((tempResult = br.readLine()) != null) {
                result += tempResult + "\n";
            }
            if (result != null) {
               // result = result.replace("\\", "");
                result = result.substring(0, result.length() - 1);
            }
            br.close();
            isr.close();
            return result.substring(4);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public UrlGetTask(Context context, String msg, String onInternetfailmsg)
    {
       this(context,msg,onInternetfailmsg,true);
    }

    public UrlGetTask(Context context) {
        this(context,null,null,true);
    }

    public UrlGetTask(Context context, String msg, String onInternetfailmsg, boolean uselog)
    {
        if(context==null)
            return;
        if(msg==null)
            msg="请等待";
        this.context=context;
        progressBar=new ProgressDialog(context);
        progressBar.setCancelable(false);
        progressBar.setMessage(msg+"......");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.onInternetfailmsg=onInternetfailmsg;
        if(onInternetfailmsg==null)
            this.onInternetfailmsg="";
        log=uselog;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(log)
        Log.i("请求联网日志:","请求类:"+context.toString()+"回应内容："+s);
        if(progressBar!=null)
        progressBar.dismiss();
        if(s==null)
        {
            if(mFail!=null)
                mFail.fail(s,FAILD_INTERNET);
            onInternetFail();//联网失败
        }
        else if(jsonTwoForOne(s,"retcode").equals("1"))
        {
            if(mSuccess!=null)
                mSuccess.success(s);
            onSuccess(s);//成功
        }
        else
        {
            if(mFail!=null)
                mFail.fail(s,FAILD_LOAD);
            onLoadfail(s);//失败
        }
    }

    protected void onLoadfail(String s) {
        Toast.makeText(context, jsonTwoForOne(s,"msg"), Toast.LENGTH_SHORT).show();
    }

    protected void onSuccess(String s) {
        Toast.makeText(context, jsonTwoForOne(s,"msg"), Toast.LENGTH_SHORT).show();
    }

    protected void onInternetFail() {
        Toast.makeText(context,onInternetfailmsg+"请检查网络链接", Toast.LENGTH_SHORT).show();
    }

    protected void onNewIntent(Class newtask, boolean clear){
        Intent intent=new Intent(context,newtask);
        if(clear)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public interface onSuccessing
    {
       void success(String s);
    }
    public interface onFailing
    {
        void fail(String s, int why);
    }

    public void setmSuccess(onSuccessing mSuccess) {
        this.mSuccess = mSuccess;
    }

    public void setmFail(onFailing mFail) {
        this.mFail = mFail;
    }
}
