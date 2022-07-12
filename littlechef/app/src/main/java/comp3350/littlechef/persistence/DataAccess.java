package comp3350.littlechef.persistence;
import comp3350.littlechef.objects.Recipe;
import java.util.List;
import java.util.ArrayList;

public interface DataAccess
{
    public boolean open(String dbPath) ;
    public boolean close();
    public String insertRecipe(Recipe recipe);
    public String updateRecipe(Recipe recipe);
    public String getRecipeSequential(List<Recipe> name);
    public ArrayList<Recipe> getRecipeRandom(Recipe recipe);
    public String deleteRecipe(Recipe recipe);
    public String resetDatabase();
}
