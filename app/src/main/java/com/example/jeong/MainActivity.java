package com.example.jeong;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀네비게이션 뷰
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Home fragment1;
    private List fragment2;
    private Like fragment3;
    private Profile fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home:
                        setFrag(0);
                        break;
                    case R.id.list:
                        setFrag(1);
                        break;
                    case R.id.like:
                        setFrag(2);
                        break;
                    case R.id.profile:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        fragment1 = new Home();
        fragment2 = new List();
        fragment3 = new Like();
        fragment4 = new Profile();

        setFrag(0); // 첫화면 설정
    }

    // 프래그먼트 교체가 일어나는 메서드
    private void setFrag(int n){

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        switch (n){
            case 0:
                transaction.replace(R.id.frameLayout, fragment1);
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.frameLayout, fragment2);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.frameLayout, fragment3);
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.frameLayout, fragment4);
                transaction.commit();
                break;
        }
    }
}