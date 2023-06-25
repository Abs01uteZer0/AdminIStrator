package com.andreypshenichnyj.iate.administrator.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Использует одну комманду и все
public class Connector {

    private BufferedReader inputReader;
    private BufferedReader errorReader;
    private Process p;

    public Connector(String command) {
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        this.errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    }

    public BufferedReader getInputReader() {
        return inputReader;
    }

    public BufferedReader getErrorReader() {
        return errorReader;
    }
}
