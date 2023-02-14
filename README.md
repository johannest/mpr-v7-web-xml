mpr-demo
==============

This project demonstrates usgae of Vaadin 7 with Vaadin 14 using
Multi Platform Runtime product and a web.xml.

Workflow
========

To compile the entire project, run "mvn install".

To run the application, deploy it to e.g. Tomcat and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
