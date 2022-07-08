package comp3350.littlechef.persistence;

import java.util.ArrayList;

import java.sql.*;
import java.util.*;

import comp3350.littlechef.objects.Recipe;

public class PersistenceAccessDB implements PersistenceAccess {

    private String databasePath;
    private Connection connection;
    private String cmd;
    private ResultSet resultSet;
    private String dbType;
    private Statement statement;

    private String result;

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
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "User", "");
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
            connection.createStatement().executeQuery("close");
            resultSet.close();
            statement.close();
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
        String name, ingredients, steps, timeToMake, difficulty, rate, quality, instruction;
        result = null;

        try
        {
            name = recipe.getName();
            ingredients = recipe.getIngredients().toString();
            steps = recipe.getInstructions().toString();
            timeToMake = recipe.getAverageCookingTime();
            difficulty = recipe.getDifficultyRating() + "";//since last time we discussed we want to make it as user-defined string, use +""
            quality = recipe.getTasteRating() + "";//same as difficulty
            rate = recipe.getRatingString();
            instruction = "'" + name + "','" + ingredients + "','" + steps + "'," + timeToMake + ",'" + difficulty + "','" + quality + "'," + rate;

            cmd = "INSERT INTO RECIPES VALUES (" + instruction + ")";

            // Kajal comment: should we break this down into two statements so its not chained like this?
            // also what is resultSet used for? Can we switch it for result like in the sample so that I can use it for error checking?
            resultSet = connection.createStatement().executeQuery(cmd);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result; // why returning name?
    }//end addRecipe


    @Override
    public String updateRecipe(Recipe currRecipe) {
        //I think it should return Recipe, because we are update and get a recipe with new information
        String name;
        result = null;

        try
        {
            name = currRecipe.getName();
            cmd = "UPDATE RECIPE SET";//stuck here
            resultSet = connection.createStatement().executeQuery(cmd);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }//end updateRecipe

    @Override
    public Recipe getRecipe(String name) {
        Recipe recipe = null;

        try
        {
            cmd = "SELECT * FROM RECIPE WHERE NAME='" + name + "'";
            resultSet = connection.createStatement().executeQuery(cmd);
        }
        catch (Exception se)
        {
            se.printStackTrace();
        }

        try
        {
            while(resultSet.next())
            {
                recipe = new Recipe(resultSet.getString("name"));
            }
        }
        catch (Exception se)
        {
            se.printStackTrace();
        }

        return recipe;
    }//end getRecipe

    @Override
    public ArrayList<Recipe> getListOfSameRecipe(Recipe recipe) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        String name = recipe.getName();

        try{
            cmd = "SELECT * FROM RECIPE WHERE NAME='"+name+"'";
            resultSet = connection.createStatement().executeQuery(cmd);
        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            while(resultSet.next()){
                recipe = new Recipe(resultSet.getString("name"));
                recipes.add(recipe);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return recipes;
    }//end getListOfSameRecipe

    @Override
    public boolean delRecipe(Recipe recipe) {
        String name = recipe.getName();
        boolean deleted = false;
        try{
            cmd = "DELETE FROM RECIPE WHERE NAME='"+name+"'";
            connection.createStatement().executeUpdate(cmd);
            System.out.println("Deleted successfully.");
            deleted = true;
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return deleted;
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