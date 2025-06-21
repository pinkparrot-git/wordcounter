# word-counter

**WordCounterApp** 
A Java-17 console application that counts the number of *valid* words in a given text.  
It supports words filtering and an optional case sensitivity feature via configuration.


## Features

- Counts and returns the number of valid words of a given text.
- Ignores non-alphabetic characters when determining what counts as a word.
- Treats multiple spaces as a single delimiter.
- Excludes configurable stopwords (e.g., "a", "and", "or").
- Can read input from a `.txt` file passed as a command-line argument.
- Falls back to manual console input if no file is provided or if file reading fails.
- Configurable case sensitivity option for word matching and filtering.
- Allows the user to type `'exit'` to quit the application.
- Includes unit tests for core logic.
- Friendly and formatted console UI.


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
<pre> ```
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
│   │   │   │   └── FileService.java
│   │   │   │   └── FileServiceImpl.java
│   │   │   │   └── WordCounterService.java
│   │   │   │   └── WordCounterServiceImpl.java
│   │   │   ├── ui
│   │   │   │   └── ConsoleWordCounterUI.java
│   │   │   │   └── ConsoleWordCounterUIImpl.java
│   │   │   └── WordCounterApp.java
│   │   └── resources
│   │       └── application.properties
│   │       └── mytext.txt
│   │       └── stopwords.txt
│   └── test
│       ├── java
│       │   ├── configuration
│       │   │   └── WordCounterConfigurationTest.java
│       │   └── service
│       │       └── integration
│       │       │   └── WordCounterIntegrationTest.java
│       │       └── unittest
│       │           └── FileServiceTest.java
│       │           └── WordCounterServiceTest.java
│       └── resources
│           └── application.properties
│           └── mytext.txt
│           └── stopwords.txt
├── pom.xml
└── README.md
 ``` </pre>

