package com.tw;

import java.util.HashMap;
import java.util.Map;


public class Student {
    private String name;
    private String number;
    private Map<String, Integer> scores = new HashMap<>();
    private Integer total = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void addScore(String name,Integer score){
        this.scores.put(name, score);
        this.total += score;
    }


    public Map<String, Integer> getScores() {
        return scores;
    }

    public Integer getTotal() {
        return total;
    }

    public Double getAver(){
        return total * 1.0D / scores.size();
    }
}
