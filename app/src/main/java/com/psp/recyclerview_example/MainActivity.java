package com.psp.recyclerview_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.psp.recyclerview_example.adapter.MyAdapter;
import com.psp.recyclerview_example.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.MyAdapterListener {

    ActivityMainBinding binding;

    private final ArrayList<String> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initialize adapter
        adapter = new MyAdapter(this,this);
        binding.recyclerViewMain.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recyclerViewMain.setHasFixedSize(true);
        binding.recyclerViewMain.setAdapter(adapter);

        // add items
        add();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = binding.edtMessage.getText().toString().trim();
                if(adapter.addItem(msg)) {
                    Snackbar.make(binding.btnAdd,"Save success",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.clear()) {
                    Snackbar.make(binding.btnAdd,"Clear",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void add() {
        list.add("Rohan");
        list.add("Raj");
        list.add("Ankit");
        list.add("Jonny");
        list.add("Martine");
        list.add("Sachin");
        list.add("Dhoni");
        list.add("Rahul");
        list.add("Warner");
        list.add("Gayle");
        list.add("Stark");
        list.add("Guptil");
        list.add("Virat");
        list.add("Yuzi");
        list.add("Yuvraj");
        list.add("Kem");
        list.add("Hussan");
        list.add("Miraj");
        list.add("Gautam");

        adapter.addAllItems(list);
    }

    @Override
    public void onItemClick(String msg, int position) {
        Snackbar.make(binding.recyclerViewMain,"Message: "+msg+"\nPosition: "+position,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemInfoClick(String msg, int position) {
        Snackbar.make(binding.recyclerViewMain,"Info "+msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemDeleteClick(String msg, int position) {
        adapter.removeItem(position);
        Snackbar.make(binding.recyclerViewMain,"Item removed "+msg+" position "+position,Snackbar.LENGTH_SHORT).show();
    }
}