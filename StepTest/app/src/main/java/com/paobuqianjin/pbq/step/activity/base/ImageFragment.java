package com.paobuqianjin.pbq.step.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lwkandroid.imagepicker.data.ImageBean;
import com.lwkandroid.imagepicker.widget.photoview.OnPhotoTapListener;
import com.lwkandroid.imagepicker.widget.photoview.PhotoView;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.presenter.Presenter;

import java.util.List;

/**
 * Created by admin
 * on 2017/7/12.
 */
public class ImageFragment extends Fragment {
    private static final String IMAGE_URL = "image";
    PhotoView image;
    private int imageUrl;

    private static List<ImageBean> mData;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(int param1, List<ImageBean> data) {
        mData = data;
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(IMAGE_URL, param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrl = getArguments().getInt(IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        image = (PhotoView) view.findViewById(R.id.image);
        Presenter.getInstance(getContext()).getOriginImage(mData.get(imageUrl).getImagePath(), image);
        image.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
