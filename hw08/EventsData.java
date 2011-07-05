package gaspar.database_hw;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static gaspar.database_hw.Constants._ID;
import static gaspar.database_hw.Constants.TABLE_NAME;
import static gaspar.database_hw.Constants.NAME;
import static gaspar.database_hw.Constants.EMAIL;

/**
 * This class is designed to check if there is a database that currently
 * exists for the given program.  If the database does not exist, it creates
 * one.  After the class ensures that the database exists, this class
 * will open the database for use.  Most of this functionality will be
 * handled by the SQLiteOpenHelper parent class.  The purpose of extending
 * this class is to tell the class how to create (or update) the database.
 * 
 * @author Randall Mitchell
 * @author Modified by gaspar obimba to suite our users tables
 *
 */
public class EventsData  extends SQLiteOpenHelper{
	private static final String DB_NAME = "users.db";
	private static final int DB_VERSION = 1;
	
	public EventsData(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// This string is used to create the database.  It should
		// be changed to suit your needs.
		String newTableQueryString = "create table " +
		TABLE_NAME +
		" (" +
		_ID + " integer primary key autoincrement not null," +
		NAME + " text," +
		EMAIL + " text" +
		");";
		// execute the query string to the database.
		db.execSQL(newTableQueryString);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	      onCreate(db);
	}
}

