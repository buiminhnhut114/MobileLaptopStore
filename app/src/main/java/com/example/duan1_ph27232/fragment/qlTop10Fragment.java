package com.example.duan1_ph27232.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.Adapter.Top10Adapter;
import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.HoaDonDAO;
import com.example.duan1_ph27232.model.Top10;

import java.util.ArrayList;
import java.util.List;

public class qlTop10Fragment extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Top10> list ;
    private Top10Adapter adapter;
    HoaDonDAO hoaDonDAO;
    ImageView cometop;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_top10);
        cometop=findViewById(R.id.cometop);
        cometop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(qlTop10Fragment.this, MainActivity.class));
            }
        });
        recyclerView = findViewById(R.id.rcv_top10);
        hoaDonDAO=new HoaDonDAO(qlTop10Fragment.this);
        list=hoaDonDAO.getTop10();
        adapter=new Top10Adapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(qlTop10Fragment.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
