# springboot-rest-service-withDB
This project helps you to perform CURD operation on DB with Spring boot
At this point it only support PostGres database. NO unit test case has been written.

Following are the request we have tested using POSTMAN -		
1. http://localhost:8080/rest/employee/1  - GET Request		
2. http://localhost:8080/rest/employee/   - GET Request		
3. http://localhost:8080/rest/employee/    -PUT Request 		

   
   sample Json Body is :  
  	   {	
	   
           "id": 1,
          "firstname": "John", 
           "lastname": "Leach",	
          "designation": "Sr .Developer",  
           "salary": 10000 
     }
	 

 and Content-Type =application/json
 

4. http://localhost:8080/rest/employee/  - POST Request		
	
	
  sample Json Body is :
  
     {
    "firstname": "John",	
     "lastname": "Kerry", 
     "designation": "Developer",  
     "salary": 10000  
     }

5.  http://localhost:8080/rest/employee/2	- DELETE Request

This request will delete 2 nd employee record from database.

