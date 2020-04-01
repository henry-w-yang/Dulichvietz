package com.viettravel.dulichviet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Xemtatca extends AppCompatActivity {
    RecyclerView recyclerviewAll;
    SQLiteDatabase database;
    public String DATABASE_NAME = "dulichviets.sqlite";
    List<DiaDanh_Modles> listdiadanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemtatca);
        recyclerviewAll = findViewById(R.id.recycler_xemtatca);

        ListAllDiadanh();
    }

    public void ListAllDiadanh (){

        listdiadanh = new ArrayList<>();
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT id,ten,mota,hinh2 FROM chitietdiadanh", null);
        while (cursor.moveToNext()) {
            listdiadanh.add(new DiaDanh_Modles(
                    cursor.getInt(0),
                    cursor.getBlob(3),
                    cursor.getString(1),
                    cursor.getString(2)
            ));
            Log.e("Chitiet", String.valueOf(cursor.getInt(0)));
        }
        database.close();
        cursor.close();

        LinearLayoutManager _layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerviewAll.setLayoutManager(_layoutManager);
        recyclerviewAll.setHasFixedSize(true);// Fix về cùng kích thước giúp trượt mượt hơn
        recyclerviewAll.setAdapter(new RecyclerAdapterXemTatCa(this,listdiadanh));
    }

}
