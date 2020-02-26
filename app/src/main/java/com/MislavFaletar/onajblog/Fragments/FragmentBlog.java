package com.MislavFaletar.onajblog.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MislavFaletar.onajblog.DataClasses.Blog;
import com.MislavFaletar.onajblog.Adapters.BlogAdapter;
import com.MislavFaletar.onajblog.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FragmentBlog extends Fragment {

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference blogReference = database.collection("Blog");

    private FragmentBlogViewer mBlogViewer;

    private BlogAdapter blogAdapter;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.blog_fragment, container, false);


        setupBlogRecyclerView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.btnBackStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void setupBlogRecyclerView(){
        Query query = blogReference.orderBy("id", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Blog> options = new FirestoreRecyclerOptions.Builder<Blog>()
                .setQuery(query, Blog.class)
                .build();


        blogAdapter = new BlogAdapter(options);

        RecyclerView recyclerViewBlog = view.findViewById(R.id.recyclerViewBlog);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewBlog.setLayoutManager(linearLayoutManager);
        recyclerViewBlog.setHasFixedSize(true);
        recyclerViewBlog.setAdapter(blogAdapter);

        mBlogViewer = new FragmentBlogViewer();

        //click on item opens a viewer
        blogAdapter.setOnItemClickListener(new BlogAdapter.OnBlogItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CharSequence title = blogAdapter.getItem(position).getTitle();
                String content = blogAdapter.getItem(position).getContent();
                String img = blogAdapter.getItem(position).getImg();

                startBlogViewer();

                mBlogViewer.getData(title,content,img);
            }
        });
    }

    private void startBlogViewer(){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentBlogViewer fragmentBlogViewer = new FragmentBlogViewer();
        manager.beginTransaction().replace(R.id.frame, fragmentBlogViewer)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(blogAdapter != null) {
            blogAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(blogAdapter != null) {
            blogAdapter.stopListening();
        }
    }

//    onStart i onStop su potrebni za RecyclerView


}
