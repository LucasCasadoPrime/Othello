package com.othello.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "score")
public class Score {
    private String id;
    private String name;
    private int score;

    public Score() {
    }

    public Score(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score [id=" + id + ", name=" + name + ", score=" + score + "]";
    }
}