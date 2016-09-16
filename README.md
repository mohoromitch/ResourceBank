#CS Resource Bank#

##About##

The CS Resource Bank will be the future replacement for the current [CSCU Exam Bank](http://exams.cscu.scs.ryerson.ca). It will do everything the CSCU Exam Bank does, including:

- A non-technical admin interface for students who don't know how to interact with databases, or don't want to
- Support for programs other than just CS
- Support for CMF uploads
- Support for user accounts with a @ryerson.ca email
- Users will be able to upload documents
- Textbooks ratings and listings for programs

###Why the rewrite?###

The current exam bank uses a NoSQL DB, and is written with Ruby on Rails. We wanted to add account support to the future version, so using a NoSQL db would be a big no no. All students know Java, and not many know Ruby, so in my opinion, going with Java is the way to go. The portions of it used in this project are easy to learn, and users won't have to learn any other programming languages.

###Dependencies:###

- Java 8 JDK
- Java EE 7
- [PrettyFaces](http://www.ocpsoft.org/prettyfaces/)
- Bootstrap (included)
- JQuery (included)

####Notes:####

####PrettyFaces####
You will most likely need to add PrettyFaces to the project yourself, which is pretty easy. In Project Settings, add rewrite-servlet and rewrite-config-prettyfaces versions 2.0.12 through Maven.
