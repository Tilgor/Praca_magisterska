package michal.obrok.praca_magisterska;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class managment extends Fragment implements OnClickListener {

	private View mainView;
	private Button insert_button_table_1, insert_button_table_2,
			insert_button_table_3, insert_button_table_4,
			insert_button_table_5;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainView = inflater.inflate(R.layout.managment, container, false);
		DB_Manager db = new DB_Manager(getActivity());
		Table_interface tb1 = new Table_with_1_column(getActivity());
		initialize();
		listeners();
		return mainView;

	}

	private void initialize() {
		insert_button_table_1 = (Button) mainView.findViewById(R.id.Button01);
		insert_button_table_2 = (Button) mainView.findViewById(R.id.Button02);
		insert_button_table_3 = (Button) mainView.findViewById(R.id.Button03);
		insert_button_table_4 = (Button) mainView.findViewById(R.id.Button04);
		insert_button_table_5 = (Button) mainView.findViewById(R.id.Button05);

	}

	private void listeners() {
		insert_button_table_1.setOnClickListener(this);
		insert_button_table_2.setOnClickListener(this);
		insert_button_table_3.setOnClickListener(this);
		insert_button_table_4.setOnClickListener(this);
		insert_button_table_5.setOnClickListener(this);
	}

	public void onClick(View v) {

		if (v == insert_button_table_1) {
			global_settings.chosed_table = table_name.TABLE_WITH_1_COLUMN;

		} else if (v == insert_button_table_2) {
			global_settings.chosed_table = table_name.TABLE_WITH_2_COLUMNS;
		} else if (v == insert_button_table_3) {
			global_settings.chosed_table = table_name.TABLE_WITH_5_COLUMNS;
		}
		else if (v == insert_button_table_4) {
		global_settings.chosed_table = table_name.SIMPLE_RELATION_TABLES;
	}
		else if (v == insert_button_table_5) {
			Table_interface tb = new Table_with_1_column(getActivity());
			if(global_settings.chosed_table==table_name.TABLE_WITH_1_COLUMN)
				tb = new Table_with_1_column(getActivity());
			else if(global_settings.chosed_table==table_name.TABLE_WITH_2_COLUMNS)
				tb = new Table_with_2_columns(getActivity());
			else if(global_settings.chosed_table==table_name.TABLE_WITH_5_COLUMNS)
				tb = new Table_with_5_columns(getActivity());
			tb.truncate_table();
	}
	}
}
