package im.silen.restclient.growth;

import java.time.LocalDate;

public class Growth {
    private String    sum;
    private LocalDate date;
    private int       level;

    public Growth() {
    }

    public Growth(String sum, LocalDate date, int level) {
        this.sum = sum;
        this.date = date;
        this.level = level;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Growth{" +
                "sum='" + sum + '\'' +
                ", date=" + date +
                ", level=" + level +
                '}';
    }
}
