# TRADUI classic-models-challenge programming challenge

## Goal

Implement a solution for the given tasks which aims for
 * robustness & correctness
 * readability & maintainability
 * clean software design & architecture

Be prepared to discuss your implementation and software design decisions. Show us how you would solve the task when working on your day-to-day job.

Feel free to come up with own ideas & solutions. There is no plain "right" or "wrong". Instead, we want to talk to you about how you achieved your goal and about your thoughts and design idea and process.

If you feel bored by our challenge, feel free to provide creative alternative solutions and/or choose other technologies/ languages, if you think they would be a better fit.

## Tasks

### Time estimation
Between ~1 hour up to ~3.5 hours, 
depending on your previous experience & development environment.

### Part A (testing your SQL skills)

We prepared you an database example which is based on the "Classic Models" built-in database scheme of BIRT.

In the database model you will find  different tables like "CUSTOMERS", "PRODUCTS" and so on.
The easiest way to use the database scheme is to install a mysql server on your laptop/pc.
Use the classic_models_scheme.sql file to create the basement for the example.

The goal for this task is, that you show up *the top 5 customers based on their order volume for each country*.
In the next step, extend the exisiting query to identify *the ratio of each top 5 customer in relation of the total country order volume*. Calculate it or use any built-in function you know.

### Part B (testing your Java skills)

In this last step fork / clone this java project. We prepared a small java project with a single main class. The project contains a CSV file with the required data from the "Classic Models" database from part A.

First parse the required data from the csv file. Your goal is exactly the same task as in part A, only programmed in java. Print out the result to console.

Test your results in the included test class.

## Getting started

Fork or directly clone the repository

    git clone https://github.com/TRADUI-de/classic-models-challenge

or if you’ve forked the repository then

    git clone git@github.com:YOURNAME/classic-models-challenge

## Building and running

The project scaffolds provides a Maven pom.xml as starting point. You should be able to start with any IDE or text editor you are convenient with.

After installing Maven 3.x you should be able to

Run the main class de.tradui.challenge.classicmodels.ClassicModelsApp::

    mvn clean compile exec:java
	
Build & test your project::

    mvn clean test.
	
Or use your IDE functionality::

    to run & debug you program.

## Submitting your results

Ideally you provide your solutions as Git repository with appropriate commits and descriptions. If you have a GitHub account (or create a new one), please feel free to publish your solution there.

If you have any questions, don't hesitate to contact us via email.
