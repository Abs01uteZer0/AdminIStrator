package com.andreypshenichnyj.iate.administrator.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Базовый класс, работает без многопоточности
public abstract class ConnectorBaseClass {

    private BufferedReader inputReader;
    private BufferedReader errorReader;
    private Process p;

    public void executeCommand(String command){
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        this.errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    }

    //Обрабатываем входной поток и выводим информацию, T - параметр, который нам необходимо получить
    public abstract <T> T parseInfo();

    public abstract <T> T parseErrors();

}