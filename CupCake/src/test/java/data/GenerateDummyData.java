package data;

import com.github.javafaker.Faker;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.ArrayList;
import junit.framework.TestCase;
import logic.UserController;
import logic.model.enums.RoleEnum;

/**
 *
 * @author Andreas Vikke
 */
public class GenerateDummyData extends TestCase {

    public GenerateDummyData(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Creates Test Data in Database
     */
    public void testCreateTestData() {
        MysqlDataSource ds = new DataSourceMySql().getDataSource();
        createUserTestData(ds);
        assertTrue(true);
    }

    private void createUserTestData(MysqlDataSource ds) {
        UserController uc = new UserController(ds);
        Faker faker = new Faker();

        uc.addUser("admin", "admin@gmail.com", "1234", RoleEnum.ADMIN);

        ArrayList<String> usedNames = new ArrayList();
        ArrayList<String> usedEmails = new ArrayList();
        for (int i = 0; usedNames.size() < 100; i++) {
            String name = faker.name().firstName();
            String email = faker.artist().name() + "@gmail.com";
            if (!usedNames.contains(name) && !usedEmails.contains(email)) {
                usedNames.add(name);
                usedEmails.add(email);
                uc.addUser(name, email, "1234", RoleEnum.CUSTOMER);
                System.out.println(usedNames.size());
            }
        }
    }
}
