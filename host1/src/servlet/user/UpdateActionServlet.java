package servlet.user;

import dao.impl.updateActionDAOimpl;
import dao.updateActionDAO;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateActionServlet {

    public UpdateActionServlet(JSONObject object) throws SQLException {
        System.out.println("UpdateActionServlet");
        updateActionDAO updateActionDAO=new updateActionDAOimpl();
        updateActionDAO.updateAction();

    }

//    public JSONObject getResponse(HttpServletRequest request, HttpServletResponse response) {
//    }

}
