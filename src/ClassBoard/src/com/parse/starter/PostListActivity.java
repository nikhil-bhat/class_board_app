package com.parse.starter;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseInstallation;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class PostListActivity extends Activity {
	private ParseQueryAdapter<Post> mainAdapter;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		ParseInstallation installation = ParseInstallation.getCurrentInstallation();
		installation.put("user",ParseUser.getCurrentUser());
		installation.saveInBackground();
		super.onCreate(savedInstanceState);
		//getListView().setClickable(false);
setContentView(R.layout.main);
		
ParseQueryAdapter.QueryFactory<Post> factory =
new ParseQueryAdapter.QueryFactory<Post>() {
  public ParseQuery<Post> create() {
    
    ParseQuery<Post> query = Post.getQuery();
    query.include("author");
    query.orderByDescending("createdAt");
    query.whereEqualTo("reciever",ParseUser.getCurrentUser().getUsername());
    query.setLimit(10);
    return query;
  }
};


mainAdapter = new ParseQueryAdapter<Post>(this, factory) {
    @Override
    public View getItemView(Post post, View view, ViewGroup parent) {
      if (view == null) {
        view = View.inflate(getContext(), R.layout.post_item, null);
      }
      TextView contentView = (TextView) view.findViewById(R.id.contentView);
      TextView usernameView = (TextView) view.findViewById(R.id.usernameView);
      contentView.setText(post.getMessage());
      usernameView.setText(post.getAuthor().getUsername());
      return view;
    }
  };

  ListView postsView = (ListView) this.findViewById(R.id.listy);
  postsView.setAdapter(mainAdapter);



/*mainAdapter = new ParseQueryAdapter<Post>(this, Post.class);
		mainAdapter.setTextKey("message");
	*/	
		//setListAdapter(mainAdapter);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.listmenu, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_refresh: {
			updatePostList();
			break;
		}
		case R.id.action_logout:{
			ParseUser.logOut();
	        // Start and intent for the dispatch activity
	        Intent intent = new Intent(this, RouteActivity.class);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(intent);
			break;
		}

		

		case R.id.action_new: {
			newPost();
			break;
		}
		
		case R.id.groups: {
			Intent i=new Intent(this,GroupActivity.class);
			startActivityForResult(i, 0);
			break;
		}
		
		
		
		}
		return super.onOptionsItemSelected(item);
	}

	private void updatePostList() {
		mainAdapter.loadObjects();
		//setListAdapter(mainAdapter);
	}

	

	private void newPost() {
		Intent i = new Intent(this, NewPostActivity.class);
		startActivityForResult(i, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			// If a new post has been added, update
			// the list of posts
			updatePostList();
		}
	}

	
	
	
	
	
}
