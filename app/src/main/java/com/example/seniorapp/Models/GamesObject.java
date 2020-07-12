package com.example.seniorapp.Models;


import com.example.seniorapp.Utils.LevelGame;
import com.example.seniorapp.Utils.NameGame;
import com.example.seniorapp.Utils.StatusGame;

import java.io.Serializable;
import java.util.Date;
import java.util.jar.Attributes;

public class GamesObject implements Serializable {

    private Integer id;
    private StatusGame status;
    private String time;
    private String date;
    private Integer idPatients;
    private LevelGame level;
    private NameGame name;

    public GamesObject() { }

    public GamesObject(StatusGame status, String time, String date, Integer idPatients, LevelGame level, NameGame name) {
        this.status = status;
        this.time = time;
        this.date = date;
        this.idPatients = idPatients;
        this.level = level;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusGame getStatus() {
        return status;
    }

    public void setStatus(StatusGame status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getIdPatients() {
        return idPatients;
    }

    public void setIdPatients(Integer idPatients) {
        this.idPatients = idPatients;
    }

    public LevelGame getLevel() {
        return level;
    }

    public void setLevel(LevelGame level) {
        this.level = level;
    }

    public NameGame getName() {
        return name;
    }

    public void setName(NameGame name) {
        this.name = name;
    }
}
