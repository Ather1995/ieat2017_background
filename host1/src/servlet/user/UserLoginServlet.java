package servlet.user;

import dao.impl.userDAOimpl;
import dao.userDAO;
import net.sf.json.JSONObject;
import servlet.CommonResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private JSONObject requestParam=null;
    private JSONObject responseJSON=null;

    public UserLoginServlet(){

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

    public UserLoginServlet(JSONObject jsonObject){
        this.requestParam=jsonObject;
        responseJSON=new JSONObject();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        String account=requestParam.getString("account");
        String password =requestParam.getString("password");
        String type=requestParam.getString("login_type");

        CommonResponse res=new CommonResponse();
        userDAO userDAO=new userDAOimpl();
        try {
            String oauthAccessToken=userDAO.getAcess(account,type);
            if (oauthAccessToken!=null&&oauthAccessToken.equals(password)){
                System.out.println("登录成功！！");
                responseJSON.put("notice","登陆成功！！！");
                res.setResult("notice","登陆成功！！！");
            }else{
                System.out.println("账号或密码错误，请检查后重新登陆");
                responseJSON.put("notice","账号或密码错误，请检查后重新登录！！");
                res.setResult("notice","账号或密码错误，请检查后重新登录！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
