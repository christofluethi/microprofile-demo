# Microprofile demo using Thorntail
Cloud Native development with Thorntail and Microprofile

![logo](https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/microprofile-logo.png)

<img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/microprofile13.png" alt="microprofile" width="300px"/>

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

### Microprofiles
* JAX-RS
    * http://localhost:8180/resource/info
    * http://localhost:8180/resource/greeting/reliable
    * http://localhost:8180/resource/greeting/unreliable
* Config
* CDI
* HealthCheck
    * http://localhost:8180/health
    * username: `health`, password `health`
* Metrics
    * http://localhost:8180/metrics
    * http://localhost:8180/metrics/base
    * http://localhost:8180/metrics/vendor
    * http://localhost:8180/metrics/application
* OpenAPI
    * http://localhost:8180/openapi

## Samples

Grafana                    |  Jaeger                   | Swagger UI
:-------------------------:|:-------------------------:|:------------------------:
<img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/grafana.png" alt="env" width="200px"/>  |  <img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/jaeger.png" alt="env" width="200px"/> | <img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/swagger.png" alt="env" width="200px"/>
