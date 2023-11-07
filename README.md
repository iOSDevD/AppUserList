# AppUserList Example

Server code with includes docker image to run the project

## Client Side
1.	Open the project into Xcode at path iOSClient/AppClient
2.	And Run the app.
3.	It will ask for user name and password, enter the required user name and password to continue.


## Server Side
1.	Import in eclipse the project at server-code-1/AppUserList
2.	Run MVN Build with clean install.
3.	Create docker image and execute it with ` docker-compose up`
4.	This will start a web app with url  http://localhost:8080/appUserList-1.0.0-BUILD-SNAPSHOT/
5.	If there are issues with MYSQL, queries are available in /src/main/db/0.sql and 1.sql

