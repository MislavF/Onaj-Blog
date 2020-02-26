package com.MislavFaletar.onajblog.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MislavFaletar.onajblog.DataClasses.Blog;
import com.MislavFaletar.onajblog.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class BlogAdapter extends FirestoreRecyclerAdapter<Blog, BlogAdapter.BlogViewHolder>  {

    private OnBlogItemClickListener mListener;

    public interface OnBlogItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnBlogItemClickListener listener){
        mListener = listener;
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */


    public BlogAdapter(@NonNull FirestoreRecyclerOptions<Blog> options) {
        super(options);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewTitle;
        public TextView mTextViewContent;
        public ImageView mImageViewImg;

        public BlogViewHolder(@NonNull View itemView, final OnBlogItemClickListener listener) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.blogPostTitle);
            mTextViewContent = itemView.findViewById(R.id.blogPostContent);
            mImageViewImg = itemView.findViewById(R.id.blogPostImage);

            //item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

    @Override
    protected void onBindViewHolder(@NonNull BlogViewHolder holder, int position, @NonNull Blog model) {
        holder.mTextViewTitle.setText(model.getTitle());
        holder.mTextViewContent.setText(model.getContent());
        Picasso.get().load(model.getImg()).into(holder.mImageViewImg);


    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View View = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.blog_card,
                viewGroup, false);
        BlogViewHolder bvh = new BlogViewHolder(View, mListener);
        return bvh;
    }

}
