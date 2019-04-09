package com.example.ahao.myapplication.temp;

        import android.content.Context;
        import android.graphics.Paint;
        import android.util.Log;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.InputStreamReader;
        import java.text.DecimalFormat;


/**
 * Created by Ahao on 2019/3/3
 */
public class Tools {

    public static int dip2px(Context context, int dp)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }

    public static int px2dip(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int px2sp(Context context, int pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String readTxtFile(String filePath){
        String txt="";
        try {
            String encoding="GBK";
            File file=new File(filePath);

            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    txt+=lineTxt+"\n";
                }
                read.close();
            }else{
                Log.e("asdasdasd","没找到文件");
            }
        } catch (Exception e) {
            Log.e("asdasdasd","读取文件内容出错");
            e.printStackTrace();
        }
        return txt;
    }

    public static String getFloat2(float a)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(a);
    }

    public static int getPages(String txt)
    {
        Paint paint=MyConfig.getInstance().getPaint();

        if(txt==null||txt.equals(""))
            return 0;
        int manyRow=MyConfig.getInstance().getManyRow();
        int lines=0;
        int width=MyConfig.getInstance().getWidth();
        String[] parts=txt.split("\n");

        for(int j=0;j<parts.length;j++)
        {
            if(parts[j].equals(""))
            {
                continue;
            }
            int oldPos=0;
            while (true)
            {
                int breadText = paint.breakText(parts[j].substring(oldPos), true, width,null);
                oldPos+=breadText;
                lines++;
                if(oldPos>=parts[j].length())
                    break;
            }
        }

        double a=(double)lines/(double)manyRow;
        return (int)Math.ceil(a);
    }
}
