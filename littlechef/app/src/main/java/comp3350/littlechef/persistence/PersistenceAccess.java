package main.java.comp3350.littlechef.persistence;

import java.util.*;

import main.java.comp3350.littlechef.objects.*;

public interface PersistenceAccess {

    /*** Initial DB access ***/
    public void open(String dbPath);

    /*** close DB access ***/
    public void close();

    /*** add a recipe ***/
    public String addRecipe(Recipe recipe);

    /*** update Recipe ***/
    public Recipe updateRecipe(Recipe currRecipe);

    /*** get recipe by name ***/
    public Recipe getRecipe(String name);

    /*** get a list of recipe ***/
    public ArrayList<Recipe> getListOfSameRecipe(Recipe recipe);

    /*** delete a recipe ***/
    public boolean delRecipe(Recipe currRecipe);

}
