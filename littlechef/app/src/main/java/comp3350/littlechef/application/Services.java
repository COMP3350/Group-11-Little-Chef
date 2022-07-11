package comp3350.littlechef.application;

import comp3350.littlechef.persistence.DataAccess;
import comp3350.littlechef.persistence.DataAccessObject;

public class Services
{
    private static DataAccess dataAccessService = null;

    public static DataAccess createDataAccess(String dbName)
    {
        validatePath(dbName);
        dbName = dbName.trim();

        if(dataAccessService == null)
        {
            dataAccessService = new DataAccessObject(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccess createDataAccess(DataAccess alternateDataAccessService)
    {
        if(alternateDataAccessService == null)
        {
            throw new NullPointerException("Alternate Data Access Service cannot be null.");
        }

        if (dataAccessService == null)
        {
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccess getDataAccess(String dbName)
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

    private static void validatePath(String path)
    {
        if(path == null)
        {
            throw new NullPointerException("Path cannot be null.");
        }

        path = path.trim();

        if(path.length() == 0)
        {
            throw new IllegalArgumentException("Path cannot be an empty String.");
        }
    }
}
