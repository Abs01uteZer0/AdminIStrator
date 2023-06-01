package com.andreypshenichnyj.iate.administrator.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Использует одну комманду и все
public class Connector {

    private BufferedReader inputReader;
    private BufferedReader errorReader;
    private Process p;

    private boolean b = false;

    public Connector(String command) {
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        this.errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String str = null;
        try{
            while ((str = inputReader.readLine()) != null) {
                System.out.println(str);
                if (str.contains("0 received")||str.contains("получено = 0,")) {
                    b = false;
                }
            }
            b = true;
        } catch (IOException e){
            b = false;
        } finally {
            p.destroy();
        }
    }

    public boolean getBoolean() {
        return b;
    }
}
