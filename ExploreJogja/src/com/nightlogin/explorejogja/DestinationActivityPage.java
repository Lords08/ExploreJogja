package com.nightlogin.explorejogja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class DestinationActivityPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destination_activity_page);
	}

	public void imgHistoClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 7);          
        startActivity(i);  
	}
	public void imgTempleClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 8);          
        startActivity(i);  
	}
	public void imgSpadvenClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 10);          
        startActivity(i);  
	}
	public void imgNatClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 11);          
        startActivity(i);    
	}
	
	public void imgMuseumClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 12);          
        startActivity(i);  
	}
	public void imgVillageClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 9);          
        startActivity(i);  
	}
	public void imgZooClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 13);          
        startActivity(i);  
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.destination_activity_page, menu);
		return true;
	}

}
