Java/Spring Notes [![Build Status](https://travis-ci.org/JacobCZ/spring-notes.svg?branch=master)](https://travis-ci.org/JacobCZ/spring-notes) ![](https://reposs.herokuapp.com/?path=JacobCZ/spring-notes&style=flat)
=================

[![Join the chat at https://gitter.im/JacobCZ/spring-notes](https://badges.gitter.im/JacobCZ/spring-notes.svg)](https://gitter.im/JacobCZ/spring-notes?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
This is a relatively light-weight (in terms of java lightweight-ness :wink:) note-taking app inspired
by Google Keep. Installation and running is pretty simple thanks to Gradle and you can even grab a
pre-compiled .jar if you don't feel like doing it yourself.

#### Do you want to report a bug or propose a new feature? Do so on our [BugTracker](https://sycha-design.myjetbrains.com/youtrack/issues/SN)!

## Compiling
You can easily compile this using gradle.
 - Create a new directory a clone this repo using ```git clone git@github.com:JacobCZ/spring-notes.git```
 - Navigate to the ```spring-notes``` directory
 - Make sure ```./gradlew(.bat)``` is executable
 - Execute ```./gradlew build```
 - Navigate to ```./build/libs```
 - Run with ```java -jar name-of-file.jar```
 - You can also skip the compiling ang grap a pre-compiled copy over [here](https://github.com/JacobCZ/spring-notes/releases)

## Connecting to database
To use this app, you will need a MongoDB database.
 - Install MongoDB
 - Run the MongoDB server
 - Edit configuration in ```MongoDB.java```

## Setting up the frontend
To make the frontend functional, open the ```app.js``` file and edit the configuration on top.
