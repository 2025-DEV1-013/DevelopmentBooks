package com.bnpp.kata.developmentbooks.store;


public enum BookEnum {

    CLEAN_CODE(1, "Clean Code", "Robert Martin", 2008, 50.0, "https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_CleanCode.png"),
    THE_CLEAN_CODER(2, "The Clean Coder", "Robert Martin", 2011, 50.0, "https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_CleanCoder.png"),
    CLEAN_ARCHITECTURE(3, "Clean Architecture", "Robert Martin", 2017, 50.0, "https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_CleanArchitecture.jpeg"),
    TDD_BY_EXAMPLE(4, "Test Driven Development by Example", "Kent Beck", 2003, 50.0, "https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_TDD.jpeg"),
    LEGACY_CODE(5, "Working Effectively with Legacy Code", "Michael Feathers", 2004, 50.0, "https://github.com/stephane-genicot/katas/raw/master/images/Kata_DevelopmentBooks_Refactoring.jpeg");

    public final int id;
    public final String title;
    public final String author;
    public final int year;
    public final double price;
    public final String imageUrl;

    BookEnum(int id, String title, String author, int year, double price, String imageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}