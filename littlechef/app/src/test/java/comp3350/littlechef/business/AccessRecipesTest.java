package comp3350.littlechef.business;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.persistence.DataAccessStub;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class AccessRecipesTest extends TestCase
{
    private String dbName = Main.dbName;
    DataAccessStub testAccess;

    public AccessRecipesTest(String arg0)
    {
        super(arg0);
    }

    @BeforeClass
    private void start()
    {

        //SWITCH BETWEEN REAL DB AND STUB

        //STUB
        //testAccess = new PersistenceAccess(dbName);

        //REAL
        testAccess = new DataAccessStub(dbName);
    }



    @Test
    public void testSimple()
    {
        //test the file could run or not
        assertEquals(1,1);
    }

    @Test
    public void testAccessRecipesCreation()
    {
        start();

        // DataAccessStub.close;


        AccessRecipes accrep1 = new AccessRecipes();
        assertNotNull(accrep1);

        AccessRecipes accrep2 = new AccessRecipes();

        ArrayList<AccessRecipes>inList = new ArrayList<>();
        inList.add(accrep1);

        assertEquals(inList.size(), 1);




    }
}
