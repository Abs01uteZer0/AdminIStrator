package com.andreypshenichnyj.iate.administrator.service.scripts;

import com.andreypshenichnyj.iate.administrator.entity.Scripts;
import com.andreypshenichnyj.iate.administrator.entity.Sub_pcs;
import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;

import java.util.List;

public class RunningScriptBuilder {
    //Параметры, которые будут передаваться в модель

    private Work_rooms work_room;

    private Scripts script;

    private boolean pdsh;

    private boolean w;

    private boolean uptime;

    private int minutes;

    public String buildScript(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ssh -l root ").append(work_room.getMain_pc_ip()).append(" \"");
        if (pdsh) stringBuilder.append("pdsh");
        if (w){
            stringBuilder.append(" -w");
            List<Sub_pcs> sub_pcs = work_room.getSub_pcs();
            for (int i = 0; i<sub_pcs.size(); i++){
                stringBuilder.append(" ");
                stringBuilder.append(sub_pcs.get(i).getSub_pc_ip());
            }
        }
        stringBuilder.append(" ").append(script.getScript_code());
        if (uptime) stringBuilder.append(" uptime");
        stringBuilder.append("\"");
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

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public RunningScriptBuilder() {
    }

    public RunningScriptBuilder(Work_rooms work_room, Scripts script, boolean pdsh, boolean w, boolean uptime, int minutes) {
        this.work_room = work_room;
        this.script = script;
        this.pdsh = pdsh;
        this.w = w;
        this.uptime = uptime;
        this.minutes = minutes;
    }
}
