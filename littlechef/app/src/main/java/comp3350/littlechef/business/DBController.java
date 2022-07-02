package comp3350.littlechef.business;

import java.security.Provider;
import java.util.ArrayList;

import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.persistence.PersistenceAccess;

public class DBController {

    private PersistenceAccess service;

    //default constructor
    public DBController() {
        service = new PersistenceAccess() {
            public void open(String dbPath) {}
            public void close() {}
            public String addRecipe(Recipe recipe) {return null;}
            public Recipe updateRecipe(Recipe currRecipe) {return null;}
            public Recipe getRecipe(String name) {return null;}
            public ArrayList<Recipe> getListOfSameRecipe(Recipe recipe) {return null;}
            public boolean delRecipe(Recipe recipe) {return false;}
        };
    }

    public void setService(PersistenceAccess newService)
    {
        service = newService;
    }

    public PersistenceAccess getService()
    {
        return service;
    }
}
