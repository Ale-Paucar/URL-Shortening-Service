# URL Shortening Service 

**Proyecto backend construido con Spring Boot y MySQL**
Sistema RESTful para acortar URLs, administrarlas y obtener estadísticas de acceso.

**Qué hace**
Este servicio implementa la funcionalidad clásica de un URL shortener, permitiendo:

* Acortar URLs largas generando un shortCode único.
* Recuperar la URL original desde el código corto.
* Actualizar o eliminar una URL acortada.
* Registrar y consultar estadísticas, como número de accesos.

**Tecnologías Utilizadas**

* Java 17
* Spring Boot (Web, JPA, Validation)
* MySQL
* Hibernate / JPA
* Maven

**Arquitectura**

El proyecto está organizado en capas:

* controller/     → Exposición de endpoints REST
* service/        → Lógica de negocio y validaciones
* repository/     → Acceso a datos con Spring Data JPA
* entity/         → Mapeo de tablas y modelos
* dto/            → Objetos de transferencia de datos

**Funcionalidades Principales**
* Crear una URL corta
  POST /shorten
* Obtener la URL original
  GET /shorten/{shortCode}
* Actualizar la URL
  PUT /shorten/{shortCode}
* Eliminar una URL corta
  DELETE /shorten/{shortCode}
* Obtener estadísticas
  GET /shorten/{shortCode}/stats


Las estadísticas incluyen:
* total de accesos (accessCount)
* fechas de creación/actualización
* shortCode asociado
* URL original

**Cómo Ejecutar el Proyecto**

Por el momento el proecto no esta desplegado, puede ser ejecutado en un entorno local:
1. Clonar el repositorio
```git
git clone https://github.com/Ale-Paucar/URL-Shortening-Service.git
cd URL-Shortening-Service
```
2. Crear la base de datos en MySQL
```SQL
CREATE DATABASE url_shortener;
```
3. Configurar application.properties
```
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
4. Ejecutar
```
mvn spring-boot:run
```

