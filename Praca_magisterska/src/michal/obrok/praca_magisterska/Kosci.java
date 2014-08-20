package michal.obrok.praca_magisterska;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Kosci extends Fragment implements OnClickListener{
	private View mainView;
	private Button k4,k6,k8,k10,k12,k20,k50,k100;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mainView = inflater.inflate(R.layout.kosci, container, false);
        initialize();
        listeners();
        return mainView;
}

	private void initialize() {
		k4=(Button)mainView.findViewById(R.id.btnk4);
		k6=(Button)mainView.findViewById(R.id.btnk6);
		k8=(Button)mainView.findViewById(R.id.btnk8);
		k10=(Button)mainView.findViewById(R.id.btnk10);
		k12=(Button)mainView.findViewById(R.id.btnk12);
		k20=(Button)mainView.findViewById(R.id.btnk20);
		k50=(Button)mainView.findViewById(R.id.btnk50);
		k100=(Button)mainView.findViewById(R.id.btnk100);
		
	}

	private void listeners() {
		k4.setOnClickListener(this);
		k6.setOnClickListener(this);
		k8.setOnClickListener(this);
		k10.setOnClickListener(this);
		k12.setOnClickListener(this);
		k20.setOnClickListener(this);
		k50.setOnClickListener(this);
		k100.setOnClickListener(this);		
	}
	/*
	 * Tym razem zamiast tworzyæ osobne listenery dla ka¿dego buttona, u¿yjemy jednego onClick z implementacji onClick
	 */
	@Override
	public void onClick(View v) {
		Random r=new Random();
		if (v==k4)
		{
			k4.setText(r.nextInt(3)+1+"");
		}
		else if(v==k6)
		{
			k6.setText(r.nextInt(5)+1+"");
		}
		else if(v==k8)
		{
			k8.setText(r.nextInt(7)+1+"");
		}
		else if(v==k10)
		{
			k10.setText(r.nextInt(9)+1+"");
		}
		else if(v==k12)
		{
			k12.setText(r.nextInt(11)+1+"");
		}
		else if(v==k20)
		{
			k20.setText(r.nextInt(19)+1+"");
		}
		else if(v==k50)
		{
			k50.setText(r.nextInt(49)+1+"");
		}
		else if(v==k100)
		{
			k100.setText(r.nextInt(99)+1+"");
		}
	}
}
