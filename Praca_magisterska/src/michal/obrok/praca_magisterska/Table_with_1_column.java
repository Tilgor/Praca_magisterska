package michal.obrok.praca_magisterska;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Table_with_1_column extends SQLiteOpenHelper {

	private final String table_name = "TABLE_WITH_1_COLUMN";

	public Table_with_1_column(Context context) {
		super(context, "database.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE if not exists " + "table_name" + "("
				+ "integer1 integer);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void create_table() {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("CREATE TABLE IF NOT EXISTS TABLE_WITH_1_COLUMN("
				+ "integer1 integer);");
	}

	public void simple_insert(int number) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("integer1", number);
		db.insertOrThrow("TABLE_WITH_1_COLUMN", null, values);
	}

	public long simple_insert_rows(int row_count) {
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < row_count; i++) {
			ContentValues values = new ContentValues();
			values.put("integer1", i);
			db.insert(table_name, null, values);
		}
		return System.currentTimeMillis() - startTime;
	}

	public long simple_insert_rows_transaction(int row_count) {
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		db.beginTransaction();
		for (int i = 0; i < row_count; i++) {
			ContentValues values = new ContentValues();
			values.put("integer1", i);
			db.insert(table_name, null, values);
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return System.currentTimeMillis() - startTime;
	}

	public long bulk_insert_rows_transaction(int row_count) {
		SQLiteDatabase db = getWritableDatabase();
		String sql = "INSERT INTO " + "table_name VALUES" + " (?);";
		SQLiteStatement statement = db.compileStatement(sql);
		long startTime = System.currentTimeMillis();
		db.beginTransaction();
		for (int i = 0; i < row_count; i++) {
			statement.clearBindings();
			statement.bindLong(1, i);
			statement.execute();
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return System.currentTimeMillis() - startTime;
	}

	public long simple_update_rows(int row_count) {
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < row_count; i++) {
			ContentValues values = new ContentValues();
			values.put("integer1", (i + row_count));
			String[] args = { "" + i };
			db.update(table_name, values, "integer1=?", args);
		}
		return System.currentTimeMillis() - startTime;
	}

	public long simple_update_rows_transaction(int row_count) {
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		db.beginTransaction();
		for (int i = 0; i < row_count; i++) {
			ContentValues values = new ContentValues();
			values.put("integer1", (i + row_count));
			String[] args = { "" + i };
			db.update(table_name, values, "integer1=?", args);
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return System.currentTimeMillis() - startTime;
	}

	public long simple_select_rows(int row_count) {
		SQLiteDatabase db = getReadableDatabase();
		String[] columns = { "integer1" };
		Cursor cursor = db.query(table_name, columns, null, null, null, null,
				null, "" + row_count);

		long startTime = System.currentTimeMillis();
		while (cursor.moveToNext()) {
			int number1;
			number1 = cursor.getInt(0);
		}
		return System.currentTimeMillis() - startTime;
	}
	
	public long simple_select_rows_ordered(int row_count) {
		SQLiteDatabase db = getReadableDatabase();
		String[] columns = { "integer1" };
		Cursor cursor = db.query(table_name, columns, null, null, null, "integer1 desc",
				null, "" + row_count);

		long startTime = System.currentTimeMillis();
		while (cursor.moveToNext()) {
			int number1;
			number1 = cursor.getInt(0);
		}
		return System.currentTimeMillis() - startTime;
	}
	
	public void create_index(String index_name, String column_name){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("create index "+index_name+" on "+table_name +"(" +column_name+")");
	}
	
	public void drop_index(String index_name){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("drop index "+index_name);
	}

	public long simple_delete_rows(int row_count) {
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < row_count; i++) {
			String[] argument = { "" + i };
			db.delete(table_name, "integer1=?", argument);
		}
		return System.currentTimeMillis() - startTime;
	}

	public long simple_delete_rows_transaction(int row_count) {
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		db.beginTransaction();
		for (int i = 0; i < row_count; i++) {
			String[] argument = { "" + i };
			db.delete(table_name, "integer1=?", argument);
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return System.currentTimeMillis() - startTime;
	}

	public void simple_delete(int id) {
		SQLiteDatabase db = getWritableDatabase();
		String[] argument = { "" + id };
		db.delete(table_name, "integer1=?", argument);
	}

	public void simple_update(int old_pk, int new_pk) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("integer1", new_pk);
		String[] args = { "" + old_pk };
		db.update(table_name, values, "integer1=?", args);
	}

	public int get_pk(int id) {
		int integer = 0;
		SQLiteDatabase db = getWritableDatabase();
		String[] argument = { "" + id };
		String[] columns = { "integer1" };
		Cursor cursor = db.query(table_name, columns, "integer1=?", argument,
				null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			integer = cursor.getInt(0);
		}
		return integer;

	}

	public List<Integer> getAll() {
		List<Integer> values = new LinkedList<Integer>();
		String[] columns = { "integer1" };
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(table_name, columns, null, null, null, null,
				null);
		while (cursor.moveToNext()) {
			int number1;
			number1 = cursor.getInt(0);
			values.add(number1);
		}
		return values;
	}

	public void truncate_table() {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + table_name + ";");
		this.create_table();
	}
}
