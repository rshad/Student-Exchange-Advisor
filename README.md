# Student-Exchange-Advisor
Category: Web App - Subject: A JavaEE application, based on showing the understanding of data integration.

### Project Story
In the subject, [Information System Engineering][1] of the third course of Computer Sicence Engineering degree, I got the oportunity to design and implement an application to demonstrate the understanding of Data Integration concept.

### Application Description
The idea of the application was free to be chosen by the students, so I really thought about a new idea, related to the students life. The idea is base on helping a student, choose the best university to go as scholarship for example. So the application recieve 3 entries; where each entry represents a univeristy or a destination. And we have to introduce 3 conditions 
value, these are:
  1. Priority of the university ranking. "high university ranking"
  2. Priority of the country life cost. "cheap country life"
  3. Priority if the country tourism ranking. "high ranked tourism"

So, depending on the introduced values for each condition, we choose the best destination for the user.

Example: Imagine that a student has 3 destination as options to choose between, to gon on a scholarship for the next year, these destinations are:
  1. The University of Granada - Computer Sicience Engineering (Granada, Spain)
  2. The University of Berling - Computer Sicience Engineering (Berlin, Germany)
  3. Harvard University - Computer Sicience Engineering (United States)

University Ranking (From the best to the worst):
  1. Harvard University.
  2. Granada University.
  3. Berlin University.

Life Cost Level (From the most expensive till the cheapest):
  1. United States
  2. Germany
  3. Spain

Tourism Level (From the most touristic site till the least one):
  1. Granada, Spain < for sure :) >
  2. New York, United States
  3.Berlin, Germany

So now imagine that the user produce the next conditions values:
  . High education university ranking, ubicated in cheap country life cost, with high toursim level.

As conclusion, the application will choose Granada University.

### Application Design:
In this application I worked with Java and JavaEE, so I extracted data from 3 different data soureces, one per each condition, as Json, Html files.

I connected the app, to Google App Engine, so we implemented a database in our account on Google App Engine and deployed the app there. This data base is used to store a logn term life valid information. "so we don't have to extract this information each time we run a query, we only have to launch a query to the database".

We used JavaEE to make the user interface and connect the app to the Internet.


### Project Structure:
In src directory, you can find all the related to the application, but not to the JavaEE part.
In web directory, you can finde all the realted to the GUI and the web connectios configuration.


[1]:http://grados.ugr.es/informatica/pages/infoacademica/guias_docentes/201314/tercero/sistemasdeinformacion/ingenieriadesistemasdeinformacion
