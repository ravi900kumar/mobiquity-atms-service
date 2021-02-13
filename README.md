# mobiquity-atms-service
Create a spring boot application to expose the REST APIs with the following definition.
<br /> The application should invoke an external service to gather a list of atms:<br />
https://www.ing.nl/api/locator/atms/
# How to run Project? 
1.	Extract mobiquity-atms-service.zip (if you are downloading zip file)
2.	In eclipse select File-> Import -> search maven -> select Existing Maven project and select mobiquity-atms-service location 
3.	Find MobiquityApplication class right click on it and run as a java application 
 
# Business assumption covered by exist code:
1.	3rd party API is returned bad format, excluded the bad part at start to not stop, 
2.	If search for City not exist in the 3rd party response, return error message with response not found
3.	Other 3rd party Errors out of our scope.

# Provided APIs:
#1.	Get city atms which return all atms
http://localhost:8040/atms/all/ - GET service and Accept: application/json

#2.	Get city atms which return all city available atms
http://localhost:8040/atms/city/CITY_NAME   - GET service and Accept: application/json

# System solution:
4.	Get city name from path variable parameter.
5.	Send Rest request to external service using rest template.
6.	Remove wrong start from the response.
7.	Convert adapted response to list.
8.	Using streams API filter, returns list based on city name.
9.	Return response to user.
10.	Code coverage is 80% classes

# Technology stack
Java8,
Spring Boot,
Eclipse (Any new versions) ,
Maven 4.0
 
# An API to get a list of all the atms
http://localhost:8040/atms/all
 

# An API to filter the atms by city
http://localhost:8040/atms/city/Delft
 
