package com.MislavFaletar.onajblog.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MislavFaletar.onajblog.R;
import com.squareup.picasso.Picasso;

public class FragmentProjectViewer extends Fragment {
    public TextView mTextViewTitle;
    public TextView mTextViewContent;
    public ImageView mImageViewImg;

    public static CharSequence mTitle;
    public static String mContent;
    public static String mImg;


    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.project_viewer_fragment, container, false);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        mTextViewTitle = view.findViewById(R.id.projectViewerTitle);
        mTextViewContent = view.findViewById(R.id.projectViewerContent);
        mImageViewImg = view.findViewById(R.id.projectViewerImg);

        mTextViewContent.setMovementMethod(new ScrollingMovementMethod());

        setData();

    }

    public void getData(CharSequence title, String content, String img){
        mTitle = title;
        mContent = content;
        mImg = img;

    }

    public void setData(){

        mTextViewTitle.setText(mTitle);
        mTextViewContent.setText(mContent);
        Picasso.get().load(mImg).into(mImageViewImg);

    }
}
