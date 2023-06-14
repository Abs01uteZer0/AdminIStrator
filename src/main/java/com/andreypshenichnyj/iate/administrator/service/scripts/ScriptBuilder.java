package com.andreypshenichnyj.iate.administrator.service.scripts;

import com.andreypshenichnyj.iate.administrator.entity.Scripts;
import com.andreypshenichnyj.iate.administrator.entity.Sub_pcs;
import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;

import java.util.List;

public class ScriptBuilder {
    //Параметры, которые будут передаваться в модель

    private Work_rooms work_room;

    private Scripts script;

    private boolean pdsh;

    private boolean w;

    private boolean uptime;


    public String buildScript(){
        StringBuilder stringBuilder = new StringBuilder();
        if (pdsh) stringBuilder.append("pdsh ");
        stringBuilder.append(script.getScript_code());
        if (w){
            stringBuilder.append(" -w");
            List<Sub_pcs> sub_pcs = work_room.getSub_pcs();
            for (int i = 0; i<sub_pcs.size(); i++){
                stringBuilder.append(" ");
                stringBuilder.append(sub_pcs.get(i).getSub_pc_ip());    //Переделать таблицу главных компьютеров, добавить поле типа xx.xx.xx.[xx-xx] или нет?
                //Либо заменить их на сокращенные значения типа 101 123 и тд, а основное значение использовать здесь.
            }
        }
        if (uptime) stringBuilder.append(" uptime");

        return stringBuilder.toString();
    }

    public Work_rooms getWork_room() {
        return work_room;
    }

    public void setWork_room(Work_rooms work_room) {
        this.work_room = work_room;
    }

    public Scripts getScript() {
        return script;
    }

    public void setScript(Scripts script) {
        this.script = script;
    }

    public boolean isPdsh() {
        return pdsh;
    }

    public void setPdsh(boolean pdsh) {
        this.pdsh = pdsh;
    }

    public boolean isW() {
        return w;
    }

    public void setW(boolean w) {
        this.w = w;
    }

    public boolean isUptime() {
        return uptime;
    }

    public void setUptime(boolean uptime) {
        this.uptime = uptime;
    }

    public ScriptBuilder(Work_rooms work_room, Scripts script, boolean pdsh, boolean w, boolean uptime) {
        this.work_room = work_room;
        this.script = script;
        this.pdsh = pdsh;
        this.w = w;
        this.uptime = uptime;
    }

    public ScriptBuilder() {
    }
}
