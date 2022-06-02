package main.java.comp3350.littlechef.persistence;

import java.util.ArrayList;

import main.java.comp3350.littlechef.objects.Recipe;

import java.sql.*;
import java.util.*;

public class PersistenceAccessDB implements PersistenceAccess{
    private String dbPath;
    private Connection connection;
    private String cmd;
    private ResultSet resultSet;
    private String dbType;
    private Statement statement;

    public PersistenceAccessDB(String dbPath){
        this.dbPath = dbPath;
    }

    @Override
    public void open(String dbPath) {
        String url = "jdbc:hsqldb.jdbcDriver";

        try{
            /*** Unsure which db we are going to use ***/
            dbType ="HSQL";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Connecting to selected database...");
            connection = DriverManager.getConnection(url);
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           try {
              if(statement!=null){
                  connection.close();
              }
            }catch (SQLException se){
               se.printStackTrace();
           }
        }

    }

    @Override
    public void close() {

    }

    @Override
    public String addRecipe(Recipe recipe) {
        return null;
    }

    @Override
    public String getRecipe(String name) {
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
