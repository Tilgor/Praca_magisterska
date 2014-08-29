package michal.obrok.praca_magisterska;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.NumberPicker;

public class updates extends Fragment  implements OnClickListener {
	
	private View mainView;
	private Button button_01,button_02;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mainView = inflater.inflate(R.layout.updates, container, false);
		initialize();
		listeners();
        return mainView;
        

}
	
	private void initialize() {
		button_01=(Button)mainView.findViewById(R.id.Button01);
		button_02=(Button)mainView.findViewById(R.id.Button02);
		
	}
	
	
	private void listeners() {
		button_01.setOnClickListener(this);
		button_02.setOnClickListener(this);
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
		
		if (v==button_01)
		{
			
			TextView result = (TextView)mainView.findViewById(R.id.Result);
			EditText rowcount_text = (EditText)mainView.findViewById(R.id.editText1);
			if(rowcount_text != null && !rowcount_text.getText().toString().equals("")){
				int rowcount = 0;
				rowcount = Integer.parseInt(rowcount_text.getText().toString());
				result.setText(tb.simple_update_rows(rowcount)+"ms");
			}
		}
		else if(v==button_02)
		{
			TextView result = (TextView)mainView.findViewById(R.id.Result);
			EditText rowcount_text = (EditText)mainView.findViewById(R.id.editText1);
			if(rowcount_text != null && !rowcount_text.getText().toString().equals("")){
				int rowcount = 0;
				rowcount = Integer.parseInt(rowcount_text.getText().toString());
				result.setText(tb.simple_update_rows_transaction(rowcount)+"ms");
			}
		}

	}
	

}
