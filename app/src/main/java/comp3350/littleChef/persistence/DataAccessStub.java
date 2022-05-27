package comp3350.littleChef.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.littleChef.application.Main;

public class DataAccessStub
{
	private String dbName;
	private String dbType = "stub";


	public DataAccessStub(String dbName)
	{
		this.dbName = dbName;
	}

	public DataAccessStub()
	{
		this(Main.dbName);
	}
}
