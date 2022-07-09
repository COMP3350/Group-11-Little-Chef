package comp3350.littlechef.persistence;

import java.util.ArrayList;

import java.sql.*;
import java.util.*;

import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;
import comp3350.littlechef.objects.UnitType;

public class PersistenceAccessDB implements PersistenceAccess {

    private String cmd, cmd2, cmd3, cmd4, cmd5;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    private String databasePath;
    private Connection connection;
    private ResultSet resultSet, resultSet2, resultSet3, resultSet4, resultSet5;
    private String dbType;
    private Statement statement;

    private ArrayList<Recipe> recipes;
    private ArrayList<Ingredient> ingredients;

    public PersistenceAccessDB(String dbPath) {
        databasePath = dbPath;
    }

    @Override
    public void open(String dbPath) {

        // Ask Briaco: is it okay to do this?
        if(dbPath == null)
        {
            throw new NullPointerException("dbPath cannot be null.");
        }

        if(dbPath == "")
        {
            throw new IllegalArgumentException("dbPath cannot be an empty string.");
        }

        try {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();

            System.out.println("Connecting to selected database...");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:", "SA", "");
            statement = connection.createStatement();
            System.out.println("Connection built successfully.");

        }
        catch (Exception se)
        {
            se.printStackTrace();
        }
        finally
        {
            try
            {
                if (statement != null)
                {
                    connection.close();
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
        }

        System.out.println(dbType + " type's database opened successfully.");
    }//end open

    @Override
    public void close() {
        try {
            String cmdString = "shutdown compact";
            resultSet = statement.executeQuery(cmdString);
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(dbType + " type's database closed.");
    }//end close

    @Override
    public String addRecipe(Recipe recipe)
    {
        String values;
        result = null;

        try
        {
            values = recipe.getId()
                    + ", '" + recipe.getName()
                    + ", '" + recipe.getIngredients()
                    + ", '" + recipe.getInstructions()
                    + ", '" + recipe.getAverageCookingTime()
                    + ", '" + recipe.getDifficultyRating()
                    + ", '" + recipe.getTasteRating()
                    + "'";

            cmd = "INSERT INTO RECIPES " + " VALUES (" + values + ")";

            // Kajal comment: should we break this down into two statements so its not chained like this?
            // also what is resultSet used for? Can we switch it for result like in the sample so that I can use it for error checking?
            updateCount = statement.executeUpdate(cmd);
            result = checkWarning(statement, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result; // why returning name?
    }//end addRecipe


    @Override
    public String updateRecipe(Recipe recipe) {
        //I think it should return Recipe, because we are update and get a recipe with new information
        String values;
        String where;
        result = null;

        try
        {
            values = "Name='" + recipe.getName()
                    + "', Ingredients='" + recipe.getIngredients()
                    + "', Instructions='" + recipe.getInstructions()
                    + "', AverageCookingTime='" + recipe.getAverageCookingTime()
                    + "', DifficultyRating='" + recipe.getDifficultyRating()
                    + "', TasteRating='" + recipe.getTasteRating()
                    + "'";
            where = "WHERE RECIPEID=" + recipe.getId();
            cmd = "UPDATE RECIPES " + " SET " + values + " " + where;
            //System.out.println(cmdString);
            updateCount = statement.executeUpdate(cmd);
            result = checkWarning(statement, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }//end updateRecipe

    @Override
    public String getRecipeSequential(List<Recipe> recipeList) {

        Recipe recipe = null;
        String myID = EOF;
        result = null;

        try
        {
            cmd = "SELECT * FROM RECIPE";
            resultSet = connection.createStatement().executeQuery(cmd);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while(resultSet.next())
            {
                myID = resultSet.getString("RecipeID");
                recipe = new Recipe(Integer.parseInt(myID));
                recipeList.add(recipe);
            }
        }
        catch (Exception se)
        {
            se.printStackTrace();
        }

        return result;
    }//end getRecipe

    @Override
    public ArrayList<Recipe> getRecipeRandom(Recipe newRecipe)
    {
        Recipe recipe;
        String myID;

        recipes = new ArrayList<Recipe>();
        try
        {
            cmd = "SELECT * FROM INGREDIENTS WHERE RECIPEID=" + newRecipe.getId() + "'";
            resultSet = statement.executeQuery(cmd);
            // ResultSetMetaData md2 = rs3.getMetaData();
            while (resultSet.next())
            {
                myID = resultSet.getString("RecipeID");
                recipe = new Recipe(Integer.parseInt(myID));
                recipes.add(recipe);
            }
            resultSet.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return recipes;
    }

    @Override
    public String deleteRecipe(Recipe recipe) {
        int recipeId;
        result = null;
        try{
            recipeId = recipe.getId();
            cmd = "DELETE FROM RECIPE WHERE RECIPEID ='"+recipeId+"'";
            connection.createStatement().executeUpdate(cmd);
            System.out.println("Deleted successfully.");
            result = checkWarning(statement, updateCount);
        }catch (Exception e){
            result = processSQLError(e);
        }
        return result;
    }//end delRecipe

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }

}