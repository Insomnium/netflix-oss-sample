1. run `config-server`
2. run `eureka-server`
3. run `workspaces-api` - 2 instances (9090, 9091 ports)
4. run `employees-api` (on port 9092)
5. run `api-gateway` on port 9093

Check following GET requests:
```
http://localhost:8080/employees-api?workspaceId=12313123
http://localhost:9093/employees-api/employees-api?workspaceId=12313123
http://localhost:9093/core/workspace/3
```
