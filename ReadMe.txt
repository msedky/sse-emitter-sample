* First Build the Backend Project as follow
	>> go inside directory "sse-emitter-be" and then open cmd
	>> type "mvn clean install"
		Note: In case not installing maven, Kindly install maven
		
		
* Build the Frontend Project
	>> go inside directory "sse-emitter-fe" and then open cmd
	>> type "ng build"
		make sure that folder "dist\sse-emitter-fe" has been created inside "sse-emitter-sample\sse-emitter-fe"
		
* Run the docker-compose file
	>> open cmd inside the base folder "sse-emitter-sample" then type 
		docker-compose up -d
	>> to unistall the container type docker-compose down -d
	
Note:
	>> file "sse-emitter-sample.postman_collection.json" contains required APIs that can be called through postman