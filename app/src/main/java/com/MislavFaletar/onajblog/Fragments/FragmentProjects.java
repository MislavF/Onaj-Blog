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

import com.MislavFaletar.onajblog.DataClasses.Project;
import com.MislavFaletar.onajblog.Adapters.ProjectAdapter;
import com.MislavFaletar.onajblog.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FragmentProjects extends Fragment {

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference projectReference = database.collection("Projects");

    private FragmentProjectViewer mProjectViewer;

    private ProjectAdapter projectAdapter;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.projects_fragment, container, false);


        setupProjectRecyclerView();
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

    private void setupProjectRecyclerView(){
        Query query = projectReference.orderBy("id", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Project> options = new FirestoreRecyclerOptions.Builder<Project>()
                .setQuery(query, Project.class)
                .build();


        projectAdapter = new ProjectAdapter(options);

        RecyclerView recyclerViewProjects = view.findViewById(R.id.recyclerViewProjects);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewProjects.setLayoutManager(linearLayoutManager);
        recyclerViewProjects.setHasFixedSize(true);
        recyclerViewProjects.setAdapter(projectAdapter);

        mProjectViewer = new FragmentProjectViewer();

        //click on item opens a viewer
        projectAdapter.setOnItemClickListener(new ProjectAdapter.OnProjectItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CharSequence title = projectAdapter.getItem(position).getTitle();
                String content = projectAdapter.getItem(position).getContent();
                String img = projectAdapter.getItem(position).getImg();

                startProjectViewer();

                mProjectViewer.getData(title,content,img);
            }
        });
    }

    private void startProjectViewer(){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentProjectViewer fragmentProjectViewer = new FragmentProjectViewer();
        manager.beginTransaction().replace(R.id.frame, fragmentProjectViewer)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(projectAdapter != null) {
            projectAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(projectAdapter != null) {
            projectAdapter.stopListening();
        }
    }

//    onStart i onStop su potrebni za RecyclerView


}
