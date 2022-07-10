package comp3350.littlechef.application;

import comp3350.littlechef.persistence.DataAccessStub;
import comp3350.littlechef.persistence.PersistenceAccess;
import comp3350.littlechef.persistence.PersistenceAccessDB;

public class Services
{
    private static PersistenceAccess dataAccessService = null;

    public static PersistenceAccess createDataAccess(String dbName)
    {
        if(dataAccessService == null)
        {
            dataAccessService = new PersistenceAccessDB(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static PersistenceAccess getDataAccess(String dbName)
    {
        if(dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess()
    {
        if(dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
