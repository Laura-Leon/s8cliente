package com.example.s8cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;


public class NombreActivity extends AppCompatActivity implements View.OnClickListener, OnMessageListener{

    private EditText enombre;
    private Button baceptar;
   private TCPsingleton tcp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);

        //las refe
        enombre = findViewById(R.id.enombre);
        baceptar = findViewById(R.id.baceptar);
        baceptar.setOnClickListener(this);
        tcp = TCPsingleton.getInstance();
        tcp.setObserver(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.baceptar:


                Gson gson = new Gson();
                String username = enombre.getText().toString();

                Usuario obj = new Usuario(username);
                String json = gson.toJson(obj);
                Log.e(">>>",""+json);

                tcp.sendMessage( json);

                Intent i = new Intent( this,MainActivity.class);

                startActivity(i);
                break;

        }
    }
    public void onMessage(String msg) {

    }
}