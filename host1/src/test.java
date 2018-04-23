import net.sf.json.JSONObject;

public class test {


    public static void main(String[] args){
        String str="{\"type\":\"login\",\"name\":\"pdf\",\"psd\":\"123456789\"}";
        JSONObject jsonObject=JSONObject.fromObject(str);

        String type=jsonObject.getString("type");
        System.out.println(type);

//        JSONObject jsonObject1=jsonObject.getJSONObject(str);
        String name=jsonObject.getString("name");
        System.out.println(name);

    }
}
