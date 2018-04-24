package filter;

import db.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * 通过用户的口味来对推荐系统推荐的菜品进行筛选
 */
public class filter {
    SQLHelper helper=new SQLHelper();

    public filter(int[] userFlavour){
        this.userFlavour=userFlavour;
    }


    /**
     * 根据id获取系统推荐的菜的口味信息
     * @param foodId
     * @return
     */
    private int[] getFoodFlavour(int foodId){
        int[] flavour=new int[5];
        String sql="select * from flavour where foodId="+foodId+";";
        try {
            ResultSet resultSet=helper.queryRs(sql);
            while (resultSet.next()){
                int isSour=resultSet.getInt("isSour");
                int isSweet=resultSet.getInt("isSweet");
                int isBitter=resultSet.getInt("isBitter");
                int isSpicy=resultSet.getInt("isSpicy");
                int isSalty=resultSet.getInt("isSalty");

                flavour[0]=isSour;
                flavour[1]=isSweet;
                flavour[2]=isBitter;
                flavour[3]=isSpicy;
                flavour[4]=isSalty;
            }

            resultSet.close();
            helper.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return flavour;
    }

    /**
     * 用户的口味是由用户自己提供的？？？？
     */
//    private String getUserFlavour(int userId){
//        StringBuilder sb=new StringBuilder();
//        String sql="select "
//    }

    /**
     * 筛选菜品
     * 参数：推荐菜品的id列表
     * 返回值：经过筛选后得到的菜品id
     * @param recommond_list
     * @return
     */
    public List<Integer> doFilter(List recommond_list){
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<recommond_list.size();i++){
           int[] flavour=getFoodFlavour((Integer) recommond_list.get(i));
           if ((userFlavour[0]==0&&flavour[0]!=0)||
                   userFlavour[1]==0&&flavour[1]!=0||
                   (userFlavour[2]==0&&flavour[2]!=0)||
                   (userFlavour[3]==0&&flavour[3]!=0)||
                   (userFlavour[4]==0&&flavour[4]!=0)){
               list.remove(i);
           }
        }
        return list;
    }

    private int[] userFlavour=new int[4];//当前用户的口味，酸甜苦辣咸，若为0则表示该用户不吃该类菜

}
