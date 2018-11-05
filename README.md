# Microprofile demo using Thorntail
Cloud Native development with Thorntail and Microprofile

![logo](https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/microprofile-logo.png)

![logo](https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/microprofile13.png)

## Involved
<img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/diagram.png" alt="env" />

## URLs
* Throntail Application: http://localhost:8180/resource/info
* Prometheus: http://localhost:9090
* Grafana: http://localhost:9091
* SwaggerUI: http://localhost:9092
* Jagger: http://localhost:9093

### Microprofiles
* JAX-RS
    * http://localhost:8180/resource/info
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

### Technologies
<img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/thorntail.png" alt="thorntail" width="200px"/>
<img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/docker.jpg" alt="docker" width="200px"/>

## Samples

Grafana                    |  Jaeger                   | Swagger UI
:-------------------------:|:-------------------------:|:------------------------:
<img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/grafana.png" alt="env" width="200px"/>  |  <img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/jaeger.png" alt="env" width="200px"/> | <img src="https://raw.githubusercontent.com/christofluethi/microprofile-demo/master/gfx/swagger.png" alt="env" width="200px"/>
