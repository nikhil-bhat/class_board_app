package com.parse.starter;



import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomAdapter extends ParseQueryAdapter<ParseUser>{

	Context context;

public CustomAdapter(Context context, Class clazz) {
		super(context, clazz);
		this.context=context;
		// TODO Auto-generated constructor stub
	}

private static class Dataholder
{
	TextView tx;
	CheckBox cb;
}

	

	public View getItemView(ParseUser object, View Cv, ViewGroup parent) {
		
		Dataholder holder=new Dataholder();
		
		  if (Cv == null) {
			  Cv = View.inflate(getContext(), R.layout.row, null); 
			  TextView name = (TextView) Cv.findViewById(R.id.te1);
			    CheckBox cb = (CheckBox) Cv.findViewById(R.id.cB1);
			  holder.tx=name;
			  holder.cb=cb;

			  Cv.setTag(holder);
		  }
		  
		 
		    
		 
		    
		 holder=(Dataholder)Cv.getTag();
		 holder.tx.setText(object.getUsername());

		 holder.cb.setOnCheckedChangeListener((GroupActivity)context); 
		  
		 
		  return Cv;
		}
		 
		
		
	
	
	
}