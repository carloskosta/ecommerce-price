# Ecommerce price

## Sobre el proyecto

Esto es un proyecto de prueba, la aplicación que expone un Api para realizar consultas contra una BBDD H2 embebida en
memoria RAM.

### Construído con

* [Spring Boot](https://github.com/spring-projects/spring-boot)
    * [Swagger](https://swagger.io/)
    * [OpenAPI-generator](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-maven-plugin)
* [Docker](https://docs.docker.com/engine/)
* [docker-compose](https://github.com/docker/compose)

### Acerca del proyecto

Esta aplicación sigue el principio de API-FIRST, es decir escribir un "contrato" para consensuar los métodos de la API para los posibles
futuros consumidores.

La arquitectura de la aplicación sigue el patron N-layer dividiéndose en tres capas: controlador, servicio y repositorio.

-El **controlador** implementa la interfaz facilitada por el generador de OpenAPI.

-El **servicio** desacopla el modelo de entrada al api (DTO) con el modelo de persistencia (Entity)

-El **repositorio** utiliza Spring Data y Spring JPA para realizar las operaciones contra el repositorio.

La parte de **test** están divididos en test unitarios y test de integración end-to-end (los definidos en el enunciado 
de la prueba) [test-end-to-end](src/test/java/com/teciopgrid/ecommerceprice/EcommercePriceApplicationTests.java). 
El proyecto está cubierto con test entre un 70% (métodos) y un 85% (clases).

### Prerrequisitos

Es necesario tener instalado **git**, **docker** y **docker-compose**. Con estas tres herramientas solo es necesario descargar el proyecto y
ejecutar un scrip de bash.

### Instalación

1. Clonar repositorio
   ```sh
   git clone URL https://github.com/carloskosta/ecommerce-price.git
   cd ecommerce-price
   ```
2. Levantar aplicación y docker-compose
   ```sh
   bash startup.sh
   ```
3. Acceder desde un navegador a [esta url](http://localhost:8080/swagger-ui.html)

### Test cases

1. Test case 1![Test case 1](documentation/test1.PNG?raw=true)
2. Test case 2![Test case 2](documentation/test2.PNG?raw=true)
3. Test case 3![Test case 3](documentation/test3.PNG?raw=true)
4. Test case 4![Test case 4](documentation/test4.PNG?raw=true)
5. Test case 5![Test case 5](documentation/test5.PNG?raw=true)