This repository contains a simple implementation of SQLDelight using Kotlin, Jetpack Compose, and Dagger Hilt for Dependency Injection. The application allows you to add, and delete information about people,
and the UI is built using Jetpack Compose. The MVVM architectural pattern is applied, and the data is stored in a SQL Delight database,
which is read using Kotlin Coroutines and Kotlin Flow. Dependencies are injected using a Dagger Hilt.


**To use SQLDelight in a simple implementation, you can follow these steps:**

1- Add the SQLDelight Gradle plugin to your project. \n
2- Create a .sq file with your SQL statements.
3- Create a Database class with a Schema object.
4- Provide a SqlDriver instance.
5- Create a Database instance with the SqlDriver instance.

Schema of DB:

CREATE TABLE MyDbEntity (
    id INTEGER NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    age INTEGER NOT NULL
);


getAllPersons:
SELECT *
FROM MyDbEntity;

insertPerson:
INSERT
INTO MyDbEntity
VALUES (?,?,?);

deleteById:
DELETE FROM MyDbEntity
WHERE id = :id;
