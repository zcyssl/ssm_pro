package cn.zyj.bean;

public class TotalScore {

    private int score;
    private String name;

    public TotalScore() {
    }

    public TotalScore(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
