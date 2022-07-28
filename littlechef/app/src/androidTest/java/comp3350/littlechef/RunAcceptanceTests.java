package comp3350.littlechef;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.littlechef.acceptance.HomeScreenAcceptanceTests;
import comp3350.littlechef.acceptance.ReadRateAddRecipeAcceptanceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({HomeScreenAcceptanceTests.class, ReadRateAddRecipeAcceptanceTest.class})
public class RunAcceptanceTests
{
    public RunAcceptanceTests()
    {
        System.out.println("Sample Acceptance tests");
    }
}
