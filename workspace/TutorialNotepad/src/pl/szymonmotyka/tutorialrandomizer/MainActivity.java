package pl.szymonmotyka.tutorialrandomizer;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	/*
	 * Page adapter jest adapterem obs³uguj¹cym wiele fragmentów w naszej aplikacji
	 */
	private PagerAdapter mPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	super.setContentView(R.layout.activity_main);
	this.initialisePaging();
	}

	/**
	* Tworzy strony naszej aplikacji
	*/
	private void initialisePaging() {

	List<Fragment> fragments = new Vector<Fragment>();
	//Dodaje fragmenty do listy, któr¹ u¿yjemy w naszym adapterze. W tym przypadku doda³em 2 takie same (s¹ jako osobne aktywnoœci) by pokazaæ jak to dzia³a w praktyce
	fragments.add(Fragment.instantiate(this, Lotto.class.getName()));
	fragments.add(Fragment.instantiate(this, Kosci.class.getName()));
	
	this.mPagerAdapter = new myPageAdapter(super.getSupportFragmentManager(), fragments);
	ViewPager pager = (ViewPager) super.findViewById(R.id.awesomepager);
	pager.setAdapter(this.mPagerAdapter);
	}  
}
/*
 * Jest to prosty adapter dla ViewPager do prze³¹czania stron
 */
class myPageAdapter extends FragmentPagerAdapter {

	/*
	 * fragments = Lista  fragmentow programu
	 */
	private final List<Fragment> fragments;

	public myPageAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
}
