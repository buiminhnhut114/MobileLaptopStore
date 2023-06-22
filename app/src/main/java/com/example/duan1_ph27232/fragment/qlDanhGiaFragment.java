package com.example.duan1_ph27232.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_ph27232.R;


public class qlDanhGiaFragment extends Fragment {


    public qlDanhGiaFragment() {
        // Required empty public constructor
    }


    public static qlDanhGiaFragment newInstance() {
        qlDanhGiaFragment fragment = new qlDanhGiaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ql_danh_gia, container, false);
    }
}