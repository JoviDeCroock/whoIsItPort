package com.example.jovi.whoisit.domain;

/**
 * Created by jovi on 1/24/2017.
 */

public class Person
{
    public int picture;
    public String name;
    public int age;
    public String favoLanguage;
    public String hateCourse;
    public String hobby;

    public Person(int age, String name, int picture, String favoLanguage, String hateCourse, String hobby)
    {
        this.age = age;
        this.name = name;
        this.hobby = hobby;
        this.hateCourse = hateCourse;
        this.favoLanguage = favoLanguage;
        this.picture = picture;
    }
}
