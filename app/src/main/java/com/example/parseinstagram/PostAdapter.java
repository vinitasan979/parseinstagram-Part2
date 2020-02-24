package com.example.parseinstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    Context context;
    List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_item_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post post =posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> list){
        posts.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUname;
        TextView tvDes;
        ImageView ivPosted;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDes=itemView.findViewById(R.id.tvDes);
            tvUname=itemView.findViewById(R.id.tvUName);
            ivPosted=itemView.findViewById(R.id.ivUPost);



        }

        public void bind(Post post) {
            tvUname.setText(post.getUser().getUsername());
            ParseFile image = (ParseFile) post.getImage();
            tvDes.setText(post.getDescription());
            if(image!=null)
                Glide.with(context).load(image.getUrl()).into(ivPosted);




        }
    }
}
