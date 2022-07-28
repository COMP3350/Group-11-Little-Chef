package comp3350.littlechef.persistence;

import comp3350.littlechef.objects.Recipe;
import java.util.List;
import java.util.ArrayList;

// CLASS: DataAccess.java
// REMARKS: Defines the data access methods.
//-----------------------------------------
public interface DataAccess
{
    boolean open(String dbPath) ;
    boolean close();
    String insertRecipe(Recipe recipe);
    String updateRecipe(Recipe recipe);
    String getRecipeSequential(List<Recipe> name);
    ArrayList<Recipe> getRecipeRandom(Recipe recipe);
    String deleteRecipe(Recipe recipe);
    String resetDatabase();
}
