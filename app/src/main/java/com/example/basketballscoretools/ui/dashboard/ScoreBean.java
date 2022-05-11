package com.example.basketballscoretools.ui.dashboard;

public class ScoreBean {
    private String date;
    private String team;
    private String score;

    @Override
    public String toString() {
        return "ListTest{" +
                "date='" + date + '\'' +
                ", team='" + team + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public ScoreBean(String date, String team, String score) {
        this.date = date;
        this.team = team;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
