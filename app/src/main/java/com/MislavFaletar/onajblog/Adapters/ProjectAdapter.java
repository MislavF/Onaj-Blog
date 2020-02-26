package com.MislavFaletar.onajblog.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MislavFaletar.onajblog.DataClasses.Project;
import com.MislavFaletar.onajblog.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ProjectAdapter extends FirestoreRecyclerAdapter<Project, ProjectAdapter.ProjectViewHolder> {

    private OnProjectItemClickListener mListener;

    public interface OnProjectItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnProjectItemClickListener listener){
        mListener = listener;
    }


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public ProjectAdapter(@NonNull FirestoreRecyclerOptions<Project> options) {
        super(options);
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewContent;
        ImageView imageViewPost;

        public ProjectViewHolder(@NonNull View itemView, final OnProjectItemClickListener listener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.projectPostTitle);
            textViewContent = itemView.findViewById(R.id.projectPostContent);
            imageViewPost = itemView.findViewById(R.id.projectPostImage);

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
    protected void onBindViewHolder(@NonNull ProjectViewHolder holder, int position, @NonNull Project model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewContent.setText(model.getContent());
        Picasso.get().load(model.getImg()).into(holder.imageViewPost);
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View View = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.projects_card,
                viewGroup, false);
        return new ProjectViewHolder(View, mListener);
    }
}
