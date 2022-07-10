package comp3350.littlechef.business;

import java.util.ArrayList;
import java.util.List;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.persistence.DataAccess;

public class DBController implements DataAccess {

    private DataAccess service;

    //default constructor
    public DBController() {
        service = new DataAccess() {
            public void open(String dbPath) {}
            public void close() {}
            public String insertRecipe(Recipe recipe) {return null;}
            public String updateRecipe(Recipe currRecipe) {return null;}
            public String getRecipeSequential(List<Recipe> name) {return null;}
            public ArrayList<Recipe> getRecipeRandom(Recipe recipe) {return null;};
            public String deleteRecipe(Recipe recipe) {return null;}
        };
    }

    public void setService(DataAccess newService)
    {
        service = newService;
    }

    public DataAccess getService()
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
    public String insertRecipe(Recipe recipe) {
        return service.insertRecipe(recipe);
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
