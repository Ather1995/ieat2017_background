package recommend;

import com.google.common.base.Preconditions;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLBooleanPrefJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.AllSimilarItemsCandidateItemsStrategy;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.PoolableConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.PoolingDataSource;
import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.util.List;

public class itemCF {

    /**
     * 基于itemCF的推荐算法
     * use the MYSQL database as the input for MAHOUT
     */
    public static void main(String[] args) throws TasteException {

        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "0305hua";
        String url="jdbc:mysql://localhost:3306/project_db_sql";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url+"?useSSL=true");
        dataSource.setUser(user);
        dataSource.setPassword(password);


        JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource, "finalScore", "userId", "foodId", "score", "timeStamp");
        //利用ReloadFromJDBCDataModel包裹jdbcDataModel,可以把输入加入内存计算，加快计算速度。
        ReloadFromJDBCDataModel model = new ReloadFromJDBCDataModel(dataModel);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(model);
        AllSimilarItemsCandidateItemsStrategy candidateStrategy = new AllSimilarItemsCandidateItemsStrategy(itemSimilarity);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, itemSimilarity,candidateStrategy,candidateStrategy);
        //给用户ID等于5的用户推荐10个与2398相似的商品
        List<RecommendedItem> recommendedItemList = recommender.recommendedBecause(5, 666, 10);
        //打印推荐的结果
        System.out.println("使用基于物品的协同过滤算法");
        System.out.println("根据用户5当前浏览的商品450，推荐10个相似的商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(recommendedItem);
        }
        long start = System.currentTimeMillis();
        recommendedItemList = recommender.recommendedBecause(0, 495, 10);
        //打印推荐的结果
        System.out.println("使用基于物品的协同过滤算法");
        System.out.println("根据用户5当前浏览的商品34，推荐10个相似的商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(recommendedItem);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

//    public ConnectionPoolDataSource getDataSource(DataSource underlyingDataSource) {
//        Preconditions.checkNotNull(underlyingDataSource);
//        ConnectionFactory connectionFactory = new ConfiguringConnectionFactory(underlyingDataSource);
//        GenericObjectPool objectPool = new GenericObjectPool();
//        objectPool.setTestOnBorrow(false);
//        objectPool.setTestOnReturn(false);
//        objectPool.setTestWhileIdle(true);
//        objectPool.setTimeBetweenEvictionRunsMillis(60 * 1000L);
//        // Constructor actually sets itself as factory on pool
//        new PoolableConnectionFactory(connectionFactory, objectPool, null, "SELECT 1", false, false);
//        PoolingDataSource delegate = new PoolingDataSource(objectPool);
//    }

}
