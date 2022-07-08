package comp3350.littlechef.persistence;
import comp3350.littlechef.objects.Recipe;



import java.util.ArrayList;

public interface PersistenceAccess {
    public void open(String dbPath) ;
    public void close();
    public String addRecipe(Recipe recipe);
    public String updateRecipe(Recipe currRecipe);
    public Recipe getRecipe(String name);
    public ArrayList<Recipe> getListOfSameRecipe(Recipe recipe);
    public boolean delRecipe(Recipe recipe);
}
