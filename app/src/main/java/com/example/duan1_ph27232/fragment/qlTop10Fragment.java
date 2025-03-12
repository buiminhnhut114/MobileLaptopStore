package com.example.duan1_ph27232.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_ph27232.Adapter.Top10Adapter;
import com.example.duan1_ph27232.MainActivity;
import com.example.duan1_ph27232.R;
import com.example.duan1_ph27232.dao.HoaDonDAO;
import com.example.duan1_ph27232.model.Top10;

import java.util.List;

public class qlTop10Fragment extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Top10Adapter adapter;
    private HoaDonDAO hoaDonDAO;
    private ImageView cometop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_top10);

        // Nút quay lại về MainActivity
        cometop = findViewById(R.id.cometop);
        cometop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(qlTop10Fragment.this, MainActivity.class));
            }
        });

        recyclerView = findViewById(R.id.rcv_top10);
        hoaDonDAO = new HoaDonDAO(this);
        List<Top10> list = hoaDonDAO.getTop10();
        adapter = new Top10Adapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
