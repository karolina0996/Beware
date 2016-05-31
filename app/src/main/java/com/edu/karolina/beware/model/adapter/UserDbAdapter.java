package com.edu.karolina.beware.model.adapter;



import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.karolina.beware.model.UserVO;
import com.edu.karolina.beware.model.WeightVO;

public class UserDbAdapter {

	private static final String DATABASE_NAME = "Beware.db";
	private static final String DATABASE_TABLE_USERS = "users";
	private static final String DATABASE_TABLE_WEIGHT = "weights";
	private static final int DATABASE_VERSION = 2;

	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_LASTNAME = "lastname";
	public static final String KEY_NICKNAME = "nickname";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_AGE = "age";
	public static final String KEY_GENDER = "gender";
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_WEIGHT = "weight";

	public static final String KEY_ID_USER = "iduser";
	public static final String KEY_VALUE = "value";
	public static final String KEY_DATE = "dateregister";

	private SQLiteDatabase db;
	private Context context;

	private BewareDBOpenHelper dbHelper;

	public UserDbAdapter(Context context) {
		super();
		this.context = context;
		dbHelper = new BewareDBOpenHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);

	}

	public Cursor consultadatos(String nick) {

		String[] args = new String[] { nick };
		Cursor c = db
				.rawQuery(
						" SELECT name,lastname,nickname,password,age,gender,height,weight,_id FROM users WHERE nickname=? ",
						args);

		return c;

	}

	public Cursor consultapesos(int iduser) {
		String[] args = new String[] { Integer.toString(iduser) };
		Cursor c = db.rawQuery("SELECT " + KEY_DATE + ", " + KEY_VALUE
				+ " FROM weights WHERE iduser=? ORDER BY " + KEY_DATE, args);
		return c;
	}

	public boolean guardarpeso(WeightVO object) {
		ContentValues newUserValues = new ContentValues();
		// Assign values for each row.
		newUserValues.put(KEY_ID_USER, object.getIduser());
		newUserValues.put(KEY_DATE, object.getDate());
		newUserValues.put(KEY_VALUE, object.getValue());

		// Insert the row.
		return db.insert(DATABASE_TABLE_WEIGHT, null, newUserValues) > 0; // parametros:nombre

	}

	public void close() {
		db.close();
	}

	public void open() throws SQLiteException {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			db = dbHelper.getReadableDatabase();
		}
	}

	public SQLiteDatabase getDb() {
		return db;
	}

	public Cursor getAllUsersCursor() {
		return db.query(DATABASE_TABLE_USERS, new String[] { KEY_ID, KEY_NAME,
				KEY_LASTNAME, KEY_NICKNAME, KEY_PASSWORD, KEY_AGE, KEY_GENDER,
				KEY_HEIGHT, KEY_WEIGHT }, null, null, null, null, null);
	}

	public long insertUser(UserVO _user) {
		// Create a new row of values to insert.
		ContentValues newUserValues = new ContentValues();
		// Assign values for each row.
		newUserValues.put(KEY_NAME, _user.getName());
		newUserValues.put(KEY_LASTNAME, _user.getLastname());
		newUserValues.put(KEY_NICKNAME, _user.getNickname());
		newUserValues.put(KEY_PASSWORD, _user.getPassword());
		newUserValues.put(KEY_AGE, _user.getAge());
		newUserValues.put(KEY_GENDER, _user.getGender());
		newUserValues.put(KEY_HEIGHT, _user.getHeight());
		newUserValues.put(KEY_WEIGHT, _user.getWeight());

		// Insert the row.
		return db.insert(DATABASE_TABLE_USERS, null, newUserValues); // parametros:nombre
																		// de la
																		// tabla,
		// nombres de las columnas que quieron que acepten valores nulos,
		// contentvalues
	}

	private static class BewareDBOpenHelper extends SQLiteOpenHelper {

		public BewareDBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		// SQL Statement to create a new database.
		private static final String DATABASE_CREATE_USER = "create table "
				+ DATABASE_TABLE_USERS + " (" + KEY_ID
				+ " integer primary key autoincrement, " + KEY_NAME
				+ " text not null, " + KEY_LASTNAME + " text not null, "
				+ KEY_NICKNAME + " text not null, " + KEY_PASSWORD
				+ " text not null, " + KEY_AGE + " integer not null, "
				+ KEY_GENDER + " text not null, " + KEY_HEIGHT
				+ " integer not null, " + KEY_WEIGHT + " integer not null);";

		private static final String DATABASE_CREATE_WEIGHT = "create table "
				+ DATABASE_TABLE_WEIGHT + " (" + KEY_ID
				+ " integer primary key autoincrement, " + KEY_ID_USER
				+ " integer not null, " + KEY_DATE + " integer not null, "
				+ KEY_VALUE + " text not null);";

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_USER);
			_db.execSQL(DATABASE_CREATE_WEIGHT);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int _oldVersion,
				int _newVersion) {
			Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion
					+ " to " + _newVersion
					+ ", which will destroy all old data");
			// Drop the old table.
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_USERS);
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_WEIGHT);
			// Create a new one.
			onCreate(_db);
		}
	}

}
