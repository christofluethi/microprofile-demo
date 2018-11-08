# Microprofile demo using Thorntail
Cloud Native development with Thorntail and Microprofile

![logo](https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/microprofile-logo.png)

<img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/microprofile13.png" alt="microprofile" width="400px"/>

## Webservers supporting MicroProfile
* Thorntail (Former WildFly swarm)
* Payara server
* Wildfly 14 (limited to MP Health, MP Config, MP OpenTracing)
* TomEE
* Websphere Liberty
* KumuluzEE

## Involved
<img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/diagram.png" alt="env" />

## Project URLs
* Throntail Application: http://localhost:8180/resource/info
* Prometheus: http://localhost:9090
* Grafana: http://localhost:9091
* SwaggerUI: http://localhost:9092
* Jagger: http://localhost:9093

## Microprofiles
### JAX-RS
* http://localhost:8180/resource/info
* http://localhost:8180/resource/greeting/reliable
* http://localhost:8180/resource/greeting/unreliable

### CDI

### Config
Provide configurations based on multiple ConfigProviders. Resolution is made in the following order:

* Own custom providers
* System.getProperties()
* System.getEnv()
* META-INF/microprofile-config.properties 

Default value may also be provided in code
```java
    @Inject
    @ConfigProperty(name = "GREETER_BASE", defaultValue = "http://greeter:8080")
    private String greeterBase;
```

### HealthCheck
* http://localhost:8180/health

Username and Password for demo: `health`

Sample Result
```json
{  
   "outcome":"UP",
   "checks":[  
      {  
         "name":"diskSpace",
         "state":"UP",
         "data":{  
            "freePercent":"65.73%",
            "total":"109.41 GB",
            "totalBytes":117477810176,
            "free":"71.92 GB",
            "freeBytes":77223878656
         }
      },
      {  
         "name":"heapSpace",
         "state":"UP",
         "data":{  
            "freePercent":"96.79%",
            "total":"776.00 MB",
            "max":"7.84 GB",
            "totalBytes":813694976,
            "maxBytes":8413773824,
            "free":"518.43 MB",
            "freeBytes":543612560
         }
      },
      {  
         "name":"ping",
         "state":"UP"
      },
      {  
         "name":"deadlockThread",
         "state":"UP"
      }
   ]
}
```

### Metrics
* http://localhost:8180/metrics
* http://localhost:8180/metrics/base
* http://localhost:8180/metrics/vendor
* http://localhost:8180/metrics/application

Published at `/metrics` which is the expection of many tools. Export format is compatible with Prometheus.

Provide the following metric types

Type     | Description
--------:|:------------
Timed    | A timer measures both the rate that a particular piece of code is called and the distribution of its duration.
Gauge    | A gauge is an instantaneous measurement of a value. For example, we may want to measure the number of pending jobs in a queue
Counted  | A counter is just a gauge for an AtomicLong instance. You can increment or decrement its value. 
Metered  | A meter measures the rate of events over time (e.g., "requests per second"). In addition to the mean rate, meters also track 1-, 5-, and 15-minute moving averages.
Metric   | An annotation that describes the metric that is injected.

Standard metrics can be found in the [MicroProfile spec](https://github.com/eclipse/microprofile-metrics/blob/master/spec/src/main/asciidoc/required-metrics.adoc)

### OpenAPI
* http://localhost:8180/openapi

Swagger-Like description of REST-Endpoints published as yaml. Endpoints may be hidden with `@Operation(hidden = true)`


### OpenTracing
* http://localhost:8180/openapi

Vendor-neutral APIs and instrumentation for distributed tracing. 

Thorntail integrates with jaeger. However, additional jaegger dependency is needed (agent which pushes data). 

OpenTracing does not (yet) work with the MP RestClient! Make sure you wrap your restclienta as follows.

```java
@Traced
public String call() {
    Client client = ClientTracingRegistrar.configure(ClientBuilder.newBuilder()).build();
    try {
        String response = client.target("http://localhost:8080")
                .path("/simple")
                .request()
                .get(String.class);
        return "Called an external service successfully, it responded: " + response;
    } finally {
        client.close();
    }
}
```

See thorntails [distributed tracing](https://docs.thorntail.io/2.2.1.Final/#distributed-tracing_thorntail)
 
### Fault-Tolerant
> It is increasingly important to build fault tolerant micro services. Fault tolerance is about leveraging different strategies to guide the execution and result of some logic. Retry policies, bulkheads, and circuit breakers are popular concepts in this area. They dictate whether and when executions should take place, and fallbacks offer an alternative result when an execution does not complete successfully.

Type             | Description
----------------:|:------------
Timeout          | Define a duration for timeout
Retry            | Define a criteria on when to retry
Fallback         | Provide an alternative solution for a failed execution
Bulkhead         | Isolate failures in part of the system while the rest of the system can still function
CircuitBreaker   | Offer a way of fail fast by automatically failing execution to prevent the system overloading and indefinite wait or timeout by the clients

Sample Fallback with one retry if RuntimeException occures.
```java
@Retry(maxRetries = 1, delay = 100, delayUnit = ChronoUnit.MILLIS, retryOn = RuntimeException.class)
@Fallback(GreetingFallbackHandler.class)
public Greeting getGreeting() {
    ...
}
```

## Samples

Grafana                    |  Jaeger                   | Swagger UI
:-------------------------:|:-------------------------:|:------------------------:
<img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/grafana.png" alt="env" width="200px"/>  |  <img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/jaeger.png" alt="env" width="200px"/> | <img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/swagger.png" alt="env" width="200px"/>
