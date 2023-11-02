package com.example.loginvsf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class Redirect extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<Integer> arrayList = new ArrayList<>();
    ImageView back,next;
    Button btUsuario, btAdvogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);

        viewPager = findViewById(R.id.viewPager);

        back = findViewById(R.id.backButton);
        next = findViewById(R.id.nextButton);

        btUsuario = findViewById(R.id.btCadastrar);
        btAdvogado = findViewById(R.id.btEntrar);

        arrayList.add(R.drawable.img1);
        arrayList.add(R.drawable.img2);
        arrayList.add(R.drawable.img3);
        arrayList.add(R.drawable.img4);

        MyAdapter myAdapter = new MyAdapter(Redirect.this,arrayList);
        viewPager.setAdapter(myAdapter);

        btUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Redirect.this, Login.class); //tela de login do usuario
                startActivity(it);
            }
        });

        btAdvogado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Redirect.this, LoginAdvogado.class); //tela de login
                startActivity(it);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (viewPager.getCurrentItem()==0){
                    back.setVisibility(View.GONE);
                } else {
                    back.setVisibility(View.VISIBLE);
                }

                if (viewPager.getCurrentItem()==arrayList.size()-1){
                    next.setVisibility(View.GONE);
                } else {
                    next.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(getItem(-1),true);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(getItem(+1), true);

            }
        });

    }

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }

}