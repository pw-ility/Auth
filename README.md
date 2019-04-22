### Features:

* Encryption data properties
    * Maven Command to run: mvn spring-boot:run -Dcom.vin.config.encryption.key={yourKey} 
* Centralized configuration management
* Database storage
* Rest API's
     * Key value pair : http://localhost:8888/{application}-{profile}.properties     
     * JSON : http://localhost:8888/{application}/{profile}/{label}   
 * to see all the properties: http://localhost:8888/properties
 
### Reload configuration from server (at runtime)

Spring Cloud Config has a `@RefreshScope` mechanism to allow beans to be reinitialized
on demand to fetch updated configuration values. The AppController on the client
has this annotation, so it will display the new config value once the refresh
endpoint is called.

```bash
curl -X POST 'http://localhost:8080/refresh'
```

### Health Check:
* http://localhost:8888/actuator/health

### Pending:
* Security to access properties
