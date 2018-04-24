package net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class parseJSON {


    /**
     * 解析服务器发送来的json数据
     * 数据格式为{'data':[{'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'},
     * {'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'},
     * {'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'}]}
     * @param data
     */
    public void parse(String data){
        try {
            JSONObject jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            int arrayLength=jsonArray.length();
            for (int i=0;i<arrayLength;i++){
                JSONObject json=jsonArray.getJSONObject(i);
                System.out.println(i+": 菜品："+json.getString("foodName")+"  "+json.getString("foodMaterial")+"   "+json.getString("imageURL"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
