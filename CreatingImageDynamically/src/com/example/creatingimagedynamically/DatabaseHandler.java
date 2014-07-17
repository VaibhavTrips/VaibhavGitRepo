package com.example.creatingimagedynamically;




import android.content.ContentValues;
import android.database.SQLException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "kidsdetails";

	// Contacts table name
	private static final String TABLE_KIDS = "kiddy";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	DatabaseHandler dbhelper;
	SQLiteDatabase db;


	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_KIDS + "(name text)";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_KIDS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addKid(String kidd) {
		 db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, kidd); // Contact Name
		

		// Inserting Row
		db.insert(TABLE_KIDS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	Kid getKid(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_KIDS, new String[] { KEY_ID,
				KEY_NAME}, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Kid kidd = new Kid(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1));
		// return contact
		return kidd;
	}
	
	public DatabaseHandler open() {
		db=dbhelper.getWritableDatabase();
		return this;
	}

	public Cursor getname() {
		// TODO Auto-generated method stub
		return db.rawQuery("select * from TABLE_KIDS", null);
		
	}

	
	// Deleting single contact
	public void deleteKid(Kid kidd) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_KIDS, KEY_ID + " = ?",
				new String[] { String.valueOf(kidd.getID()) });
		db.close();
	}


	// Getting contacts Count
	public int getKidCount() {
		String countQuery = "SELECT  * FROM " + TABLE_KIDS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
