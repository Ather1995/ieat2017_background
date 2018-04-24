package servlet.user;

import bean.user;
import config.Config;
import dao.impl.userDAOimpl;
import dao.userDAO;
import net.sf.json.JSONObject;
import servlet.CommonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    public JSONObject jsonObject=null;
    public JSONObject responseJSON=null;

    public UserRegisterServlet(){

    }

    public JSONObject getResponse(HttpServletRequest request, HttpServletResponse response){
        try {
            this.doPost(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseJSON;
    }

    public UserRegisterServlet(JSONObject jsonObject){
        this.jsonObject=jsonObject;
        responseJSON=new JSONObject();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");



        String account = jsonObject.getString("account");
        String password = jsonObject.getString("password");
        String register_type = jsonObject.getString("register_type");//获取注册类型


        user user = new user();
        user.setType(register_type);
        user.setOauthAccessToken(password);
        switch (register_type) {
            case Config.TEL:
                user.setUserTel(account);
                break;
            case Config.WEICHAT:
                user.setUserWeiChat(account);
                break;
            case Config.QQ:
                user.setUserQQ(account);
                break;
        }

        CommonResponse res = new CommonResponse();
        userDAO userDAO = new userDAOimpl();
        if (userDAO.addUser(user, register_type)) {
            System.out.println("注册成功！！");
            responseJSON.put("notice","注册成功！！");
            res.setResult("notice", "注册成功！！");
        } else {
            System.out.println("注册失败！");
            responseJSON.put("notice","注册失败，请稍候重试！！！");
            res.setResult("notice", "注册失败，请稍后重试！！");
        }
    }
}
