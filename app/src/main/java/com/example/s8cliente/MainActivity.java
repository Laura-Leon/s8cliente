package com.example.s8cliente;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,OnMessageListener {
    private Button bizq;
    private Button bder;
    private Button barr;
    private Button baba;
    private Button bcolor;
    private Coordenada coor;
    private Gson gson;
    private Color color;

    private TCPsingleton tcp;
    public int x = 0,y = 0,r,g,b;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciar
        bizq = findViewById(R.id.bizq);
        bizq.setOnClickListener(this);
        bder = findViewById(R.id.bder);
        bder.setOnClickListener(this);
        barr = findViewById(R.id.barr);
        barr.setOnClickListener(this);
        baba = findViewById(R.id.baba);
        baba.setOnClickListener(this);
        bcolor = findViewById(R.id.bcolor);

        tcp = TCPsingleton.getInstance();
        tcp.setObserver(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.bcolor:
                r = (int)(Math.random()*255);
                g = (int)(Math.random()*255);
                b = (int)(Math.random()*255);
                color = new Color (r,g,b);
                gson = new Gson();
                String jsoon = gson.toJson(color);

                tcp.sendMessage(jsoon);
                break;
            case R.id.bizq:

                x=-5;

                coor = new Coordenada(x,y);
                 gson = new Gson();
                String json = gson.toJson(coor);

                tcp.sendMessage(json);

                break;
            case R.id.bder:
                x+=5;


                coor = new Coordenada(x, y);
                gson = new Gson();
                json = gson.toJson(coor);

                tcp.sendMessage(json);

                break;
            case R.id.barr:
                y-=5;
                coor = new Coordenada(x, y);
                gson = new Gson();
                json = gson.toJson(coor);

                tcp.sendMessage(json);

                break;
            case R.id.baba:
                y+=5;
                coor = new Coordenada(x, y);
                gson = new Gson();
                json = gson.toJson(coor);

                tcp.sendMessage(json);

                break;

        }
    }
   /* public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        switch (view.getId()) {
                            case R.id.bizq:

                                x -= 5;

                                Coordenada coor = new Coordenada(x, y);
                                Gson gson = new Gson();
                                String json = gson.toJson(coor);
                                tcp.sendMessage(json);
                                break;
                            case R.id.bder:
                                x += 5;
                                break;
                            case R.id.barr:
                                y -= 5;
                                break;
                            case R.id.baba:
                                y += 5;
                                break;
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        switch (view.getId()) {
                            case R.id.bizq:
                                x = 0;
                                break;
                            case R.id.bder:
                                x = 0;
                                break;
                            case R.id.barr:
                                y = 0;
                                break;
                            case R.id.baba:
                                y = 0;
                                break;
                        }
                }

        return false;
    }

    */

    @Override
    public void onMessage(String msg) {
        runOnUiThread(
                ()->{
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }
        );
    }
}