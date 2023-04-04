package com.example.timerrv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_next=findViewById(R.id.next_button);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NextActivity.class);
                startActivity(i);
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Long> timeList = new ArrayList<>();
        timeList.add(60000L); // 1 minute
        timeList.add(300000L); // 5 minutes
        timeList.add(900000L); // 15 minutes
        RvAdapter adapter = new RvAdapter(timeList);
        recyclerView.setAdapter(adapter);

    }
}