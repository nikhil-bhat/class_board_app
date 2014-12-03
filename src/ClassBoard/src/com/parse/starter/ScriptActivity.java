package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class ScriptActivity extends Activity {
	Button b1;
	EditText t1;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coms);
		b1=(Button)(findViewById(R.id.but1));
		t1= (EditText)(findViewById(R.id.edt1));
		b1.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(t1.getText().toString()=="")
				{
					Toast.makeText(getApplicationContext(), "Enter some commands", Toast.LENGTH_LONG).show();
				}
				else
				{ ParseObject command=new ParseObject("Command");
				command.put("string",t1.getText().toString());
				command.put("sender",ParseUser.getCurrentUser().getUsername());
				command.saveInBackground();
					Toast.makeText(getApplicationContext(), "Performing!!!", Toast.LENGTH_LONG).show();
					finish();
				}
			}
			
		});
	}

}
