package michal.obrok.praca_magisterska;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Simple_relation_tables extends SQLiteOpenHelper implements Table_interface {

	private final String table_name1 = "SIMPLE_RELATION_1";
	private final String table_name2 = "SIMPLE_RELATION_2";

	public Simple_relation_tables(Context context) {
		super(context, "database.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE if not exists " + table_name1 + "("
				+ "ID_INT integer primary key autoincrement, integer1 integer);");
		db.execSQL("CREATE TABLE if not exists " + table_name2 + "("
				+ "ID_INT integer primary key autoincrement, SM1_ID integer, integer1 integer);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void create_table() {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("CREATE TABLE if not exists " + table_name1 + "("
				+ "ID_INT integer primary key autoincrement, integer1 integer);");
		db.execSQL("CREATE TABLE if not exists " + table_name2 + "("
				+ "ID_INT integer primary key autoincrement, SM1_ID integer, integer1 integer);");
	}

	public void simple_insert(int number) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("integer2", number);
		values.put("integer3", number);
		values.put("integer4", number);
		values.put("integer5", number);
		db.insertOrThrow(table_name1, null, values);
	}

	public long simple_insert_rows(int row_count) {
		row_count = row_count/5;
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < row_count; i++) {
			ContentValues values = new ContentValues();
			values.put("integer1", i);
			db.insert(table_name1, null, values);
			for(int k =0; k<4;k++){
				ContentValues values1 = new ContentValues();
				values1.put("SM1_ID", i);
				values1.put("integer1", i+k);
				db.insert(table_name2, null, values1);
			}
		}
		return System.currentTimeMillis() - startTime;
	}

	public long simple_insert_rows_transaction(int row_count) {
		row_count = row_count/5;
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		db.beginTransaction();
		for (int i = 0; i < row_count; i++) {
			ContentValues values = new ContentValues();
			values.put("integer1", i);
			db.insert(table_name1, null, values);
			for(int k =0; k<4;k++){
				ContentValues values1 = new ContentValues();
				values1.put("SM1_ID", i);
				values1.put("integer1", i+k);
				db.insert(table_name2, null, values1);
			}
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return System.currentTimeMillis() - startTime;
	}

	public long bulk_insert_rows_transaction(int row_count) {
		row_count = row_count/5;
		SQLiteDatabase db = getWritableDatabase();
		String sql = "INSERT INTO " + table_name1 + " (integer1) VALUES " + " (?);";
		String sql1 = "INSERT INTO " + table_name2 + " (SM1_ID,integer1) VALUES " + " (?,?);";
		SQLiteStatement statement = db.compileStatement(sql);
		SQLiteStatement statement1 = db.compileStatement(sql1);
		long startTime = System.currentTimeMillis();
		db.beginTransaction();
		for (int i = 0; i < row_count; i++) {
			statement.clearBindings();
			statement.bindLong(1, i);
			statement.execute();
			for(int k =0; k<4;k++){
				statement1.clearBindings();
				statement1.bindLong(1, i);
				statement1.bindLong(2, i+k);
				statement1.execute();
			}
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return System.currentTimeMillis() - startTime;
	}

	public long simple_update_rows(int row_count) {
		row_count = row_count/5;
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < row_count; i++) {
			ContentValues values = new ContentValues();
			values.put("integer1", i);
			String[] args = { "" + i };
			db.update(table_name1, values, "integer1=?", args);
			for(int k =0; k<4;k++){
				ContentValues values1 = new ContentValues();
				values1.put("SM1_ID", i);
				values1.put("integer1", i+k);
				db.update(table_name2, values1, "SM1_ID=?", args);
			}
		}
		return System.currentTimeMillis() - startTime;
	}

	public long simple_update_rows_transaction(int row_count) {
		row_count = row_count/5;
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		db.beginTransaction();
		for (int i = 0; i < row_count; i++) {
			ContentValues values = new ContentValues();
			values.put("integer1", i);
			String[] args = { "" + i };
			db.update(table_name1, values, "integer1=?", args);
			for(int k =0; k<4;k++){
				ContentValues values1 = new ContentValues();
				values1.put("SM1_ID", i);
				values1.put("integer1", i+k);
				db.update(table_name2, values1, "SM1_ID=?", args);
			}
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return System.currentTimeMillis() - startTime;
	}

	public long simple_select_rows(int row_count) {
		SQLiteDatabase db = getReadableDatabase();
		String[] columns = { "integer1","integer2","integer3","integer4","integer5" };
		Cursor cursor =
				db.rawQuery("SELECT * FROM "+table_name1+" a INNER JOIN "+table_name2+" b ON a.ID_INT = b.SM1_ID LIMIT ?",
						new String[]{""+row_count});

		long startTime = System.currentTimeMillis();
		while (cursor.moveToNext()) {
			int number1,number2,number3,number4;
			number1 = cursor.getInt(0);
			number2 = cursor.getInt(1);
			number3 = cursor.getInt(2);
			number4 = cursor.getInt(3);
		}
		return System.currentTimeMillis() - startTime;
	}
	
	public long simple_select_rows_ordered(int row_count) {
		SQLiteDatabase db = getReadableDatabase();
		String[] columns = { "integer1","integer2","integer3","integer4","integer5" };
		Cursor cursor =
				db.rawQuery("SELECT * FROM "+table_name1+" a INNER JOIN "+table_name2+" b ON a.ID_INT=b.SM1_ID order by a.integer1,b.integer1 LIMIT ?",
						new String[]{""+row_count});

		long startTime = System.currentTimeMillis();
		while (cursor.moveToNext()) {
			int number1,number2,number3,number4,number5;
			number1 = cursor.getInt(0);
			number2 = cursor.getInt(1);
			number3 = cursor.getInt(2);
			number4 = cursor.getInt(3);
			number5 = cursor.getInt(4);
		}
		return System.currentTimeMillis() - startTime;
	}
	
	public void create_index(String index_name, String column_name){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("create index if not exists "+index_name+table_name1+" on "+table_name1 +"(" +column_name+")");
		db.execSQL("create index if not exists "+index_name+table_name2+" on "+table_name2 +"(" +column_name+")");
	}
	
	public void drop_index(String index_name){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("drop index if exists "+index_name+table_name1);
		db.execSQL("drop index if exists "+index_name+table_name2);
	}

	public long simple_delete_rows(int row_count) {
		row_count = row_count/5;
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < row_count; i++) {
			String[] argument = { "" + i };
			db.delete(table_name1, "integer1=?", argument);
			for(int k =0; k<4;k++){
				String[] argument1 = { "" + i };
				db.delete(table_name2, "SM1_ID=?", argument1);
			}
		}
		return System.currentTimeMillis() - startTime;
	}

	public long simple_delete_rows_transaction(int row_count) {
		row_count = row_count/5;
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		db.beginTransaction();
		for (int i = 0; i < row_count; i++) {
			String[] argument = { "" + i };
			db.delete(table_name1, "integer1=?", argument);
			for(int k =0; k<4;k++){
				String[] argument1 = { "" + i };
				db.delete(table_name2, "SM1_ID=?", argument1);
			}
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		return System.currentTimeMillis() - startTime;
	}

	public void simple_delete(int id) {
		SQLiteDatabase db = getWritableDatabase();
		String[] argument = { "" + id };
		db.delete(table_name1, "integer1=?", argument);
	}

	public void simple_update(int old_pk, int new_pk) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("integer1", new_pk);
		String[] args = { "" + old_pk };
		db.update(table_name1, values, "integer1=?", args);
	}

	public int get_pk(int id) {
		int integer = 0;
		SQLiteDatabase db = getWritableDatabase();
		String[] argument = { "" + id };
		String[] columns = { "integer1" };
		Cursor cursor = db.query(table_name1, columns, "integer1=?", argument,
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
		Cursor cursor = db.query(table_name1, columns, null, null, null, null,
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
		db.execSQL("DROP TABLE IF EXISTS " + table_name1 + ";");
		db.execSQL("DROP TABLE IF EXISTS " + table_name2 + ";");
		this.create_table();
	}
}
