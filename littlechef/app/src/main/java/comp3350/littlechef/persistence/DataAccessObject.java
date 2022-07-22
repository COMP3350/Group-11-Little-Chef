package comp3350.littlechef.persistence;

import java.util.ArrayList;

import java.sql.*;
import java.util.*;

import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;

public class DataAccessObject implements DataAccess
{

    private String cmd, cmd2;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    private String databasePath;
    private String dbName;

    private Connection connection;
    private ResultSet resultSet, resultSet2;
    private String dbType;
    private Statement statement;

    private ArrayList<Recipe> recipes;
    private boolean success;

    public DataAccessObject(String dbName)
    {
        this.dbName = dbName;
    }

    @Override
    public boolean open(String dbPath)
    {
        success = false;
        String url;

        validatePath(dbPath);

        try
        {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();

            System.out.println("Connecting to selected database...\n");
            url = "jdbc:hsqldb:file:" + dbPath.trim(); // stored on disk mode
            connection = DriverManager.getConnection(url, "SA", "");
            statement = connection.createStatement();
            System.out.println("Connection built successfully.");
            success = true;
        }
        catch (Exception se)
        {
            se.printStackTrace();
        }

        System.out.println(dbType + " type's database opened successfully.");

        return success;
    }

    @Override
    public boolean close()
    {
        success = false;

        try
        {
            if(connection == null)
            {
                return false;
            }
            cmd = "shutdown compact";
            resultSet = statement.executeQuery(cmd);
            connection.close();
            success = true;
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        System.out.println(dbType + " type's database closed.");
        return success;
    }

    @Override
    public String insertRecipe(Recipe recipe)
    {
        int recipeID;

        String values;
        result = null;

        if(connection == null)
        {
            return "connection is not open.";
        }

        try
        {
            values = "'" + recipe.getName().replace("'","''")
                    + "'";
            cmd = "INSERT INTO RECIPES " + "(NAME)" + " VALUES (" + values + ")";
            updateCount = statement.executeUpdate(cmd);
            result = checkWarning(statement, updateCount);
            cmd = "CALL IDENTITY()";
            resultSet = statement.executeQuery(cmd);
            resultSet.next();
            recipeID = resultSet.getInt(1);
            recipe.setId(recipeID);

            insertRecipeData(recipe);

        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result; // why returning name?
    }//end addRecipe

    private void insertRecipeData(Recipe recipe)
    {
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<String[]> instructions = recipe.getInstructions();
        ArrayList<Integer> cookTimes = recipe.getCookingTimes();
        ArrayList<Double> difficultyRating = recipe.getDifficultyRatingsList();
        ArrayList<Double> tasteRating = recipe.getTasteRatingsList();

        String values;
        result = null;

        try
        {
            for(int i = 0; i < instructions.size(); i++)
            {
                values = "'" + instructions.get(i)[0].replace("'","''")
                        + "', '" + instructions.get(i)[1].replace("'","''")
                        + "', " + recipe.getId()
                        + "";
                cmd = "INSERT INTO INSTRUCTIONS " + "(INSTRUCTION, SUBINSTRUCTION, RECIPEID)" + "VALUES(" + values + ")"+"\n";
                updateCount = statement.executeUpdate(cmd);
                result = checkWarning(statement, updateCount);
            }
            for(int i = 0; i < ingredients.size(); i++)
            {
                values = recipe.getId()
                        + ", '" + ingredients.get(i).getName().replace("'","''")
                        + "', " + ingredients.get(i).getAmount()
                        + ", '" + ingredients.get(i).getUnitType()
                        + "', '" + ingredients.get(i).getMeasurement().toString()
                        + "'";
                cmd = "\nINSERT INTO INGREDIENTS " + "(RECIPEID, NAME, AMOUNT, UNITTYPE, UNIT)" + " VALUES (" + values + ")"+"\n";
                updateCount = statement.executeUpdate(cmd);
                result = checkWarning(statement, updateCount);
            }
            result = insertRating(recipe, cookTimes, "COOKINGTIMES");
            result = insertRating(recipe, difficultyRating, "DIFFICULTYRATINGS");
            result = insertRating(recipe, tasteRating, "TASTERATINGS");
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

    }

    //insert recipe helper method
    private String insertRating(Recipe recipe, ArrayList list, String table)
    {
        try
        {
            for(int i = 0; i < list.size(); i++)
            {
                String values = list.get(i)
                        + ", " + recipe.getId()
                        + "";
                cmd = "INSERT INTO " + table + "(RATING, RECIPEID)" + " VALUES (" + values + ")"+"\n";
                updateCount = statement.executeUpdate(cmd);
                result = checkWarning(statement, updateCount);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }
    @Override
    public String updateRecipe(Recipe recipe) {

        String table;
        String values;
        String where = "WHERE RECIPEID=" + recipe.getId();
        result = null;

        if(connection == null)
        {
            return "connection is not open.";
        }

        try
        {
            table = "RECIPES";
            values = "NAME='" + recipe.getName().replace("'","''") + "'";
            cmd = "UPDATE " + table + " SET " + values + " " + where+"\n";
            updateCount = statement.executeUpdate(cmd);
            result = checkWarning(statement, updateCount);

            deleteAllRecipeInfo(recipe.getId());
            insertRecipeData(recipe);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }//end updateRecipe

    private void deleteAllRecipeInfo(int RECIPEID)
    {
        String tables[] = {"INGREDIENTS", "INSTRUCTIONS", "COOKINGTIMES" , "TASTERATINGS", "DIFFICULTYRATINGS"};

        try
        {
            for (int i = 0; i < tables.length; i++)
            {
                cmd = "DELETE FROM " + tables[i] + " WHERE RECIPEID=" + RECIPEID+"\n";
                updateCount = statement.executeUpdate(cmd);
                result = checkWarning(statement, updateCount);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
    }

    @Override
    public String getRecipeSequential(List<Recipe> recipeList)
    {

        Recipe recipe;
        String myID, myName;
        myID = EOF;
        myName = EOF;
        result = null;

        if(connection == null)
        {
            return "connection is not open.";
        }

        try
        {
            cmd = "SELECT * FROM RECIPES";
            resultSet = statement.executeQuery(cmd);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while(resultSet.next())
            {
                myID = resultSet.getString("RECIPEID");
                myName = resultSet.getString("NAME");
                recipe = new Recipe(Integer.parseInt(myID));
                recipe.setName(myName);
                cmd2 = "SELECT * FROM INGREDIENTS WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    Ingredient ing = new Ingredient(resultSet2.getString("NAME"),Unit.valueOf(resultSet2.getString("UNIT")),resultSet2.getDouble("AMOUNT"));
                    recipe.addIngredient(ing);
                }
                cmd2 = "SELECT * FROM INSTRUCTIONS WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    recipe.addInstructions(resultSet2.getString("INSTRUCTION"), resultSet2.getString("SUBINSTRUCTION"));
                }
                cmd2 = "SELECT * FROM DIFFICULTYRATINGS WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    recipe.addDifficultyRating(resultSet2.getDouble("RATING"));
                }
                cmd2 = "SELECT * FROM TASTERATINGS WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    recipe.addTasteRating(resultSet2.getDouble("RATING"));
                }
                cmd2 = "SELECT * FROM COOKINGTIMES WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    recipe.addCookingTime(resultSet2.getInt("RATING"));
                }
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

        recipes = null;
        try
        {
            cmd = "SELECT * FROM RECIPES WHERE RECIPEID=" + newRecipe.getId();
            resultSet = statement.executeQuery(cmd);
            // ResultSetMetaData md2 = rs3.getMetaData();
            recipes = new ArrayList<Recipe>();
            while (resultSet.next())
            {
                myID = resultSet.getString("RecipeID");
                recipe = new Recipe(Integer.parseInt(myID));
                recipe.setName(resultSet.getString("Name"));
                cmd2 = "SELECT * FROM INGREDIENTS WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    Ingredient ing = new Ingredient(resultSet2.getString("NAME"),Unit.valueOf(resultSet2.getString("UNIT")),resultSet2.getDouble("AMOUNT"));
                    recipe.addIngredient(ing);
                }
                cmd2 = "SELECT * FROM INSTRUCTIONS WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    recipe.addInstructions(resultSet2.getString("INSTRUCTION"), resultSet2.getString("SUBINSTRUCTION"));
                }
                cmd2 = "SELECT * FROM DIFFICULTYRATINGS WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    recipe.addDifficultyRating(resultSet2.getDouble("RATING"));
                }
                cmd2 = "SELECT * FROM TASTERATINGS WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    recipe.addTasteRating(resultSet2.getDouble("RATING"));
                }
                cmd2 = "SELECT * FROM COOKINGTIMES WHERE RECIPEID=" + myID;
                resultSet2 = statement.executeQuery(cmd2);
                while(resultSet2.next())
                {
                    recipe.addCookingTime(resultSet2.getInt("RATING"));
                }
                recipes.add(recipe);
            }
            resultSet.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        return recipes;
    }

    @Override
    public String deleteRecipe(Recipe recipe)
    {
        int recipeId;
        result = null;

        if(connection == null)
        {
            return "connection is not open.";
        }

        try
        {
            recipeId = recipe.getId();
            cmd = "DELETE FROM RECIPES WHERE RECIPEID ="+recipeId+"\n";
            updateCount = statement.executeUpdate(cmd);
            System.out.println("Deleted successfully.");
            result = checkWarning(statement, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }//end delRecipe

    public String resetDatabase()
    {

        result = null;

        if(connection == null)
        {
            return "connection is not open.";
        }

        try
        {
            cmd = "drop table if exists INGREDIENTS;\n" +
                    "drop table if exists INSTRUCTIONS;\n" +
                    "drop table if exists DIFFICULTYRATINGS;\n" +
                    "drop table if exists TASTERATINGS;\n" +
                    "drop table if exists COOKINGTIMES;\n" +
                    "drop table if exists RECIPES;\n";
            connection.createStatement().executeUpdate(cmd);

            cmd = "CREATE MEMORY TABLE RECIPES(RECIPEID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,NAME VARCHAR(64))\n" +
            "CREATE MEMORY TABLE INGREDIENTS(INGREDIENTID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,RECIPEID INTEGER NOT NULL,NAME VARCHAR(64),AMOUNT DOUBLE,UNITTYPE VARCHAR(64),UNIT VARCHAR(64),CONSTRAINT CONSTRAINT1 FOREIGN KEY(RECIPEID) REFERENCES RECIPES(RECIPEID) ON DELETE CASCADE)\n" +
            "CREATE MEMORY TABLE INSTRUCTIONS(INSTRUCTIONID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,INSTRUCTION VARCHAR(2000),SUBINSTRUCTION VARCHAR(2000),RECIPEID INTEGER NOT NULL,CONSTRAINT CONSTRAINT2 FOREIGN KEY(RECIPEID) REFERENCES RECIPES(RECIPEID) ON DELETE CASCADE)\n" +
            "CREATE MEMORY TABLE DIFFICULTYRATINGS(DIFFICULTYRATINGID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,RATING DOUBLE,RECIPEID INTEGER NOT NULL,CONSTRAINT CONSTRAINT3 FOREIGN KEY(RECIPEID) REFERENCES RECIPES(RECIPEID) ON DELETE CASCADE)\n" +
            "CREATE MEMORY TABLE TASTERATINGS(TASTERATINGID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,RATING DOUBLE,RECIPEID INTEGER NOT NULL,CONSTRAINT CONSTRAINT4 FOREIGN KEY(RECIPEID) REFERENCES RECIPES(RECIPEID) ON DELETE CASCADE)\n" +
            "CREATE MEMORY TABLE COOKINGTIMES(COOKINGTIMEID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,RATING INTEGER,RECIPEID INTEGER NOT NULL,CONSTRAINT CONSTRAINT5 FOREIGN KEY(RECIPEID) REFERENCES RECIPES(RECIPEID) ON DELETE CASCADE)\n";
            connection.createStatement().executeUpdate(cmd);
            System.out.println("DATABASE RESET SUCCESSFULLY.");
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

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

    private void validatePath(String path)
    {
        if(path == null)
        {
            throw new NullPointerException("Path cannot be null.");
        }

        path = path.trim();

        if(path.length() == 0)
        {
            throw new IllegalArgumentException("Path cannot be an empty String.");
        }
    }

}