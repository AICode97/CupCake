package data;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author Martin Frederiksen
 */
public class DataSourceMySql {

    private MysqlDataSource dataSource = new MysqlDataSource();
    
    public DataSourceMySql(){
        {
            try{
            dataSource.setServerName("andreasvikke.dk");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("cupcake");
            dataSource.setUser("transformer");
            dataSource.setPassword("f7qGtArm");
            dataSource.setUseSSL(false);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }
}
