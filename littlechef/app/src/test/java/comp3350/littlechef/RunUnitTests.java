package comp3350.littlechef;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import comp3350.littlechef.business.BusinessTests;
import comp3350.littlechef.objects.ObjectTests;
import comp3350.littlechef.persistence.PersistenceTests;

public class RunUnitTests extends TestCase
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Unit tests");
        suite.addTest(ObjectTests.suite());
        suite.addTest(BusinessTests.suite());
        suite.addTest(PersistenceTests.suite());
        return suite;
    }
}

