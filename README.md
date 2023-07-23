In the project directory, you can run:

You can run in test mode ./mvnw spring-boot:run -Dspring-boot.run.profiles=test , or you can set env
variable with file name like FILE=countries.json
./mvnw spring-boot:run Runs the app using port 8080 by default.

The application exposes REST endpoint /routing/{origin}/{destination} that returns a list of border
crossings to get from origin to destination.

