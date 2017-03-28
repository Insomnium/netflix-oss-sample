1. run `config-server` with default `application.yml`. Config-Server will get running on port **8888**
2. run `eureka-server` with default `bootstrap.yml` so it will run on port **8989** (that is not default eureka port)
3. run `workspaces-api` - 2 instances (with `-Dserver.port=9090` and `-Dserver.port=9091` JVM args)
Consider services are working correctly. Send GET requests to `http://localhost:9090/workspaces/0000001` and `http://localhost:9091/workspaces/0000001` URIs 
4. run `employees-api` (`-Dserver.port=9093`)
Check employees api by sending GET request to `http://localhost:9093/employees/0000001`. 
Send several request and check that feign client applies round-robin for **workspaces-api** service.
5. run `api-gateway` on port 9094. Set it up to make URLs below serve and proxy requests to underlying business services:
 ```
 http://localhost:9094/workspaces-api/workspaces/0000001
 http://localhost:9094/employees-api/employees/0000001
 ```

Make business service work by applying eureka URL setting as common for all services on config-server.
