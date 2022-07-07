package comp3350.littlechef.persistence;

import static comp3350.littlechef.persistence.PersistenceAccessDB.*;
import static comp3350.littlechef.persistence.DataAccessStub.*;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

// might not need
import comp3350.littlechef.application.Main;
import comp3350.littlechef.application.Services;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;

public class PersistenceAccessDBTest extends TestCase
{

    private DataAccessStub dbAccess;
    // private PersistenceAccessDB dataAccess;

    // create an empty db for the empty tests

    public void setUp()
    {
        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        // Use the following statements to run with the stub database:
        dbAccess = new DataAccessStub();
        dbAccess.open(Main.dbName);

        // or switch to the real database:
        // dbAccess = new PersistenceAccessDB(Main.dbName);
        // dbAccess.open(Main.getDBPathName());
    }

    public void tearDown()
    {
        System.out.println("Finished Persistence test DataAccess (using stub)");
        dbAccess.close();
    }

    @Test
    public void testAccessRecipesSimple() {

    }

    //


    // test to make sure open throws the right exceptions
    // test to make sure close throws the right exceptions

}
