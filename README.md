# word-counter

An application that counts the number of words in a given text.
When the application starts, it prompts the user to enter text, then displays the number of words found.
It is written in Java-17



## Features

- Prompts the user to input text and returns the number of valid words.
- Ignores non-alphabetic characters when determining what counts as a word.
- Treats multiple spaces as a single delimiter.
- Allows the user to type `'exit'` to quit the application.
- Includes unit tests for core logic.

## Example

Welcome to the WordCounter App!
Type a sentence and I'll count the words.
Type 'exit' to quit.

Mary had a little lamb
Number of words: 5

Ma*ry had a little lamb.
Number of words: 4

## Technical Details

- Language: Java 17
- Build Tool: Maven
- No external libraries or frameworks (except JUnit for testing)

## Project Structure

.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── WordCounterApp.java
│   │   │   └── WordCounterService.java
│   │   └── resources
│   └── test
│       ├── java
│       │   └── WordCounterTest.java
│       └── resources
├── pom.xml
└── README.md

