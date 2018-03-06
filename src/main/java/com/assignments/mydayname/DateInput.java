package com.assignments.mydayname;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DateInput {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date indate;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }




}
