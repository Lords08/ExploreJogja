package com.nightlogin.explorejogja;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalsPage extends FragmentActivity implements ActionBar.TabListener 
{
    String imageURL = "http://maps.googleapis.com/maps/api/staticmap?center="+map+"&zoom=16&size=600x900&markers=color:blue|label:A|"+map+"&sensor=false";
	public static final String defaultImage = "yogya";
	final String TAG_PRODUCTS = "product";
    final String TAG_JUDUL = "judul";
    final String TAG_DESKRIPSI= "deskripsi";
    final String TAG_ALAMAT = "alamat";
    final String TAG_PRICE = "price_range";
    final String TAG_OPENING = "opening_hours";
    final String TAG_CP = "cp_web";
    final String TAG_FACILITY = "facility";
    final String TAG_REVIEW= "review";
    final String TAG_MAP = "map";
    final String TAG_KABUPATEN = "kabupaten";
    final String TAG_TIPE = "tipe"; 
    final String TAG_SUBTIPE = "subtipe";
    final String TAG_GAMBAR = "images";
    static String judul, deskripsi, alamat, price_range, opening_hours, cp_web, 
    			  facility, review, map, kabupaten, tipe, subtipe, gambar;
    
    private ProgressDialog pDialog;
    public static int Jenis, id;
    public String namaTempat; 
    JSONArray products = null;
    JSONParser jParser = new JSONParser();
    ActionBar actionBar;
    static TextView textJudul, textJudulInfo, textDeskripsi, textCP, textFasilitas, textSubtipe;
    static TextView textPrice, textOpening, textReview, textMap, textAlamat, textKabupaten, textTipe;
    static ImageView imageTempat, imageTempatInfo;
    static Drawable drawable, drawableMap;
    static ImageView googleMap;
    private static final int IO_BUFFER_SIZE = 4 * 1024;
    static Bitmap bitmap=null;
	
    /**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detals_page);
		Jenis = getIntent().getExtras().getInt("jenis");
		namaTempat = getIntent().getExtras().getString("namatempat");
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() 
		{
			@Override
			public void onPageSelected(int position) 
			{
				actionBar.setSelectedNavigationItem(position);
			}
		});
		addTab();
		new LoadAllProducts().execute();
	}
	
	public void addTab()
	{
		actionBar.addTab(actionBar.newTab().setText("DESCRIPTION").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("INFO").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("MAP").setTabListener(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.detals_page, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,FragmentTransaction fragmentTransaction)
	{
		// When the given tab is selected, switch to the corresponding page in the ViewPager.
		int f = tab.getPosition();
		mViewPager.setCurrentItem(tab.getPosition());
		if(f==2)
		{
		    //rootView = inflater.inflate(R.layout.mappage,container, false);
			googleMap = (ImageView)findViewById(R.id.imageMap);
			if(map.equals(defaultImage)) googleMap.setImageDrawable(drawableMap);		
			else googleMap.setImageBitmap(bitmap); 
			//else googleMap.setImageDrawable(drawableMap);
			//else googleMap.setImageBitmap(bitmap); 
		}
		//loadDetails(tab);
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,FragmentTransaction fragmentTransaction) {}

	@Override
	public void onTabReselected(ActionBar.Tab tab,FragmentTransaction fragmentTransaction) {}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter 
	{
		public SectionsPagerAdapter(FragmentManager fm) {super(fm);}
		@Override
		public Fragment getItem(int position) 
		{
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position+1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {return 3;}

		@Override
		public CharSequence getPageTitle(int position) 
		{
			Locale l = Locale.getDefault();
			switch (position) 
			{
				case 0: return getString(R.string.title_section1).toUpperCase(l);
				case 1: return getString(R.string.title_section2).toUpperCase(l);
				case 2: return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment 
	{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		public DummySectionFragment() {}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
		{
			View rootView=null;
			int tab = getArguments().getInt(ARG_SECTION_NUMBER);		
			//if(tab==2) tab=1;
			//else if(tab==1) tab=0;
			//else tab=2;
			
			if(tab==1) 
			{
				rootView = inflater.inflate(R.layout.descriptionpage,container, false);
				imageTempat = (ImageView)rootView.findViewById(R.id.idGambar);
				textJudul = (TextView)rootView.findViewById(R.id.idJudul);
				textDeskripsi = (TextView)rootView.findViewById(R.id.idDeskripsi);
			}
			else if(tab==2) 
			{
				rootView = inflater.inflate(R.layout.infopage,container, false);
				imageTempatInfo = (ImageView)rootView.findViewById(R.id.idGambarInfo);
				textJudulInfo = (TextView)rootView.findViewById(R.id.idJudulInfo);
				textCP = (TextView)rootView.findViewById(R.id.idKontak);
				textFasilitas = (TextView)rootView.findViewById(R.id.idFasilitas);    	
            	textPrice= (TextView)rootView.findViewById(R.id.idPrice); 
            	textOpening = (TextView)rootView.findViewById(R.id.idOpening); 
            	textReview = (TextView)rootView.findViewById(R.id.idReview); 
            	textMap = (TextView)rootView.findViewById(R.id.idMap); 
            	textAlamat = (TextView)rootView.findViewById(R.id.idAlamat);    	
            	textKabupaten = (TextView)rootView.findViewById(R.id.idKabupaten);
            	textTipe = (TextView)rootView.findViewById(R.id.idTipe);
            	textSubtipe = (TextView)rootView.findViewById(R.id.idSubtipe);
			}
			else if(tab==3)
			{
				rootView = inflater.inflate(R.layout.mappage,container, false);
				googleMap = (ImageView)rootView.findViewById(R.id.imageMap);
			}
			loadDetails(tab);
			return rootView;
		}
		public static void loadDetails(int no)
        {
        	if(no==1)
        	{       		
        		//imageTempat.setImageResource(id);
        		imageTempat.setImageDrawable(drawable);
        		textJudul.setText(judul);
            	textDeskripsi.setText(deskripsi);
        	}
        	else if(no==2)
        	{
        		//imageTempatInfo.setImageResource(id);
        		imageTempatInfo.setImageDrawable(drawable);
        		textJudulInfo.setText(judul);
            	textCP.setText(cp_web);
            	textFasilitas.setText(facility);     	
            	textPrice.setText(price_range);
            	textOpening.setText(opening_hours);
            	textReview.setText(review);
            	textMap.setText(map);
            	textAlamat.setText(alamat);      	
            	textKabupaten.setText(kabupaten);
            	textTipe.setText(tipe);
            	textSubtipe.setText(subtipe);
        	}
        	else if(no==3)
        		{
        			if(map.equals(defaultImage)) googleMap.setImageDrawable(drawableMap); 
        			//if(map.equals("") || map.equals(null) || map.equals("null")) googleMap.setImageBitmap(bitmap);
        			else googleMap.setImageBitmap(bitmap);
        			//else googleMap.setImageBitmap(bitmap); 
        		}
        }
	}

	
	class LoadAllProducts extends AsyncTask<String, String, String> 
	{	
		JSONObject c;
		@Override
        protected void onPreExecute() 
		{
            super.onPreExecute();
            pDialog = new ProgressDialog(DetalsPage.this);
            pDialog.setMessage("Loading... Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) 
        { 
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("action", String.valueOf(Jenis)));
            params.add(new BasicNameValuePair("namalokasi", namaTempat));
            JSONObject json = jParser.makeHttpRequest(Tempat.getURL(), "GET", params);
            Resources res = getResources();
            try 
            {        
                products = json.getJSONArray(TAG_PRODUCTS);         
                c = products.getJSONObject(0);
                judul= c.getString(TAG_JUDUL).toString();
                deskripsi= c.getString(TAG_DESKRIPSI).toString();
                alamat= c.getString(TAG_ALAMAT).toString();
                price_range= c.getString(TAG_PRICE).toString();
                opening_hours= c.getString(TAG_OPENING).toString();
                cp_web= c.getString(TAG_CP).toString();
                facility= c.getString(TAG_FACILITY).toString();
                review= c.getString(TAG_REVIEW).toString();
                map= c.getString(TAG_MAP).toString();
                kabupaten= c.getString(TAG_KABUPATEN).toString();
                subtipe=c.getString(TAG_SUBTIPE).toString();
                tipe= c.getString(TAG_TIPE).toString();  
                gambar= c.getString(TAG_GAMBAR).toString();
                //id = getResources().getIdentifier("com.nightlogin.explorejogja:drawable/" + gambar, null, null);
                
                if(gambar.equals("") || gambar.equals(null) || gambar.equals("null")) gambar=defaultImage;       
                String mDrawableName = gambar;
                id = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                drawable = res.getDrawable(id);  
                
                loadMap();
                filterOutput();
            } catch (JSONException e) {e.printStackTrace();}
            return null;
        }
        
        public void filterOutput()
        {
        	if(deskripsi.equals("") || deskripsi.equals(null) || deskripsi.equals("-") || deskripsi.equals("null")) deskripsi="Masih belum ada deskripsi mengenai tempat ini.";
        	if(alamat.equals("") || alamat.equals(null)) alamat="-";
        	if(price_range.equals("") || price_range.equals(null)) price_range="-";
        	if(opening_hours.equals("") || opening_hours.equals(null)) opening_hours="-";
        	if(cp_web.equals("") || cp_web.equals(null)) cp_web="-";
        	if(facility.equals("") || facility.equals(null)) facility="-";
        	if(review.equals("") || review.equals(null)) review="-";
        	if(map.equals("") || map.equals(null) || map.equals("null")) map="-";
        	if(kabupaten.equals("") || kabupaten.equals(null)) kabupaten="-";
        	if(subtipe.equals("") || subtipe.equals(null)) subtipe="-";
        	if(tipe.equals("") || tipe.equals(null)) tipe="-";
        }
        
        public void loadMap()
        {
        	if(map.equals("") || map.equals(null) || map.equals("null")) 
            {
        		Resources res = getResources();
        		map=defaultImage;
            	String mDrawableName = map;
                id = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                drawableMap = res.getDrawable(id);
                //googleMap.setImageDrawable(drawable);
            }
            else
            {	
                try {
					//bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageURL).getContent());
                	URL url = new URL(imageURL);
                	bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                	//imageView.setImageBitmap(bmp);
					//googleMap.setImageBitmap(bitmap); 
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        
        protected void onPostExecute(String file_url) 
        {
            pDialog.dismiss();
            runOnUiThread(new Runnable() 
            {
                public void run() 
                {          	
                	loadDetails();
                }
            });
        }
        
        public void loadDetails()
        {
        	//imageTempat.setImageResource(id);
        	//imageTempatInfo.setImageResource(id);
        	imageTempat.setImageDrawable(drawable);
        	imageTempatInfo.setImageDrawable(drawable);
        	//googleMap = (ImageView)findViewById(R.id.imageMap);
			//if(map.equals(defaultImage)) googleMap.setImageDrawable(drawableMap);		
			//else googleMap.setImageBitmap(bitmap); 
        	//if(map.equals("") || map.equals(null) || map.equals("null")) googleMap.setImageBitmap(bitmap);
        	//else googleMap.setImageDrawable(drawableMap);  
        	textJudul.setText(judul);
        	textJudulInfo.setText(judul);
        	textDeskripsi.setText(deskripsi);
        	textCP.setText(cp_web);
        	textFasilitas.setText(facility);     	
        	textPrice.setText(price_range);
        	textOpening.setText(opening_hours);
        	textReview.setText(review);
        	textMap.setText(map);
        	textAlamat.setText(alamat);      	
        	textKabupaten.setText(kabupaten);
        	textTipe.setText(tipe);
        	textSubtipe.setText(subtipe);
        }
    }
}
