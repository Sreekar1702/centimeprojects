# centimeprojects

****Task1****

_Service1 MainApp:_ @GetMapping - /healthCheck - Returns "UP" is service is running.
                  @PostMapping - /concatResponse - Hits service2 and service2 and returns concatenated response from service2 + service3.
_Service2 helloservice:_ @GetMapping - /hello - Returns "Hello" is serv
ice is running.   
_Service3 concatstringsserver:_ @PostMapping - /concatuser - If input contains "name and surname" returns a concatened string with "name surname".
                               If any parameter is null/empty in name/surname, throws exception as "name/surmae is null"

_ConfigServer:_ Eureka server to host eureka on 8761 port.

**Problem staments and resolution for Task1:**

_Included Error Handling:_  CustomExceptionhandling while processing request and GenricExceptionhandler which triggers when input data is null/empty, validation of response from servers.

_Logs tracing:_ Used zipkin for tracking request based on traceid/servicename , distributed tracing, time taken by each request to process.

_Service Discovery:_ Created Eureka Server for service discovery using service names to communicate between two apis.
Swagger UI has been included in order to help visualize and interact with the resources of service1.

**Deployment in AWS:**
Created docker images for each service and pushed to dockerhub.
Created EC2 instances and deployed docker images by pulling them from dockerhub on the instances in VPC and added inbound rule in Security group for exposing ports of respective services.

Service1 GetMethod:(service status)

<img width="944" alt="image" src="https://user-images.githubusercontent.com/87267481/236699020-cb04afc9-3970-4c39-87b6-35da81701c3f.png">

Service PostMethod:(concat response from service2 and service3)

<img width="960" alt="image" src="https://user-images.githubusercontent.com/87267481/236699246-e9e3ee3c-bcc1-4386-928e-ad13588fddd6.png">
<img width="960" alt="image" src="https://user-images.githubusercontent.com/87267481/236699141-f938fae1-61d3-450b-adee-4904e2d161a1.png">

Zipkin:

<img width="960" alt="image" src="https://user-images.githubusercontent.com/87267481/236699258-5c2c8666-1984-4244-9b39-103827a2f935.png">

Eureka:

<img width="960" alt="image" src="https://user-images.githubusercontent.com/87267481/236699287-47b76fcc-d1ab-4fa4-a4af-c75aeea9be5f.png">

****Task2****

Service name : database - @GetMapping - /getUser/{id} - To find details by id
                          @GetMapping - /getAllUsers - return json string with nested object structure.

Use cases for task2:

1. Created H2 database on 8088 port, DB name: userdb, username=sa,password=password. 
2. By using DatabaseInitializer class, when h2 database is started it will insert data into database using StartupQueries.sql file in resources folder.
3. Implemented Custom exception handling when user details are not found/ exception while fetching details from database/ while processing.
4. Implemented @LogMethodParam Before and Afterrunning api call.
5. Used @ApiOperation for method level description.
6. Implemented a datastructure to generate below json response :

The _NestedOutput_ class is defined with id, parentId,colour and name. The _DFSGetNestedOutputHelper_ class takes a list of NestedOutput objects in its constructor and builds a map of parent-child relationships based on the parentId property. The _getHierarchy_ method recursively updates the hierarchy of NestedOutput objects using a depth-first traversal and the _updateHierarchy_ method simply calls this method for each root node in the map (i.e. nodes with a parentId of 0).
Above logic keeps updating values in _NestedOutputWithSubclasses_ which has name("name") and Sub Classes ("NestedOutputWithSubclasses"), this class is returned as response.
Ignoring jsonProperties of null objects.

@GetMapping - return nested json object:

**_[{"Name":"Warrior","Sub Classes":[{"Name":"Fighter"},{"Name":"Paladin"},{"Name":"Ranger"}]},{"Name":"Wizard","Sub Classes":[{"Name":"Mage"},{"Name":"Specialist wizard"}]},{"Name":"Priest","Sub Classes":[{"Name":"Cleric"},{"Name":"Druid"},{"Name":"Priest of specific mythos"}]},{"Name":"Rogue","Sub Classes":[{"Name":"Thief","Sub Classes":[{"Name":"Assassin"}]},{"Name":"Bard"}]}]**
_

<img width="960" alt="image" src="https://user-images.githubusercontent.com/87267481/236700234-a17436ce-2523-49c1-9872-51ba78f20814.png">
<img width="960" alt="image" src="https://user-images.githubusercontent.com/87267481/236700246-8b9862b1-a40e-4be4-999f-33b66e6bf888.png">
<img width="959" alt="image" src="https://user-images.githubusercontent.com/87267481/236700276-cac1fd9c-4b21-44b2-a002-86528fdf75d6.png">
<img width="960" alt="image" src="https://user-images.githubusercontent.com/87267481/236700281-e298ac7f-9f1a-432f-a1b5-8b540cd6ccdc.png">


@GetMapping - find by id
<img width="960" alt="image" src="https://user-images.githubusercontent.com/87267481/236700134-6cd2d970-e876-438e-a651-39699a33af79.png">

