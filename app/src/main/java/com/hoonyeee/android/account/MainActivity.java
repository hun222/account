package com.hoonyeee.android.account;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.hoonyeee.android.account.adapater.BaseAdapter;
import com.hoonyeee.android.account.domain.Account;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BaseAdapter adapter;
    DBConnect con;
    List<Account> datas;
    FloatingActionButton fButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fButton = findViewById(R.id.fButton);

        con = new DBConnect(this);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new BaseAdapter(this, con);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 새로운 카드 추가
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"button clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), WriteActivity.class);
                startActivityForResult(intent, 999);
            }
        });

        refreshList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==999){
            if(resultCode==RESULT_OK){
                refreshList();
            }
        }
    }

    public void refreshList(){
        try {
            datas = con.selectAll();
            adapter.setData(datas);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "DB error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
