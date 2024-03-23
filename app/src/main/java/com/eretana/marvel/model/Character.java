package com.eretana.marvel.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "characters")
public class Character {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("thumbnail")
    @Ignore
    public Thumbnail thumbnail;


    public long savedAt;

}
