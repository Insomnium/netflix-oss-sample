# Build
`./gradlew build`

# Run applications

## Run eureka
Run **eureka** in default configuration. First instance will run with exceptions - never mind. There is no other instances from defaultZone yet.

## Run config-server
0. *Symmetric flow omitted*   
1. Initialize keystore and create docker image of **config-server**, then run it and follow instructions.  
```
./run_all.sh -k $some_key -s $some_secret

```

2. Now that we have an encrypted repo password and docker image, we can run **config-server** with encrypted password.  
```
docker run --name config-server --net=host -p 8890:8890 -e SPRING_PROFILES_ACTIVE=keystore -e KEY_PWD=$some_key -e KEYSTORE_PWD=$some_secret net.ins.hw.netflix/config-server:0.0.1 --spring.cloud.config.server.git.password='{cipher}$SECRET_FROM_PREVIOUS_STEP'  
```
Now we can run **config-server** in symmetric profile using long value above
3. Check that **config-server** works correctly 
```
curl -XGET http://localhost:8890/orders-api/vanaheim | jq
```
where `orders-api` is an application name and `vanaheim` is a profile.  
Successful response example:
```json
{
  "name": "orders-api",
  "profiles": [
    "vanaheim"
  ],
  "label": null,
  "version": "9d2d4585e37762a30c28953f527d9fa665ce0ba4",
  "state": null,
  "propertySources": [
    {
      "name": "https://bitbucket.org/ins-ms/infra-configs.git/orders-api-vanaheim.yml",
      "source": {
        "spring.data.mongodb.uri": "mongodb://vanaheim:27017/orders",
        "spring.data.mongodb.repositories.enabled": true
      }
    },
    {
      "name": "https://bitbucket.org/ins-ms/infra-configs.git/application.yml",
      "source": {
        "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds": 5000,
        "management.security.enabled": false
      }
    }
  ]
}
``` 