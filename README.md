# springbootrestapp  
Spring boot rest app with postgres  
AWS base url: http://springbootrestapp.us-east-2.elasticbeanstalk.com  

# Resources
1.  `Add a department`  
    Type: Post  
    URL: `/departments`   
    Request Body:  
    ```json
    {
	    "departmentName" : "Development"
    }
    ```

2. `Get all departments`  
	Type: Get  
    URL: `/departments`  

3. `Add an employee`  
    Type: Post  
    URL: `/employees/department/{dept_id}`  
    Request Body:  
    ```json
    {
    	"firstName" : "Manjunatha",
    	"lastName" : "KB",
    	"email" : "kanekal.manjunatha@gmail.com",
    	"dateOfBirth" : "1991-03-05",
    	"dateOfJoining" : "2016-05-26",
    	"dateOfExit" : null,
    	"gender" : "MALE",
    	"designation" : "SSE"
    }
    ```

4. `Get all employees`  
    Type: Get  
    URL: `employees/`  

5. `Get employee by employee code`  
    Type: Get  
    URL: `/employees/{emp_code}`  

6.  `Add addresses to an employee`  
    Type: Post  
    URL: `/employees/{emp_code}/address`  
    Request Body:  
    ```json
    { "address" : [
    	{
    	"houseNumber" : "1",
    	"addressLine2" : "Test Address Perm",
    	"street" : "Test Street Perm",
    	"city" : "Test City Perm",
    	"pincode" : "123456",
    	"addressType" : "CURRENT"
    	},
    	{
    	"houseNumber" : "2",
    	"addressLine2" : "Test Address Perm",
    	"street" : "Test Street Perm",
    	"city" : "Test City Perm",
    	"pincode" : "123456",
    	"addressType" : "PERMANENT"
    	}
    ]}
    ```

7.  `Get addresses of an employee`  
    Type: Get  
    URL: `/employees/{emp_code}/address`  

8.  `Add skillsets to an employee `  
    Type: Post  
    URL: `/employees/{emp_code}/skillsets`  
    Request Body:  
    ```json
    { "skillsets" : [
        {
        "skill" : "Core Java",
        "rating" : "8",
        "endorsed" : true
        },
        {
        "skill" : "PostgreSQL",
        "rating" : "7",
        "endorsed" : false
        },
        {
        "skill" : "Salesforce",
        "rating" : "8",
        "endorsed" : true
        }
    ]}
    ```

9.  `Get skillset of an employee`  
    Type: Get  
    URL: `/employees/{emp_code}/skillsets`  

10. `Update a skillset of an employee`  
    Type: Put  
    URL: `/employees/{emp_code}/skillsets/{skill_id}`  
    Request Body:  
    ```json
    {
        "skill" : "Core Java",
        "rating" : "9",
        "endorsed" : true
    }
    ```
    
11. `Find employees by skill and mininum rating`  
    Type: Get  
    URL: `/employees/skill/java/rating/6`  
    
# Postman collection
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/5c90d92cbc77ad7ce258)
