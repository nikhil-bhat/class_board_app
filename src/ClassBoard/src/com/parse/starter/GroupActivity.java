package com.parse.starter;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class GroupActivity extends Activity implements OnCheckedChangeListener,Button.OnClickListener {
	ListView lv;
	SparseIntArray myMap ;
Button ok;
CustomAdapter aa;
EditText group;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group);
		lv=(ListView)findViewById(R.id.lv1);
		ok=(Button)findViewById(R.id.but1);
		myMap=new SparseIntArray();
		group=(EditText)findViewById(R.id.edT);
		 aa=new CustomAdapter(this,ParseUser.class);
		lv.setAdapter(aa);
		
		
		 Log.d("here","here")  ;
		
		 ok.setOnClickListener(this);

				
				
			
		
		
		
		}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		int pos=lv.getPositionForView(buttonView);
		if(pos!=ListView.INVALID_POSITION)
		{
			if(isChecked)
			{ Toast.makeText(this,"selected",Toast.LENGTH_LONG).show();
		      myMap.put(pos,1);
			}
		
			else
			{
				myMap.put(pos,0);
			}
		
	}
	


	}

	@Override
	public void onClick(View arg0) {
		
		ArrayList <String>groupmem = new ArrayList<String>();
		if(group.getText().toString()==" ")
		{
			Toast.makeText(getApplicationContext(), "Enter group name", Toast.LENGTH_SHORT).show();
		}
		else
		{
			if(myMap.size()==0)
			{
				Toast.makeText(getApplicationContext(), "Select atleast one", Toast.LENGTH_SHORT).show();
			}
			
			else
			{
				ParseObject Group=new ParseObject("Group");
				Group.add("creator", ParseUser.getCurrentUser());
				for (int i=0;i<myMap.size();i++)
				{
				if (myMap.get(myMap.keyAt(i),0)==1)
				{ if(aa.getItem(myMap.keyAt(i))!=null)
				{
					try
	             //   groupmem.add(aa.getItem(myMap.keyAt(i)).getUsername());			
				{groupmem.add(aa.getItem(myMap.keyAt(i)).getUsername());
				
				}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				}
				
				Group.addAll("Members",groupmem);
				
				Group.saveInBackground();
				finish();
			}
		
		
		
		
	}
	
	
}
	}
}
