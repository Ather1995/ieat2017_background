package servlet;

import net.sf.json.JSONObject;

public class Response_to_Clinent {

    JSONObject jsonObject=null;
    String content=null;

    public Response_to_Clinent() {
        this.jsonObject = new JSONObject();
        this.content = "";
    }

//    public void setResult()
    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
