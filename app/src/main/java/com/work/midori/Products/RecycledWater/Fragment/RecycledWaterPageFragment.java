package com.work.midori.Products.RecycledWater.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.work.midori.R;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by hanwe on 16/4/22.
 */
public class RecycledWaterPageFragment extends Fragment
{
    private static String strImageID = "ImageID";
    private int iImageID = 0;

    private PhotoView imageview;

    public static final RecycledWaterPageFragment newInstance(int imageId)
    {
        RecycledWaterPageFragment hp = new RecycledWaterPageFragment();
        Bundle bd = new Bundle();
        bd.putInt(strImageID, imageId);
        hp.setArguments(bd);

        return hp;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        iImageID = getArguments().getInt(strImageID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_recyclewater_view, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        imageview = (PhotoView) view.findViewById(R.id.recyclewater_imageView);

        if(iImageID != 0)
        {
            imageview.setImageResource(iImageID);
//            imageview.setImage(ImageSource.resource(iImageID));
        }


    }
}


