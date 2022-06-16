package comp3350.littlechef.presentation;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comp3350.littlechef.R;

// CLASS: MealsFragment.java
//
//
// REMARKS: This class creates meal plan fragment
//
//-----------------------------------------
public class MealsFragment extends Fragment
{
    public MealsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals, container, false);
    }
}