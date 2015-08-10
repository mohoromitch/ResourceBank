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

The current exam bank uses a NoSQL DB, and is written with Ruby on Rails. We wanted to add account support to the future version, so using a NoSQL db would be a big no no. All students know Java, and not many know Ruby, so in my opinion, going with Java EE was the way to go. The portions of it used in this project are easy to learn, and users won't have to learn any other programming languages.

##Roadmap##
The project will be done in in phases, with the first release to be done for September.

###Phase 1: Base App RELEASE###
The resource bank will reach feature parity with the current exam bank with the addition of CMF hositng. Programs will not be implemented yet, and will be CS-only until later phases. It is essentially a static stite at this stage, although the site has the foundation for expansion.

###Phase 2: Users MILESTONE###
We will be adding user accounts to the page in this phase, which will include user registration, user verification (JavaMail tokens) and user login. 

###Phase 3: Admin Interface MILESTONE###
The admin interface will be added in this phase. Admins will be able to add and remove documents and courses. Admins will also be able to ban users.

###Phase 4: User Interaction RELASE###
In this phase, users are able to register and login, and admins are able to control the users, so it should be safe to let users help maintain the site. Users will be able to upload documents to the site. 

###Phase 5: Textbooks RELEASE###
User submitted textbook reviews will be added in this release.

###Phase 6: Expansion TBD###
The web application will be expanded to include all programs in the school. 

##Technical##
The project was made, and edited with IntelliJ Ultimate, it would probably be best to continue editing with it.

###Dependencies:###

- Java 8 JDK
- Java EE 7
- [PrettyFaces](http://www.ocpsoft.org/prettyfaces/)
- Bootstrap (included)
- JQuery (included)

####Notes:####
#####Java 8 SDK####
You should know how to download, install, and configure this. ☺

####PrettyFaces####
You will most likely need to add PrettyFaces to the project yourself, which is pretty easy. In Project Settings, add rewrite-servlet and rewrite-config-prettyfaces versions 2.0.12 through Maven.