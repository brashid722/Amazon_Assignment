# Amazon-Assignment
## Test Automation Framework
Created by Bushra Rashid, SDET

**Project Overview**
Test Automation Framework - Reusable template for Java/Selenium frameworks.

**Tech Stack**  
Java 11  
Maven  
TestNG  
Selenium  
Extent Report

**Key Files Description**  

pom.xml - this file contains all the dependencies that are being used by the application (and basically everything for the building/testing/deploying automation with Java)  

testng.xml - this file contains all the test cases and their respective classes name to run.  

## Getting started

**Prerequisites**  
Java 11 SDK (https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)  
Apache Maven (https://maven.apache.org/download.cgi)  

**Run tests locally**  

Right click the feature file and select "Run" or "Debug" to start the test.  

**Clone the repository & run the project**  

Launch Terminal.app and navigate (using cd command) to directory, where you'd like to store your copy of the source code

Use the command below to clone the repository to your local directory

git clone  
Navigate (using cd command) to the root directory of the project (basically - it's where the pom.xml file is located)  

Run the command below in order to run the tests  

mvn clean test  

**Defining the browser**  

By default, the project will default to ChromeLocal (running a local Chrome instance) if no browser is specified.  

To update or to run on different browser, update the browser name in **Config.properties** file.  

**Reporting**  

Default reporting provided by the framwork is TestNG report. Implemented Extent Report framework, taking screenshot for failed test cases only.

## Selenium Grid Setup Instruction
Download the Selenium Server: https://www.selenium.dev/downloads/  

Download Browser drivers and place in the same path where Selenium server is located  

**Start the Hub**  
which eventually Starts Router, Distributor, Session Map , New Session Queue, Event Bus  

'java -jar [SeleniumJarname] hub'  

**Start the Node in Same Machine where Hub is running**  

'java -jar [SeleniumJarname] node --detect-drivers true'  

**Start the Node in different Physical Machine**  

'java -jar [SeleniumJarname] node --detect-drivers true -- publish-events tcp://[ipaddressofhub] --subscribe-events tcp:// [ipaddressofhub]'  
