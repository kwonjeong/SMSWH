package com.example.jeong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class subinfo extends AppCompatActivity {

    public ArrayList<ttoy> ttoyArrayList;

    private void initLoadDB(){
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        ttoyArrayList = mDbHelper.getTableData();

        mDbHelper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subinfo);

        ImageView img = findViewById(R.id.subimg);
        TextView textview = findViewById(R.id.subname);
        TextView quality = findViewById(R.id.subquality);
        TextView made = findViewById(R.id.submade);
        TextView detail = findViewById(R.id.subdetail);
        Button button = findViewById(R.id.register);

        Intent intent = getIntent();
        String name1 = intent.getExtras().getString("ibid");//swing

        initLoadDB();

        int [] ImageId = { R.drawable.swing, R.drawable.frandimal, R.drawable.cocomong,
                R.drawable.redcar, R.drawable.racing_car, R.drawable.camera,
                R.drawable.jazz, R.drawable.basketball, R.drawable.cut_fruit, R.drawable.turtle};


        for(int i=0;i<10;i++){
            if(name1.equals(ttoyArrayList.get(i).getTtoyimgid())) {
                img.setImageResource(ImageId[i]);
                textview.setText(ttoyArrayList.get(i).getTtoyname());
                quality.setText(ttoyArrayList.get(i).getTtoyquality());
                made.setText(ttoyArrayList.get(i).getTtoymade().toString());
                detail.setText(ttoyArrayList.get(i).getTtoydetail());

            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "신청이 완료되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }
}