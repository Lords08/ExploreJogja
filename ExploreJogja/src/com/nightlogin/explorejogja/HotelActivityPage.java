package com.nightlogin.explorejogja;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class HotelActivityPage extends Activity {
	ImageButton hotelsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_activity_page);
	}
	
	public void imgHotelClk(View v)
	{
		Intent intent = new Intent(HotelActivityPage.this, HotelSelectionPage.class);
		intent.putExtra("kategori", 5);
		startActivity(intent);
	}
	public void imgVilaClk(View v)
	{
		Intent i = new Intent(HotelActivityPage.this, HotelSelectionPage.class); 
		i.putExtra("kategori", 6);          
        startActivity(i);  
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hotel_activity_page, menu);
		return true;
	}

}
