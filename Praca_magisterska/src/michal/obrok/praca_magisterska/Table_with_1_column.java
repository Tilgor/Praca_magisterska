package michal.obrok.praca_magisterska;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Table_with_1_column extends SQLiteOpenHelper implements Table_interface {

	private final String table_name = "TABLE_WITH_1_COLUMN";

	public Table_with_1_column(Context context) {
		super(context, "database.db", null, 1);
	}

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#onCreate(android.database.sqlite.SQLiteDatabase)
	 */

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE if not exists " + table_name + "("
				+ "integer1 integer);");
	}

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#create_table()
	 */
	@Override
	public void create_table() {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("CREATE TABLE IF NOT EXISTS " + table_name + "("
				+ "integer1 integer);");
	}

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_insert(int)
	 */
	@Override
	public void simple_insert(int number) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("integer1", number);
		db.insertOrThrow("TABLE_WITH_1_COLUMN", null, values);
	}

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_insert_rows(int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_insert_rows_transaction(int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#bulk_insert_rows_transaction(int)
	 */
	@Override
	public long bulk_insert_rows_transaction(int row_count) {
		SQLiteDatabase db = getWritableDatabase();
		String sql = "INSERT INTO " + table_name + " VALUES" + " (?);";
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

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_update_rows(int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_update_rows_transaction(int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_select_rows(int)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_select_rows_ordered(int)
	 */
	@Override
	public long simple_select_rows_ordered(int row_count) {
		SQLiteDatabase db = getReadableDatabase();
		String[] columns = { "integer1" };
		Cursor cursor = db.query(table_name, columns, null, null, null,null, "integer1 desc", "" + row_count);

		long startTime = System.currentTimeMillis();
		while (cursor.moveToNext()) {
			int number1;
			number1 = cursor.getInt(0);
		}
		return System.currentTimeMillis() - startTime;
	}
	
	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#create_index(java.lang.String, java.lang.String)
	 */
	@Override
	public void create_index(String index_name, String column_name){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("create index if not exists "+index_name+table_name+" on "+table_name +"(" +column_name+")");
	}
	
	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#drop_index(java.lang.String)
	 */
	@Override
	public void drop_index(String index_name){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("drop index if exists "+index_name+table_name);
	}

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_delete_rows(int)
	 */
	@Override
	public long simple_delete_rows(int row_count) {
		SQLiteDatabase db = getWritableDatabase();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < row_count; i++) {
			String[] argument = { "" + i };
			db.delete(table_name, "integer1=?", argument);
		}
		return System.currentTimeMillis() - startTime;
	}

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_delete_rows_transaction(int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_delete(int)
	 */
	@Override
	public void simple_delete(int id) {
		SQLiteDatabase db = getWritableDatabase();
		String[] argument = { "" + id };
		db.delete(table_name, "integer1=?", argument);
	}

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#simple_update(int, int)
	 */
	@Override
	public void simple_update(int old_pk, int new_pk) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("integer1", new_pk);
		String[] args = { "" + old_pk };
		db.update(table_name, values, "integer1=?", args);
	}

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#get_pk(int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#getAll()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see michal.obrok.praca_magisterska.Table_interface#truncate_table()
	 */
	@Override
	public void truncate_table() {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + table_name + ";");
		this.create_table();
	}
}
