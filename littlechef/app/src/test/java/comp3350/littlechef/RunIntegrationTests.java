package comp3350.littlechef;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import comp3350.littlechef.integration.IntegrationTests;

public class RunIntegrationTests extends TestCase
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTest(IntegrationTests.suite());
        return suite;
    }
}