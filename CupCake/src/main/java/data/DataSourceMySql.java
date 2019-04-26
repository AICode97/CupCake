package data;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author Martin Frederiksen
 */
public class DataSourceMySql {

    private MysqlDataSource dataSource = new MysqlDataSource();
    private MysqlDataSource localDataSource = new MysqlDataSource();

    public DataSourceMySql() {
        {
            try {
                dataSource.setServerName("157.230.110.164");
                //dataSource.setServerName("andreasvikke.dk");
                dataSource.setPort(3306);
                dataSource.setDatabaseName("cupcake");
                dataSource.setUser("transform");
                dataSource.setPassword("transform");
                //dataSource.setUser("transformer");
                //dataSource.setPassword("f7qGtArm");
                dataSource.setUseSSL(false);
                
                localDataSource.setServerName("localhost");
                localDataSource.setPort(3306);
                localDataSource.setDatabaseName("cupcake");
                localDataSource.setUser("transform");
                //localDataSource.setUser("transformer");
                localDataSource.setPassword("transform");
                //localDataSource.setPassword("f7qGtArm");
                localDataSource.setUseSSL(false);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }
}
