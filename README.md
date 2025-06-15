# word-counter

An application that counts the number of words in a given text.
When the application starts, it prompts the user to enter text, then displays the number of words found.
It is written in Java-17



## Features

- Prompts the user to input text and returns the number of valid words.
- Ignores non-alphabetic characters when determining what counts as a word.
- Treats multiple spaces as a single delimiter.
- Excludes configurable stopwords (e.g., "a", "and", "or").
- Configurable case sensitivity option for word matching and filtering.
- Allows the user to type `'exit'` to quit the application.
- Includes unit tests for core logic.

## Stop Words
The application ignores the following stop words to provide a more meaningful word count:

the
a
on
off

These stop words are stored in the file stopwords.txt inside the resources folder.
You can modify this file to customize which words are ignored.

## Configuration
The file path for the stopwords list is configured in application.properties:

stopwords.file.path=stopwords.txt

By default this stopwords are not case sensitive.
You can enable case-sensitivity by setting it's configuration `'case.sensitive=true'` in application.properties file.


## Example

Welcome to the WordCounter App!
Type a sentence and I'll count the words.
Type 'exit' to quit.

Mary had a little lamb
Number of words: 4

Ma*ry had a little lamb.
Number of words: 2

## Technical Details

- Language: Java 17
- Build Tool: Maven
- No external libraries or frameworks (except JUnit for testing)

## Project Structure

.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── configuration
│   │   │   │   └── WordCounterConfiguration.java
│   │   │   ├── exception
│   │   │   │   └── ConfigurationLoadException.java
│   │   │   │   └── FileLoadingException.java
│   │   │   ├── service
│   │   │   │   └── FileUtilService.java
│   │   │   │   └── IFileUtilService.java
│   │   │   │   └── WordCounterService.java
│   │   │   │   └── IWordCounterService.java
│   │   │   │   └── WordCounterFactory.java
│   │   │   └── WordCounterApp.java
│   │   └── resources
│   │       └── application.properties
│   │       └── stopwords.txt
│   └── test
│       ├── java
│       │   └── WordCounterTest.java
│       │   └── FileUtilServiceTest.java
│       │   └── WordCounterConfigurationTest.java
│       └── resources
│           └── application.properties
│           └── stopwords.txt
├── pom.xml
└── README.md

