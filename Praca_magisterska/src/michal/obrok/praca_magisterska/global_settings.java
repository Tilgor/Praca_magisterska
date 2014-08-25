package michal.obrok.praca_magisterska;

public class global_settings {
	
	public static table_name chosed_table = table_name.TABLE_WITH_1_COLUMN;
	
	
	public static void change_selected_table(table_name name){
		chosed_table = name;
	}

}
