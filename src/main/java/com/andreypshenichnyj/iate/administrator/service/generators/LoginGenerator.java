package com.andreypshenichnyj.iate.administrator.service.generators;

import java.util.HashMap;
import java.util.Map;

public class LoginGenerator {

    Map<Character, String> alphabet = new HashMap<>();

    {
        alphabet.put('а', "a");
        alphabet.put('б', "b");
        alphabet.put('в', "v");
        alphabet.put('г', "g");
        alphabet.put('д', "d");
        alphabet.put('е', "e");
        alphabet.put('ё', "e");
        alphabet.put('ж', "zh");
        alphabet.put('з', "z");
        alphabet.put('и', "i");
        alphabet.put('й', "j");
        alphabet.put('к', "k");
        alphabet.put('л', "l");
        alphabet.put('м', "m");
        alphabet.put('н', "n");
        alphabet.put('о', "o");
        alphabet.put('п', "p");
        alphabet.put('р', "r");
        alphabet.put('с', "s");
        alphabet.put('т', "t");
        alphabet.put('у', "u");
        alphabet.put('ф', "f");
        alphabet.put('х', "h");
        alphabet.put('ц', "ts");
        alphabet.put('ч', "ch");
        alphabet.put('ш', "sh");
        alphabet.put('щ', "shch");
        alphabet.put('ъ', "?");         //??
        alphabet.put('ы', "y");
        alphabet.put('ь', "?");         //??
        alphabet.put('э', "e");
        alphabet.put('ю', "yu");        //??
        alphabet.put('я', "ya");        //??
    }


    public String getLogin(String name, String surname, String middle_name){
        StringBuilder login = new StringBuilder();
        char[] array = surname.toLowerCase().toCharArray();
        for (char ch: array){
            login.append(alphabet.get(ch));
        }
        login.append(alphabet.get(name.toLowerCase().charAt(0)));
        login.append(alphabet.get(middle_name.toLowerCase().charAt(0)));

        return login.toString();
    }
}
