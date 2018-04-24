package servlet.user;

import config.Config;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import servlet.Response_to_Clinent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * 解析App发来的json数据
 */
@WebServlet(name = "ParseJSONServlet")
public class ParseJSONServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");




        System.out.println(request.getContentType());//得到客户端发过来内容的类型
        System.out.println(request.getRemoteAddr());//得到客户端的ip地址
        //使用字符流来读取客户端发来的数据
        //获取App发来的报文并将其解析成json格式
        BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line=null;
        StringBuffer s=new StringBuffer();
        while ((line=br.readLine())!=null){
            s.append(line);
        }
        br.close();

        System.out.println("客户端发来的内容为:"+s.toString());
        JSONObject object=JSONObject.fromObject(s.toString());

        //测试
//        JSONObject object=new JSONObject();
//        object.put("request_type","login");
//        object.put("login_type","tel");
//        object.put("account","彭敦福");
//        object.put("password","123456");


        String request_type = object.getString(Config.REQUEST_TYPE);

        JSONObject responseJSON=new JSONObject();//创建返回客户端的json数据

        switch (request_type) {
            case Config.LOGIN:
                System.out.println("LOGIN!!!!!!");
                UserLoginServlet loginServlet=new UserLoginServlet(object);
                responseJSON=loginServlet.getResponse(request,response);
                break;
            case Config.REGISTER:
                System.out.println("REGISTER!!!!!!");
                UserRegisterServlet registerServlet=new UserRegisterServlet(object);
                responseJSON=registerServlet.getResponse(request,response);
                break;
            case Config.FOOD_CLICK:
                System.out.println("food!!!!!!");
                FoodClickServlet foodClickServlet=new FoodClickServlet(object);
                responseJSON=foodClickServlet.getResponse(request,response);
                break;
            case Config.UPDATE_ACTION:
                System.out.println("UPDATE_ACTION!!!!!!");
//                UpdateActionServlet updateActionServlet= null;
//                try {
//                    updateActionServlet = new UpdateActionServlet(object);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                UpdateFinalScoreServlet updateFinalScoreServlet= null;
                try {
                    updateFinalScoreServlet = new UpdateFinalScoreServlet(object);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
        System.out.println(responseJSON.toString());
        response.getOutputStream().write(responseJSON.toString().getBytes("UTF-8"));//向客户端发送一个带有json对象内容的响应
    }
}
