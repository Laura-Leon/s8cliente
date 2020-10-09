package com.example.s8cliente;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPsingleton extends Thread {


    private  static TCPsingleton unica;

    public static TCPsingleton getInstance(){
        if (unica == null){
            unica = new TCPsingleton();
            unica.start();
        }
        return unica;
    }
private TCPsingleton(){

}
private String lastMessage = "0,0";
    private Socket socket;
    private BufferedWriter writer;
    private OnMessageListener observer;

    public void  setObserver(OnMessageListener observer){
        this.observer = observer;
    }

    public void run(){

        try{
            Log.e("qqqqq","dejame conectar");
            socket = new Socket("192.168.0.6",5000);
            Log.e("dddd","conectamos");


            InputStream is = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            writer = new BufferedWriter(new OutputStreamWriter(out));
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while (true){

                Log.e("aaaa","esperandomensaje...");
                String line = reader.readLine();
                Log.e("aaaa",line);
                observer.onMessage(line);
            }

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
public void sendMessage(String msg){
        new Thread(
                ()->{
                    try {
                        writer.write(msg+"\n");
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ).start();
}
    public String getLastMessage() {
        return lastMessage;
    }
}


