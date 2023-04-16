package com.andreypshenichnyj.iate.administrator.entity.students;

public enum WorkRoom {
    _2_612("IP_1"),
    _2_610("IP_2"),
    _2_609("IP_3"),
    _2_608("IP_4"),
    _2_607("IP_5"),
    _2_606("IP_6");

    private final String mainCabPcIP;

    WorkRoom(String mainCabPcIP) {
        this.mainCabPcIP = mainCabPcIP;
    }
}
