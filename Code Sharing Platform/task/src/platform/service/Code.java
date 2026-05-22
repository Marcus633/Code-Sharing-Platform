package platform.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table
public class Code {

    @JsonIgnore
    @Column
    @Id
    private UUID id;
    @Column
    private String code;
    @Column
    private LocalDateTime date;

    @Column
    private long time;

    @Column
    private int views;

    @Column
    @JsonIgnore
    private long initialTime;

    @Column
    @JsonIgnore
    private int initialViews;


    public Code(String code, long time, int views, long initialTime, int initialViews){
        this.id = UUID.randomUUID();
        this.code = code;
        LocalDateTime dateNow = LocalDateTime.now();
        this.date = LocalDateTime.of(dateNow.getYear(),dateNow.getMonthValue(),dateNow.getDayOfMonth(),dateNow.getHour(),dateNow.getMinute(),dateNow.getSecond());
        this.time = time;
        this.views = views;
        this.initialTime = initialTime;
        this.initialViews = initialViews;
    }

    public Code() {
    }

    public String getCode(){
        return this.code;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public UUID getId(){
        return this.id;
    }

    public long getTime(){
        return this.time;
    }

    public int getViews(){
        return this.views;
    }

    public void setViews(int views){
        this.views = views;
    }

    public void setTime(long time){
        this.time = time;
    }
    public void setInitialViews(int views){
        this.initialViews = views;
    }

    public void setInitialTime(long time){
        this.initialTime = time;
    }

    public long getInitialTime(){
        return this.initialTime;
    }

    public long getInitialViews(){
        return this.initialViews;
    }

//    public String dateString(){
//        String DATE_FORMATTER= "yyyy/MM/dd HH:mm:ss";
//        return this.date.format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
//    }

}
