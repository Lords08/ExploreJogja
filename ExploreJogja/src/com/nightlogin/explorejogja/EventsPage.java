package com.nightlogin.explorejogja;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EventsPage extends Activity {
	ListView daftarList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events_page);
		
		//Daftar Event
		String[] web = {
				"Pameran Seni Grafis Etiket Batik Tenun 1930, 1990",
				"Perempuan Lahung Bak Bidadari",
				"ARTCTION",
				"Jazztraffic Festival",
				"Young Entrepreneurs Show National Seminar",
				"Diplomatic Course & Table Manner",
				"National Roadshow EXPO 2013 Toys & Games",
				"Stand Up Night #2"
		};
		String[] tgl = {
				"3-12 Des",
				"6-12 Des",
				"7 Des",
				"7 Des",
				"7 Des",
				"7-8 Des",
				"7-8 Des",
				"14 Des"
		};
		
		String[] tempat = {
				"Bentara Budaya Yogyakarta",
				"Kedai Belakang Jl. Taman Siswa",
				"Gedung Pusat Kebudayaan Koesnadi Hardjasoemantri",
				"Grand Pacific Hall",
				"Auditorium Magister Manajemen UGM",
				"Gedung Lengkung & Hotel Arjuna",
				"",
				"Grha Sabha PramanaUGM"
		};
		Integer[] cover = {
				R.drawable.batiktenun,
				R.drawable.perempuanlahungbakbidadari,
				R.drawable.artction,
				R.drawable.jazztrafficfestival,
				R.drawable.youngentrepreneursshow,
				R.drawable.diplomaticcoursetablemanner,
				R.drawable.toysgame,
				R.drawable.standupbight2
		};
		
		daftarList = (ListView)findViewById(R.id.ListEvents);
		EventCustomList adapter = new EventCustomList(EventsPage.this, web, cover, tempat, tgl);
	    daftarList.setAdapter(adapter);
	    daftarList.setOnItemClickListener(new OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            { 
            	Intent i = new Intent(getApplicationContext(), EventDetail.class); 
            	Bundle bundle = new Bundle();
            	//bundle.putString("tanggal", position);
        		bundle.putInt("nomor", position+1);
        		i.putExtras(bundle);
                startActivity(i);  
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.events_page, menu);
		return true;
	}

}
