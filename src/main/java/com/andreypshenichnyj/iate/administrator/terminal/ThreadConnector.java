package com.andreypshenichnyj.iate.administrator.terminal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;

public class ThreadConnector {

    //ping -c 1 -w 1 ip
}

class Con implements Callable<Boolean>{

    private String ip;

    @Override
    public Boolean call() throws Exception {
        String str = null;
        Process p = Runtime.getRuntime().exec("ping -c 1 -w 1 "+ ip);

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        while ((str = inputReader.readLine()) != null) {
            if (str.contains("получено 0")) {
                return false;
            }
        }
        return true;
    }
}
