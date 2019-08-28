package com.xyz.coolstickynotes.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private String date;

    private int sticker;

    private int color;

    private int font;

    private float font_size;

    public Note(String title, String description, String date, int sticker,int color, int font, float font_size) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.sticker=sticker;
        this.color = color;
        this.font = font;
        this.font_size = font_size;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public int getSticker() {
        return sticker;
    }


    public int getColor() {
        return color;
    }

    public int getFont() {
        return font;
    }

    public float getFont_size() {
        return font_size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSticker(int sticker) {
        this.sticker = sticker;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public void setFont_size(float font_size) {
        this.font_size = font_size;
    }
}