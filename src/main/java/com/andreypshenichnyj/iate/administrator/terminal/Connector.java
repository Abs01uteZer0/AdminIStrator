package com.andreypshenichnyj.iate.administrator.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Использует одну комманду и все
public class Connector {

    private BufferedReader inputReader;
    private BufferedReader errorReader;
    private Process p;

    public Connector(String command) throws IOException {
        p = Runtime.getRuntime().exec(command);
        this.inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        this.errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    }

    public BufferedReader getInfo(){
        return inputReader;
    }

    public BufferedReader getErrors(){
        return errorReader;
    }
}
