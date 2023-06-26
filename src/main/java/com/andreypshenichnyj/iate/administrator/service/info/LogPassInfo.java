package com.andreypshenichnyj.iate.administrator.service.info;

import com.andreypshenichnyj.iate.administrator.entity.students.Students;

import java.util.List;

public class LogPassInfo {

    List<Students> list;

    private String info;

    public void doInfo() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            sb.append(i+1).append(") ").append(list.get(i).getSurname()).append(" ").append(list.get(i).getName())
                    .append(" ").append(list.get(i).getMiddle_name()).append(" : ").append(list.get(i).getLogin())
                    .append("|").append(list.get(i).getPassword()).append("\n");
        }
        System.out.println(sb.toString());

        info = sb.toString();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Students> getList() {
        return list;
    }

    public void setList(List<Students> list) {
        this.list = list;
    }

    public LogPassInfo() {
    }
}
