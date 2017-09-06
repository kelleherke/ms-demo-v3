#Introduction

Introducing zuul ...

This demo retrieves pupil data. For this a pupil service is called. The retrieved pupil data including an eircode. 

To get the address associated with the eircode, the pupil service calls a second service, the address service supplying said eircode.

The pupil service needs to locate this address service. For this , I use a discovery service - netflix eureka.    

Thus we have

    -  A pupil-service instance
    -  An address-service instance
    -  A Spring Cloud Eureka Service Instance
    -  A Zuul API Gateway. All of our microservices can be routed through the gateway and have pre, response and post policies enforced on the calls.

##eurekasvr
To enable service discovery I use springboot and Netflix Eureka.

To start the service, in the eurekasvr directory, run the following command.
   
	mvn spring-boot:run
   
All registered services can be seen via [http://localhost:8761/](http://localhost:8761)

Please start this service first, as the other two services will register themselves with Eureka on startup.


##zuulsvr
To use an API gateway, I use Netflix Zuul.

To start the service, in the zuulscr directory, run the following command

    mvn spring-boot:run 
    
All zuul routes can be seen via [http://localhost:5555/routes](http://localhost:5555/routes)  

You should see a json response as follows ...

    {
     "/api/pupil/**":"pupilservice",
     "/api/address/**":"addressservice"
    }    

On startup, this service registered itself with the eureka. which can be seen via [http://localhost:8761/](http://localhost:8761/)    

##address-service
This springboot REST microservice enables the retrieval of an address by eircode.

To start the service, in the address-service directory, run the following command.
   
	mvn spring-boot:run

To test the service   

	http://localhost:5555/api/address/v1/addresses/D20AB01
   
You should see a json response as follows ...

	{
    "eircode": "D20 AB12",
    "address1": "Any House",
    "address2": "Any Street",
    "address3": "Any Town",
    "address4": "Anywhere"
	}


On startup, this service registered itself with the eureka. which can be seen via via [http://localhost:8761/](http://localhost:8761)

        

##pupil-service
This springboot REST microservice enables the retrieval of a pupil by pupilId.

To start the service
   
	mvn spring-boot:run

To test the service   

	http://localhost:5555/api/pupil/v1/pupils/f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a
   
You should see a json response as follows ...

	{
	 "pupilId": "f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a",
	 "eircode": "D20 AB01",
	 "forename": "Joe",
	 "surname": "Bloggs",
	 "address1": "Any House",
	 "address2": "Any Street",
	 "address3": "Any Town",
	 "address4": "Anywhere"
	}


On startup, this service registered itself with the eureka. which can be seen via [http://localhost:8761/](http://localhost:8761/)

Note : The pupil service calls the address service, locating it via the Eureaka service.


#Software Needed
##Java8
##Maven
I used version 3.3.3

#Recommended IDE
I used eclipse STS as my IDE, which is great for stopping/starting/debugging the services.

#Docker
In each of the 3 projects run the following command, which will execute the [Spotify docker plugin] defined in the pom.xml file
    
    mvn  clean package docker:build
    
To see all build docker images

    docker images --all  
    
Now we are going to use docker-compose to start the actual image. To start the docker image , change to the docker-compose/common directory. Issue the following docker-compose command.

    docker-compose -f docker-compose.yml up
    
This command will start the  

    - eureka server
    - zuul server
    - pupil service
    - address service
    
To sanity test that all is ok run the following commands

    http://10.0.17.43:8761/
    http://10.0.17.43:5555/routes
    http://10.0.17.43:5555/api/address/v1/addresses/D20AB01
    http://10.0.17.43:5555/api/pupil/v1/pupils/f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a
    
note : 10.0.17.43 is my docker ip address.

    
You should see a json response as follows ...

	{
	 "pupilId": "f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a",
	 "eircode": "D20 AB01",
	 "forename": "Joe",
	 "surname": "Bloggs",
	 "address1": "Any House",
	 "address2": "Any Street",
	 "address3": "Any Town",
	 "address4": "Anywhere"
	}    
        
    
To see if the service are running in docker type the following

    docker ps
    
To stop and remove the containers, networks, images issue the following command

    docker-compose -f docker-compose.yml down
    
To see docker images

    docker images
    
To remove a docker image

    docker rmi c999209b0836
    
To save a docker image

    docker save 199487e8d638 > eureka.tar    
    
    
#Git

    start up git shell
    
    git init
    
    git add --all
    
    git commit -m "first commit"
        

                 
    
            

    



