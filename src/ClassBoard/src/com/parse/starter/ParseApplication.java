package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		ParseObject.registerSubclass(Post.class);
		// Add your initialization code here
		 Parse.initialize(this, "9p1jgEiWtixYQzIYbrh3oiY2rA9aNuCnOQYOhQ4i", "UETquOw1dDTAD0uUQ3jpf8iW9uVLQ5fGfddkFgdK");

	 PushService.setDefaultPushCallback(this,PostListActivity.class);
		
	}

}
