package michal.obrok.praca_magisterska;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

public interface Table_interface {

	public abstract void onCreate(SQLiteDatabase db);

	public abstract void onUpgrade(SQLiteDatabase db, int oldVersion,
			int newVersion);

	public abstract void create_table();

	public abstract void simple_insert(int number);

	public abstract long simple_insert_rows(int row_count);

	public abstract long simple_insert_rows_transaction(int row_count);

	public abstract long bulk_insert_rows_transaction(int row_count);

	public abstract long simple_update_rows(int row_count);

	public abstract long simple_update_rows_transaction(int row_count);

	public abstract long simple_select_rows(int row_count);

	public abstract long simple_select_rows_ordered(int row_count);

	public abstract void create_index(String index_name, String column_name);

	public abstract void drop_index(String index_name);

	public abstract long simple_delete_rows(int row_count);

	public abstract long simple_delete_rows_transaction(int row_count);

	public abstract void simple_delete(int id);

	public abstract void simple_update(int old_pk, int new_pk);

	public abstract int get_pk(int id);

	public abstract List<Integer> getAll();

	public abstract void truncate_table();

}