package comp3350.littlechef.business;

import java.security.Provider;
import java.util.ArrayList;

import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.persistence.PersistenceAccess;

public class DBController implements PersistenceAccess{

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

    @Override
    public void open(String dbPath) {
        service.open(dbPath);
    }

    @Override
    public void close() { service.close();}

    @Override
    public String addRecipe(Recipe recipe) {
        return service.addRecipe(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe currRecipe) {
        return service.updateRecipe(currRecipe);
    }

    @Override
    public Recipe getRecipe(String name) {
        return getService().getRecipe(name);
    }

    @Override
    public ArrayList<Recipe> getListOfSameRecipe(Recipe recipe) { return service.getListOfSameRecipe(recipe); }

    @Override
    public boolean delRecipe(Recipe recipe) {
        return delRecipe(recipe);
    }
}
