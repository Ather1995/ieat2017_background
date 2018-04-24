package servlet.user;

import dao.impl.updateActionDAOimpl;
import dao.impl.updateFinalScoreDAOimpl;
import dao.updateFinalScoreDAO;
import net.sf.json.JSONObject;

import java.sql.SQLException;

public class UpdateFinalScoreServlet {
    public UpdateFinalScoreServlet(JSONObject object) throws SQLException {
        System.out.println("UpdateFinalScoreServlet");
        updateFinalScoreDAO updateFinalScoreDAO=new updateFinalScoreDAOimpl();
        updateFinalScoreDAO.updateFinalScore();

    }
}
