# Pixogram
Pixogram setup :-
Frontend 
•	Install NodeJS
•	Run this command to install angular cli = “ npm install -g @angular/cli”.
•	Run “ npm install “ to install dependencies.
•	To run project= “ng serve”

Backend
•	Install Eclipse
•	Install JDK 1.8.0 or above
•	Open Eclipse -> File -> Import > Type maven in search box -> choose Existing maven project
•	Click on next and finish.

To Run project
•	Project should run on port 8000 (By default mentioned as 8000 in application.properties file)
•	Change the file upload directory in application.properties file.
•	Right click on project -> choose Run as -> click on spring boot app

DB Details
•	Install mongodb 5.0.0
•	Create DB “ pixogram “
•	Create collection “ users”
•	Once database created create 3 more collections “allfilesdetails” , ”profilepictures” and “roles” in same DB.
•	Add below documents to roles collection -
[{
  "_id": {
    "$oid": "60f69e441ae5750f1e0eefa2"
  },
  "role": "ADMIN",
  "_class": "com.ibm.pixogram.models.Role"
},{
  "_id": {
    "$oid": "60f69e441ae5750f1e0eefa3"
  },
  "role": "USER",
  "_class": "com.ibm.pixogram.models.Role"
}]
