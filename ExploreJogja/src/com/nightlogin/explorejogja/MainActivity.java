package com.nightlogin.explorejogja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	ImageButton hotels, destinations, fooddrinks, events;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hotels = (ImageButton)findViewById(R.id.BTNhotel);
		destinations = (ImageButton)findViewById(R.id.BTNdestinations);
		fooddrinks = (ImageButton)findViewById(R.id.BTNfooddrinks);
		events = (ImageButton)findViewById(R.id.BTNevents);
		hotels.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, HotelActivityPage.class);
				startActivity(intent);
			}
		});
		destinations.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, DestinationActivityPage.class);
				startActivity(intent);
			}
		});
		
		fooddrinks.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, FoodDrinkPage.class);
				startActivity(intent);
			}
		});
		
		events.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, EventsPage.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
