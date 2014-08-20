package pl.szymonmotyka.tutorialrandomizer;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Lotto extends Fragment {
	/*
	 * Zmienna odpowiadajaca za widok
	 */
	View mainView;
	TextView txtNr1,txtNr2,txtNr3,txtNr4,txtNr5,txtNr6;
	Button btnRandom;
	Context context;
	private SharedPreferences preferences;
	public static final String USTAWIENIA = "ustawienia";
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			mainView = inflater.inflate(R.layout.lotto, container, false);
	        initialize();
	        restore();
	        listeners();	        
	        return mainView;
	}
	/*
	 * Za pomoc¹ get... pobieramy dowoln¹ zmienn¹ z ustawieñ. Dla przyk³adu tutaj pobierane s¹ Stringi(¿eby nie bawiæ siê konwersj¹ getText>String i setText>String
	 * Pierwszym parametrem jest nazwa ustawienia, a drugim wartoœæ domyœlna, gdy takiego ustawienia nie znajdzie.
	 */
	private void restore() {
		txtNr1.setText(preferences.getString("nr1", "0"));
		txtNr2.setText(preferences.getString("nr2", "0"));
		txtNr3.setText(preferences.getString("nr3", "0"));
		txtNr4.setText(preferences.getString("nr4", "0"));
		txtNr5.setText(preferences.getString("nr5", "0"));
		txtNr6.setText(preferences.getString("nr6", "0"));

	}
	/*
	 * Metoda zapisuj¹ca nasze dane do ustwaieñ
	 */
	private void save(){
		SharedPreferences.Editor editor= preferences.edit();
		/*
		 *  za pomoc¹ putString przekazujemy zmienn¹ do ustawieñ, pierwszym parametrem jest klucz, do którego siê potem odwo³ujemy.
		 */
		editor.putString("nr1", txtNr1.getText().toString());
		editor.putString("nr2", txtNr2.getText().toString());
		editor.putString("nr3", txtNr3.getText().toString());
		editor.putString("nr4", txtNr4.getText().toString());
		editor.putString("nr5", txtNr5.getText().toString());
		editor.putString("nr6", txtNr6.getText().toString());
		editor.commit();
	}
	private void listeners() {
		btnRandom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Random rand=new Random();
				txtNr1.setText(rand.nextInt(48)+1+"");
				txtNr2.setText(rand.nextInt(48)+1+"");
				txtNr3.setText(rand.nextInt(48)+1+"");
				txtNr4.setText(rand.nextInt(48)+1+"");
				txtNr5.setText(rand.nextInt(48)+1+"");
				txtNr6.setText(rand.nextInt(48)+1+"");
				save();
			}
		});
	}
	
	private void initialize() {
		txtNr1=(TextView)mainView.findViewById(R.id.nr1);
		txtNr2=(TextView)mainView.findViewById(R.id.nr2);
		txtNr3=(TextView)mainView.findViewById(R.id.nr3);
		txtNr4=(TextView)mainView.findViewById(R.id.nr4);
		txtNr5=(TextView)mainView.findViewById(R.id.nr5);
		txtNr6=(TextView)mainView.findViewById(R.id.nr6);
		btnRandom=(Button)mainView.findViewById(R.id.btnrandom);
		context=getActivity().getApplicationContext();
		preferences = context.getSharedPreferences(USTAWIENIA, Activity.MODE_PRIVATE);
	}
}
