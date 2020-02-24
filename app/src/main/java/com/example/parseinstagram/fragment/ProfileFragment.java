package com.example.parseinstagram.fragment;

import android.util.Log;

import com.example.parseinstagram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {
    @Override
    protected void queryPosts() {
        ParseQuery<Post> postQuery= new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        postQuery.setLimit(20);
        postQuery.addDescendingOrder(Post.KEY_CREATED_KEY);
        //less expensive operation is find in background
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {

                if(e!=null){
                    Log.e(TAG,"Error with query ");
                    e.printStackTrace();
                    return;

                }
                for(Post post:posts) {
                    Log.d(TAG, "Post: " + post.getDescription() + " USERNAME : " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
