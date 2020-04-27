package im.silen.vueboot.growth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.Date;

public class Growth {
    private final String    sum;
    @JsonFormat(pattern = "yyyy--MM--dd")
    @JsonSerialize()
    private final LocalDate date;
    private final int       level;
    private final Date oldDate;

    public Growth(String sum, LocalDate date, int level, Date oldDate) {
        this.sum = sum;
        this.date = date;
        this.level = level;
        this.oldDate = oldDate;
    }

    public String getSum() {
        return sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getLevel() {
        return level;
    }

    public Date getOldDate() {
        return oldDate;
    }
}
