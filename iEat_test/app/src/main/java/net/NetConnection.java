package net;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import common.Config;

/**
 * Created by 小巷有狗 on 2018/2/18.
 */


/**
 * 进行网络通信，进行消息的上传与下载
 */
public class NetConnection {

    public NetConnection(final String url, final HttpMethod method, final SuccessCallback successCallback, final FailCallback failCallback, final String... kvs) {

        new AsyncTask<Void, Void, String>() {//防止阻塞主线程
            @Override
            protected String doInBackground(Void... voids) {


                JSONObject jsonObject=new JSONObject();//创建json对象
                //将要发送的消息储存在json对象中
                for (int i=0;i<kvs.length;i+=2){
                    try {
                        jsonObject.put(kvs[i], URLEncoder.encode(kvs[i+1],"UTF-8"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                String jsonStr=jsonObject.toString();//把json对象俺json的编码格式转换为字符串
//
//                StringBuffer jsonObject = new StringBuffer();
//                jsonObject.append("{");
//                for (int i = 0; i < kvs.length; i += 2) {
//                    //将要发送的信息以键值对的形式存储起来
//                    jsonObject.append("\""+kvs[i]+"\":").append("\""+kvs[i + 1]+"\"").append(",");
//                }
//                jsonObject.append("}");

                try {
                    URLConnection uc;

                    switch (method) {//选择传送方式
                        case POST://POST方式以流的方式上传数据到服务器
                            uc = new URL(url).openConnection();
                            uc.setConnectTimeout(8000);//连接的超时时间
                            uc.setReadTimeout(3000);//设置响应的时间
                            uc.setDoOutput(true);//设置这个连接是否可以写出数据
                            uc.setRequestProperty("Content-Type","application/json;charset=UTF-8");//设置消息的类型
                            uc.connect();//连接，从上述至此的配置必须要在connect之前完成，实际上它只是建立了一个与服务器的TCP连接

                            OutputStream out=uc.getOutputStream();//输出流，用来发送请求，http请求实际上直到这个函数里面才发送出去
                            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(out));//创建字符流对象并用高效缓冲流包装它，便获得最高的效率,发送的是字符串推荐用字符流，其它数据就用字节流


//                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bw.write(jsonStr);//把json字符串写入缓冲区中
                            bw.flush();//刷新缓冲区，把数据发送出去
                            out.close();
                            bw.close();
                            break;
                        default:
                            uc = new URL(url + "?" + jsonObject.toString()).openConnection();
                            break;
                    }

                    System.out.println("Request url:" + uc.getURL());
                    System.out.println("Request data:" + uc.getURL());


                    //从服务器读取数据
                    BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), Config.CHARSET));
                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while ((line = br.readLine()) != null) {//一行一行读取数据
                        result.append(line);
                    }

                    System.out.println("Result:" + result);

                    return result.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }


            //对DoInbackground返回值响应
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (result != null) {
                    if (successCallback != null) {
                        successCallback.onSuccess(result);
                    }
                } else {
                    if (failCallback != null) {
                        failCallback.onFail(result);
                    }
                }
            }
        }.execute();
    }



    /**
     * result就是从服务器返回的数据
     */
    public static interface SuccessCallback {
        void onSuccess(String result);
    }

    public static interface FailCallback {
        void onFail(String result);
    }
}
