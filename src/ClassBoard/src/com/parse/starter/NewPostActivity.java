package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class NewPostActivity extends Activity implements OnItemSelectedListener{
	Button b1;
	EditText t2;
	EditText t3;
	Spinner s1;
	Post newpost;
	String reciever="broadcast";
	 ParseQueryAdapter<ParseUser> spad;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lain);
		s1=(Spinner)findViewById(R.id.contact);
		spad=new ParseQueryAdapter<ParseUser>(this,ParseUser.class);
		spad.setTextKey("username");
		s1.setAdapter(spad);
		s1.setOnItemSelectedListener(this);
        b1=(Button)findViewById(R.id.b1);
        t2=(EditText)findViewById(R.id.t2);
        t3=(EditText)findViewById(R.id.title);
        
b1.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				String text=t2.getText().toString();
				String title=t3.getText().toString();
				 newpost=new Post();
				
				    
				    newpost.setMessage(text);
				 newpost.setTitle(title);
				   newpost.setReciever(reciever);
				    newpost.setAuthor(ParseUser.getCurrentUser());
				 
				    newpost.saveInBackground(new SaveCallback() {
				 
				        @Override
				        public void done(ParseException e) {
				            if (e == null) {
				               setResult(Activity.RESULT_OK);
				                finish();
				            } else {
				                Toast.makeText(null, "Error saving: " + e.getMessage(),Toast.LENGTH_SHORT).show();
				            }
				        }
				 
				    });
				 
			}
        	
        });
		
		
		
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		ParseUser recir=spad.getItem(arg2);
		reciever=recir.getUsername();
		
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	reciever="broadcast";
	}
}
