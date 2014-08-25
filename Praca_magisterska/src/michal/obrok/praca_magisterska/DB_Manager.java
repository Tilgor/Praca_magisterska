package michal.obrok.praca_magisterska;

 

	import java.util.LinkedList;
import java.util.List;

	import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

	public class DB_Manager extends SQLiteOpenHelper{

		public DB_Manager(Context context) {
			super(context, "database.db", null, 1);		
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			db.execSQL(
					"CREATE TABLE if not exists TABLE_WITH_1_COLUMN(" +
					"integer1 integer);");		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {		
		}
		
		
		public void drop_table(String table_name){
			SQLiteDatabase db = getWritableDatabase();
			db.execSQL("DROP TABLE IF EXISTS "+table_name+";");
		}
		
		public void create_1_table(){
			SQLiteDatabase db = getWritableDatabase();
			db.execSQL(
					"CREATE TABLE IF NOT EXISTS TABLE_WITH_1_COLUMN(" +
					"integer1 integer);");	
		}
		
		public void simple_insert(int number){	
		SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("integer1", number);
			db.insertOrThrow("TABLE_WITH_1_COLUMN",null, values);


		}
		
		public long simple_insert_rows(int row_count){
			SQLiteDatabase db = getWritableDatabase();
			long startTime = System.currentTimeMillis();
	          for (int i = 0; i<row_count; i++) {
                  ContentValues values = new ContentValues();
                  values.put("integer1", i);
                  db.insert("TABLE_WITH_1_COLUMN",null,values);
	          }
        return System.currentTimeMillis() - startTime;
		}
		
		public void simple_delete(int id){
			SQLiteDatabase db = getWritableDatabase();
			String[] argument={""+id};
			db.delete("TABLE_WITH_1_COLUMN", "integer1=?", argument);
		}
		
		
		public void simple_update(int old_pk, int new_pk){
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("integer1", new_pk);
			String[] args ={""+old_pk};
			db.update("TABLE_WITH_1_COLUMN", values,"integer1=?",args);
		}
		
		public int get_pk(int id){
			int integer = 0;
			SQLiteDatabase db = getWritableDatabase();
			String[] argument={""+id};
			String[] columns={"integer1"};
			Cursor cursor =db.query("TABLE_WITH_1_COLUMN",columns,"integer1=?",argument,null,null,null);	
			if(cursor!=null){
				cursor.moveToFirst();
				integer = cursor.getInt(0);
			}
			return integer;
				
		}
		
		public List<Integer> getAll(){
			List<Integer> values = new LinkedList<Integer>();
			String[] columns={"integer1"};
			SQLiteDatabase db = getReadableDatabase();
			Cursor cursor =db.query("TABLE_WITH_1_COLUMN",columns,null,null,null,null,null);	
			while(cursor.moveToNext()){			
				int number1;
				number1 = cursor.getInt(0);
				values.add(number1);
			}
			return values;
		}
		
		/*
		public Kontakt dajKontakt(int nr){		
			Kontakt kontakt=new Kontakt();		
				SQLiteDatabase db = getReadableDatabase();
				String[] kolumny={"nr","imie","nazwisko","telefon"};
				String args[]={nr+""};
				Cursor kursor=db.query("telefony",kolumny," nr=?",args,null,null,null,null);
				if(kursor!=null){
					kursor.moveToFirst();
					kontakt.setNr(kursor.getLong(0));
					kontakt.setImie(kursor.getString(1));
					kontakt.setNazwisko(kursor.getString(2));
					kontakt.setTelefon(kursor.getString(3));
				}
			return kontakt;
		}
		*/
		/*
		public List<Kontakt> dajPoNazwisku(String nazwisko){
			List<Kontakt> kontakty = new LinkedList<Kontakt>();
			String[] kolumny={"nr","imie","nazwisko","telefon"};
			SQLiteDatabase db = getReadableDatabase();		
			Cursor kursor =db.rawQuery("select nr,imie,nazwisko,telefon from telefony where nazwisko='"
										+nazwisko+
									   "' order by imie asc", null);
			/*Alternatywne wywo³anie metody rawQuery
			 * 
			 * Cursor kursor =db.rawQuery
			 * ("select nr,imie,nazwisko,telefon from telefony where nazwisko=?	order by imie asc", nazwi);		
			 * */
		/*
			while(kursor.moveToNext()){			
				Kontakt kontakt = new Kontakt();
				kontakt.setNr(kursor.getLong(0));
				kontakt.setImie(kursor.getString(1));
				kontakt.setNazwisko(kursor.getString(2));
				kontakt.setTelefon(kursor.getString(3));
				kontakty.add(kontakt);
			}
			return kontakty;
		}
	*/
		
	}
