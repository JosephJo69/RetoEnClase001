package com.example.retoenclase.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotes")
public class Quote {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String q;  // quote text
    private String a;  // author
    private String timestamp;

    public Quote(String q, String a) {
        this.q = q;
        this.a = a;
        this.timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .format(new java.util.Date());
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }
    public String getA() { return a; }
    public void setA(String a) { this.a = a; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
