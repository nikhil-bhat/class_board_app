package com.parse.starter;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
public Post()
{
	
}
public String getTitle()
{
	return getString("title");
	
}

public void setTitle(String title)
{
	put("title",title);
	
}

public ParseUser getAuthor()
{
	return getParseUser("author");
}

public void setAuthor(ParseUser user)
{
	put("author",user);
	
}
public String getMessage()
{
	return getString("message");
}

public void setMessage(String message)
{
	put("message",message);
}
public ParseUser getReciever()
{
	return getParseUser("reciever");
}
public void setReciever(String reciever)
{
	put("reciever",reciever);
}
public static ParseQuery<Post> getQuery() {
    return ParseQuery.getQuery(Post.class);
  }

}
