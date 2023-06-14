package com.andreypshenichnyj.iate.administrator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "archives")
public class Archives {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private int archive_id;

    @Column(name = "label")
    private String label;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "script_id")
    private Scripts script;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_room_id")
    private Work_rooms work_room;

    @Column(name = "generated_script")
    private String generated_script;

    @Column(name = "status")
    private boolean status = true;

    public Archives() {
    }

    public Archives(int archive_id, String label, Scripts script, Work_rooms work_room, String generated_script, boolean status) {
        this.archive_id = archive_id;
        this.label = label;
        this.script = script;
        this.work_room = work_room;
        this.generated_script = generated_script;
        this.status = status;
    }

    public int getArchive_id() {
        return archive_id;
    }

    public void setArchive_id(int archive_id) {
        this.archive_id = archive_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Scripts getScript() {
        return script;
    }

    public void setScript(Scripts script) {
        this.script = script;
    }

    public Work_rooms getWork_room() {
        return work_room;
    }

    public void setWork_room(Work_rooms work_room) {
        this.work_room = work_room;
    }

    public String getGenerated_script() {
        return generated_script;
    }

    public void setGenerated_script(String generated_script) {
        this.generated_script = generated_script;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
