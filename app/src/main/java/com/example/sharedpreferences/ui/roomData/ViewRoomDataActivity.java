package com.example.sharedpreferences.ui.roomData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.sharedpreferences.R;
import com.example.sharedpreferences.adapter.RecyclerViewAdapter;
import com.example.sharedpreferences.data.db.AppDatabase;
import com.example.sharedpreferences.data.model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

import static com.example.sharedpreferences.data.db.MyApp.db;

public class ViewRoomDataActivity extends AppCompatActivity {

    RecyclerView myRecyclerview;
    RecyclerViewAdapter recycleAdapter;
    List<Mahasiswa> listMahasiswas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room_data);
        myRecyclerview = findViewById(R.id.myRecyclerview);

        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"mahasiswa").allowMainThreadQueries().build();
        listMahasiswas = db.userDao().getAll();

        //just checking data from db
        for (int i = 0 ;i <listMahasiswas.size();i++){
            Log.e("Aplikasi",listMahasiswas.get(i).getAlamat()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getKejuruan()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getNama()+i);
            Log.e("Aplikasi",listMahasiswas.get(i).getNim()+i);
        }
    }
    private void initRecyclerView() {
        myRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerview.setLayoutManager(llm);
        recycleAdapter =new RecyclerViewAdapter(this,listMahasiswas);
    }
    private void setAdapter() {
        myRecyclerview.setAdapter(recycleAdapter);
    }
}