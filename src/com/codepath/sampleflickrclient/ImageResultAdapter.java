package com.codepath.sampleflickrclient;

import java.util.List;


import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ImageResultAdapter extends ArrayAdapter<ImageResult> {
	public ImageResultAdapter(Context context, List<ImageResult> images){
		super(context,R.layout.griditemview,images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		return super.getView(position, convertView, parent);
		ImageResult imageInfo = this.getItem(position);
		SmartImageView ivImage;
		if(convertView == null){
			LayoutInflater inflator = LayoutInflater.from(getContext());
			ivImage = (SmartImageView)inflator.inflate(R.layout.griditemview, parent, false);
		} else {
			ivImage = (SmartImageView)convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		
		ivImage.setImageUrl(imageInfo.getThumbUrl());
		return ivImage;
	}
}
