package com.nightlogin.explorejogja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HotelSelectionPage extends Activity {
	//Deklarasi variabel Listview
		ListView HotelList;
		TextView namaKategori;
		String[] from, web={};
		int[] to;
		Integer[] imageId;
		ListAdapter adapterListView;
		ArrayList<HashMap<String, String>> mylist;
		HashMap<String, String> map;
		int RecordCount,kondisi, id;
		String kategori;
		
		// Progress Dialog
	    private ProgressDialog pDialog;
	    // Creating JSON Parser object
	    JSONParser jParser = new JSONParser();	 
	    // url to get all products list
	    //private static String url_all_products = "http://192.168.137.1/explorejogja/testingbeta.php";
	    //private static String url_all_products = "http://192.168.137.130/pariwisatajogja/testingbeta.php";
	    //private static String url_all_products = "http://10.42.200.193/pariwisatajogja/testingbeta.php";
	    private static final String TAG_SUCCESS = "success";
	    private static final String TAG_PRODUCTS = "product";
	    private static final String TAG_JUDUL = "judul";
	    private static final String TAG_SUBTIPE = "subtipe";
	    private static final String TAG_GAMBAR = "images";
	    //private static final String TAG_BAHAN = "bahan";
	    JSONArray products = null;
	
	//Untuk pengolahan listview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_selection_page);
		//Intent myIntent = getIntent(); // this is just for example purpose
		namaKategori = (TextView)findViewById(R.id.ListCategory);
		Bundle bundle = getIntent().getExtras();
		kondisi = bundle.getInt("kategori");
		HotelList = (ListView)findViewById(R.id.ListHotel);
		
		from = new String[] {"NamaHotel", "GambarHotel"};
        to = new int[] { R.id.HotelName, R.id.HotelImage};
		//Deklarasi untuk listview
        //kategori = Tempat.ambilListKategori(kondisi);
        //namaKategori.setText(kategori);
        //web = Tempat.ambilListNama(kondisi);
        //imageId = Tempat.ambilListGambar(kondisi);
        /*if(web.equals(null))
        {
        	
        }
        else
        {
	        CustomList adapter = new
	                CustomList(HotelSelectionPage.this, web, imageId);
		    HotelList.setAdapter(adapter);
        }*/
        HotelList.setOnItemClickListener(new OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            { 
            	Intent i = new Intent(getApplicationContext(), DetalsPage.class); 
            	Bundle bundle = new Bundle();
            	bundle.putString("namatempat", web[position]);
        		bundle.putInt("jenis", kondisi);
        		i.putExtras(bundle);
                startActivity(i);  
            }
        });
        //Mengambil data JSON dari Server
        new LoadAllProducts().execute();
	}
	
	class LoadAllProducts extends AsyncTask<String, String, String> {
	
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(HotelSelectionPage.this);
            pDialog.setMessage("Loading... Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        protected String doInBackground(String... args) {
            
        	// Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("action",String.valueOf(kondisi)));
            params.add(new BasicNameValuePair("namalokasi", ""));
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(Tempat.getURL(), "GET", params);
            
            // Check your log cat for JSON reponse
            //Log.d("All Products: ", json.toString());
            //String[] data={};
            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
                if (success == 2) 
                {
                    // products found
                    // Getting Array of Products
                    //products = json.getJSONArray(TAG_PRODUCTS);
                	kategori = json.getString(TAG_SUBTIPE);
                	products = json.getJSONArray(TAG_PRODUCTS);
                	web = new String[products.length()];
                	imageId = new Integer[products.length()];
                    // looping through All Products
                    //int j = products.length();
                	Resources res = getResources();
                    for (int i = 0; i < products.length(); i++)
                    {
                        JSONObject c = products.getJSONObject(i);
                        // Storing each json item in variable
                        //web[i] = c.getString(TAG_JUDUL).toString();
                        String e = c.getString(TAG_JUDUL).toString();
                        String gambar = c.getString(TAG_GAMBAR).toString();
                        web[i] = e;   
                        if(gambar.equals("") || gambar.equals(null) || gambar.equals("null")) gambar="events";
                        id = res.getIdentifier(gambar,"drawable",getPackageName());
                        imageId[i] = id;  
                        //web[i] = e;
                        //String resep = c.getString(TAG_RESEP);                  
                    }    
                } 
                else {
                    // no products found
                    // Launch Add New product Activity
                    //Intent i = new Intent(getApplicationContext(),NewProductActivity.class);
                    // Closing all previous activities
                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
 
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                	//toastLong(a);
                	namaKategori = (TextView)findViewById(R.id.ListCategory);
                	namaKategori.setText(kategori);
                	CustomList adapter = new
        	                CustomList(HotelSelectionPage.this, web, imageId);
        		    HotelList.setAdapter(adapter);
                }
            });
 
        }
    }
	
	public void toastShort(String message)
	{
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
	}
	
	public void toastLong(String message)
	{
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
	}
	
	public void showList()
	{
		CustomList adapter = new
                CustomList(HotelSelectionPage.this, web, imageId);
		HotelList.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hotel_selection_page, menu);
		return true;
	}

}
