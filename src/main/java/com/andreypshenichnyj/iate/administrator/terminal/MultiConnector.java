package com.andreypshenichnyj.iate.administrator.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiConnector {

    private BufferedReader inputReader;
    private BufferedReader errorReader;
    private Runtime r;

    public MultiConnector(){
        r = Runtime.getRuntime();
    }

    public BufferedReader[] execute(String command) throws IOException {
        Process p = r.exec(command);
        inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        BufferedReader[] array = new BufferedReader[]{inputReader, errorReader};
        return array;
    }
}
