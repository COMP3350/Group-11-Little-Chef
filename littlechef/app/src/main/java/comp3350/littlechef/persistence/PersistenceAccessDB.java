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

    public PersistenceAccessDB(String dbPath) {
        databasePath = dbPath;
    }

    @Override
    public void open(String dbPath) {
        try {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();

            System.out.println("Connecting to selected database...");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "User", "");
            statement = connection.createStatement();
            System.out.println("Connection built successfully.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    connection.close();
                }
            } catch (SQLException se) {
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
    public String addRecipe(Recipe recipe) {
        String name = recipe.getName();
        String ingredients = recipe.getIngredients().toString();
        String steps = recipe.getInstructions().toString();
        String timeToMake = recipe.getAverageCookingTime();
        String difficulty = recipe.getDifficultyRating() + "";//since last time we discussed we want to make it as user-defined string, use +""
        String quality = recipe.getTasteRating() + "";//same as difficulty
        String rate = recipe.getRatingString();

        String instruction = "'" + name + "','" + ingredients + "','" + steps + "'," + timeToMake + ",'" + difficulty + "','" + quality + "'," + rate;

        try {
            cmd = "INSERT INTO RECIPES VALUES (" + instruction + ")";
            resultSet = connection.createStatement().executeQuery(cmd);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }//end addRecipe


    @Override
    public Recipe updateRecipe(Recipe currRecipe) {
        //I think it should return Recipe, because we are update and get a recipe with new information
        String name = currRecipe.getName();
        try {
            cmd = "UPDATE RECIPE SET";//stuck here
            resultSet = connection.createStatement().executeQuery(cmd);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currRecipe;
    }//end updateRecipe

    @Override
    public Recipe getRecipe(String name) {
        Recipe recipe = null;
        try {
            cmd = "SELECT * FROM RECIPE WHERE NAME='" + name + "'";
            resultSet = connection.createStatement().executeQuery(cmd);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            while(resultSet.next()){
                recipe = new Recipe(resultSet.getString("name"));
            }
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
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

}