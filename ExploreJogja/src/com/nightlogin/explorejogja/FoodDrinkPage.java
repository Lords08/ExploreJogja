package com.nightlogin.explorejogja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class FoodDrinkPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_drink_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_drink_page, menu);
		return true;
	}
	
	public void imgRestoClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 1);          
        startActivity(i);
	}
	
	public void imgCafeClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 2);          
        startActivity(i);
	}
	
	public void imgBakeryClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 3);          
        startActivity(i);
	}
	
	public void imgBarClk(View v)
	{
		Intent i = new Intent(getApplicationContext(), HotelSelectionPage.class); 
        i.putExtra("kategori", 4);          
        startActivity(i);
	}
	
}
