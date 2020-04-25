package im.silen.vueboot.growth;

import java.time.LocalDate;

public class Growth {
    private final String    sum;
    private final LocalDate date;
    private final String    level;

    public Growth(String sum, LocalDate date, String level) {
        this.sum = sum;
        this.date = date;
        this.level = level;
    }

    public String getSum() {
        return sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLevel() {
        return level;
    }
}
