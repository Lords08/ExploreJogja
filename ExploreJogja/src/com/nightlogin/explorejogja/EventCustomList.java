package com.nightlogin.explorejogja;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventCustomList extends ArrayAdapter<String>{
	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;
	private final String[] tempat;
	private final String[] tanggal;
	public EventCustomList(Activity context, String[] web, Integer[] imageId, String[] tempat, String[] tanggal) {
		super(context, R.layout.listeventformat, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
		this.tempat = tempat;
		this.tanggal = tanggal;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.listeventformat, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.eventName);
		TextView txtPlace = (TextView) rowView.findViewById(R.id.eventPlace);
		TextView txtDate = (TextView) rowView.findViewById(R.id.eventDateList);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.coverEvent);
		txtTitle.setText(web[position]);
		txtPlace.setText(tempat[position]);
		txtDate.setText(tanggal[position]);
		imageView.setImageResource(imageId[position]);
		return rowView;
	}
}
