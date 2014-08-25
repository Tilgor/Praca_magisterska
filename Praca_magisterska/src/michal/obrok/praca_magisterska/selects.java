package michal.obrok.praca_magisterska;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.NumberPicker;

public class selects extends Fragment {
	
	private View mainView;
	

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mainView = inflater.inflate(R.layout.selects, container, false);
		DB_Manager db = new DB_Manager(getActivity());
		Table_interface tb1 = new Table_with_1_column(getActivity());
		
		db.create_1_table();
//		db.drop_table("TABLE_WITH_1_COLUMN");
		TextView result = (TextView)mainView.findViewById(R.id.Result);
		tb1.simple_insert(12);
		if (result == null) {Log.e("dupa", "DUPA");};
		result.setText(tb1.simple_insert_rows(100) +"");
		
        return mainView;
        

}

}
