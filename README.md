# coding-test

This is a small command line application written to the specifications in the Digio base engineer application coding test.

The application takes in a log file consisting of strings seperated by a new line and parses them into logs. It then generates a report on three metrics:
- The number of unique IP addresses
- The top 3 most visited URL's
- The top 3 most active IP's

* Assumptions

- Most visited is measured by number of requests to a unique URL over the log period
- Most active is measured by number of requests made by each unique IP over the log period

* Running the application

Open a terminal at the root of the project folder and run with ./gradlew run. 
A report in the following format will be outputted to the terminal window.

********* REPORT *********

Number of unique IP addresses is: 
11

Top 3 most visited URL's are:
/docs/manage-websites/=2
/blog/2018/08/survey-your-opinion-matters/=1
/newsletter/=1

Top 3 most active IP's are:
168.41.191.40=4
50.112.00.11=3
177.71.128.21=3

* Running tests

Open a terminal at the root of the project folder and run with ./gradlew test. 
If any tests have failed, a HTML report with details of the failed tests will be generated at /coding-test/build/reports/tests/test/index.html
