package com.example.morphtin.dishes.database;

public class UserDbSchema {
    public static final class UserTable {
        public static final String TABLENAME = "Users";

        public static final class Cols {
            public static final String DESCRIPTION = "description";
            public static final String IMAGE = "image";
            public static final String AGE = "age";
            public static final String NAME = "name";
        }
    }
}