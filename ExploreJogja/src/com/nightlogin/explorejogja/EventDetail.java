package com.nightlogin.explorejogja;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetail extends Activity {
	TextView title, date, time, description;
	ImageView cover;
	int kondisi;
	String[] data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_detail);
		title = (TextView)findViewById(R.id.eventTitle);
		date = (TextView)findViewById(R.id.eventDate);
		time = (TextView)findViewById(R.id.eventJam);
		description = (TextView)findViewById(R.id.eventDesc);
		cover = (ImageView)findViewById(R.id.eventCoverDetail);
		Bundle bundle = getIntent().getExtras();
		kondisi = bundle.getInt("nomor");
		data = Tempat.ambilDetailEvent(kondisi);
		LoadAllData();
		String c="";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_detail, menu);
		return true;
	}
	
	public void LoadAllData()
	{
		//GetImage
		Resources res = getResources();
		String mDrawableName = data[4];
        int id = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(id);
        //set data
        title.setText(data[1]);
        date.setText(data[0]);
        time.setText(data[2]);
        description.setText(data[3]);
        cover.setImageDrawable(drawable);
	}
}
