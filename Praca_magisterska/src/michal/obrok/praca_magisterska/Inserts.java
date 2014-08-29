package michal.obrok.praca_magisterska;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.view.View.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Inserts extends Fragment  implements OnClickListener {
	
	private View mainView;
	private Button insert_button_table_1,insert_button_table_2,insert_button_table_3;
	

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mainView = inflater.inflate(R.layout.inserts, container, false);
		initialize();
		listeners();
        return mainView;
}
	private void initialize() {
		insert_button_table_1=(Button)mainView.findViewById(R.id.Button01);
		insert_button_table_2=(Button)mainView.findViewById(R.id.Button02);
		insert_button_table_3=(Button)mainView.findViewById(R.id.Button03);
		
	}
	
	private void listeners() {
		insert_button_table_1.setOnClickListener(this);
		insert_button_table_2.setOnClickListener(this);
		insert_button_table_3.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Table_interface tb = new Table_with_1_column(getActivity());
		if(global_settings.chosed_table==table_name.TABLE_WITH_1_COLUMN)
			tb = new Table_with_1_column(getActivity());
		else if(global_settings.chosed_table==table_name.TABLE_WITH_2_COLUMNS)
			tb = new Table_with_2_columns(getActivity());
		else if(global_settings.chosed_table==table_name.TABLE_WITH_5_COLUMNS)
			tb = new Table_with_5_columns(getActivity());
		else if(global_settings.chosed_table==table_name.SIMPLE_RELATION_TABLES)
			tb = new Simple_relation_tables(getActivity());
		
		if (v==insert_button_table_1)
		{
			
			TextView result = (TextView)mainView.findViewById(R.id.Result);
			EditText rowcount_text = (EditText)mainView.findViewById(R.id.editText1);
			if(rowcount_text != null && !rowcount_text.getText().toString().equals("")){
				int rowcount = 0;
				rowcount = Integer.parseInt(rowcount_text.getText().toString());
				result.setText(tb.simple_insert_rows(rowcount)+"ms");
			}
		}
		else if(v==insert_button_table_2)
		{
			TextView result = (TextView)mainView.findViewById(R.id.Result);
			EditText rowcount_text = (EditText)mainView.findViewById(R.id.editText1);
			if(rowcount_text != null && !rowcount_text.getText().toString().equals("")){
				int rowcount = 0;
				rowcount = Integer.parseInt(rowcount_text.getText().toString());
				result.setText(tb.simple_insert_rows_transaction(rowcount)+"ms");
			}
		}
		else if(v==insert_button_table_3)
		{
			TextView result = (TextView)mainView.findViewById(R.id.Result);
			EditText rowcount_text = (EditText)mainView.findViewById(R.id.editText1);
			if(rowcount_text != null && !rowcount_text.getText().toString().equals("")){
				int rowcount = 0;
				rowcount = Integer.parseInt(rowcount_text.getText().toString());
				result.setText(tb.bulk_insert_rows_transaction(rowcount)+"ms");
			}
		}

	}


}
