package comp3350.littlechef;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.srsys.tests.acceptance.SampleAcceptanceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({SampleAcceptanceTests.class})
public class RunAcceptanceTests
{
    public RunAcceptanceTests()
    {
        System.out.println("Sample Acceptance tests");
    }
}
