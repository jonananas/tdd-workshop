TDD
=============

This repo contains the resulting code from Workshops on TDD held at different occasions.
When known katas are used the testfile should contain a link to the kata in question.

It is also a good starting point to begin a new kata, with dependencies included for
- JUnit
- AssertJ
- Mockito

# Installation
Uses maven for dependency management. After cloning, import into eclipse using Import...->Existing Maven Project. Do similar for IntelliJ or your favourite IDE.

The master branch uses java 8, use branch java6 if you need an older java.

# Suggested escalation

## 1. TDD a simple class without dependencies
[String Calculator Kata](http://osherove.com/tdd-kata-1)

## 2. TDD a class using Mockito to mock a dependency
Either [DotEnv Mockito Kata](DotEnv_Mockito_Kata.md)  
or  
[String Calculator Mockito Kata](stringcalculator-mockito-kata.md)    
There are slides from intro to Mockito and kata (In swedish) at <http://www.slideshare.net/Jonananas/mockito-kata><br>
More about Mockito can be found at <http://static.javadoc.io/org.mockito/mockito-core/2.18.3/org/mockito/Mockito.html#1>
 
## 3. TDD the database 
Goto <https://github.com/jonananas/tdd-the-database> and follow the instructions. 

# Links
* Goto <http://kata-log.rocks/> for more katas!
* Goto [eclipse-tips.md](eclipse-tips.md) to get more productive TDD:ing on eclipse

# TODO
* Provide more detailed instructions for the Mockito kata. DONE.
* Add instructions for Factory and Builder kata.
* Possibly add TDD-instructions or video for beginners.
