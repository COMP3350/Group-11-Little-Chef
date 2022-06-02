package main.java.comp3350.littlechef.persistence;

import java.util.ArrayList;

import main.java.comp3350.littlechef.objects.Recipe;

import java.sql.*;
import java.util.*;

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
            /*** Unsure which db we are going to use ***/
            dbType = "HSQL";
            Class.forName("oracle.jdbc.driver.OracleDriver");

            System.out.println("Connecting to selected database...");
            connection = DriverManager.getConnection("jdbc:hsqldb.jdbcDriver", "User", "");
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
        String steps = recipe.getSteps().toString();
        String timeToMake = String.valueOf(recipe.getTimeToMake());
        String difficulty = recipe.getDifficulty() + "";//since last time we discussed we want to make it as user-defined string, use +""
        String quality = recipe.getQuality() + "";
        String rate = String.valueOf(recipe.getRating());

        String instruction = name + ingredients;
        
        try {
            cmd = "INSERT INTO RECIPES VALUES (" + instruction + ")";
            resultSet = connection.createStatement().executeQuery(cmd);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public String getRecipe(Recipe recipe) {
        return null;
    }

    @Override
    public ArrayList<Recipe> getListOfRecipe() {
        return null;
    }

    @Override
    public boolean delRecipe(String name) {
        return false;
    }
}
