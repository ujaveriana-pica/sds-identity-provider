# sds-identity-provider

Microservicio de identificación para el registro de usuarios y autenticación con tokens JWT


### Instalación 🔧

- Crea la base de datos **sds-identity-provider** y luego ejecuta el siguiente script:

```
CREATE TABLE `sds-identity-provider`.`user` (
   `user_id` VARCHAR(150) NOT NULL,
   `username` VARCHAR(45) NOT NULL,
   `name` VARCHAR(150) NOT NULL,
   `last_name` VARCHAR(150) NOT NULL,
   `password` VARCHAR(150) NOT NULL,
   `email` VARCHAR(150) NOT NULL,
   `status` VARCHAR(45) NOT NULL,
   `rol` VARCHAR(45) NOT NULL,
   PRIMARY KEY (`user_id`),
   UNIQUE (username));
```

Puede levantar directamente la instancia del servicio, mediante: 
* [Docker](https://hub.docker.com/r/dockerbasp/sds-identity-provider) - Imagen del servicio.

## Construido con 🛠️

Las herramientas utilizadas para la implementación de la solución son:

* [AWS](https://aws.amazon.com/) - Despliegue en la nube de los servicios y base de datos
* [MySQL](https://www.mysql.com/) - Gestor de base de datos
* [Kafka](https://kafka.apache.org/) - Broker de eventos
* [Java](https://openjdk.java.net/) - Lenguaje programación
* [SpringBoot](https://spring.io/projects/spring-boot) - Framework de desarrollo
* [Gradle](https://gradle.org/) - Configuración del proyecto
* [Maven](https://maven.apache.org/) - Gestor de dependencias

## Definición 📋

Para ver la definición del servicio, ingresar a:
* [Swagger](https://github.com/ujaveriana-pica/sds-identity-provider/definition/sds-identity-provider-swagger.yaml) - Definición del contrato.
* [Postman](https://github.com/ujaveriana-pica/sds-identity-provider/definition/sds-identity-provider.postman_collection.json) - Colección de API definidas.
* [MySQL](https://github.com/ujaveriana-pica/sds-identity-provider/definition/sds-identity-provider-user.sql) - Script base de datos definido


## Autores ✒️


* **Grupo Ares** - *Diseño, desarrollo, implementación y documentación* - 