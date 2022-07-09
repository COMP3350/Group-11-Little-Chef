package comp3350.littlechef.business;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
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
            public String updateRecipe(Recipe currRecipe) {return null;}
            public String getRecipeSequential(List<Recipe> name) {return null;}
            public ArrayList<Recipe> getRecipeRandom(Recipe recipe) {return null;};
            public String deleteRecipe(Recipe recipe) {return null;}
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
    public String updateRecipe(Recipe currRecipe) {
        return service.updateRecipe(currRecipe);
    }

    @Override
    public String getRecipeSequential(List<Recipe> name) { return getService().getRecipeSequential(name); }

    @Override
    public ArrayList<Recipe> getRecipeRandom(Recipe recipe) { return getService().getRecipeRandom(recipe); }

    @Override
    public String deleteRecipe(Recipe recipe) {
        return deleteRecipe(recipe);
    }
}
