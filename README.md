# sds-identity-provider

Microservicio de identificación para el registro de usuarios y autenticación con tokens JWT


### Instalación 🔧

- Crea la base de datos **pica** y luego ejecuta el script **resources/PICA - Identity-Provider-DB.sql**

```
CREATE TABLE `pica`.`user` (
  `user_id` VARCHAR(150) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `name` VARCHAR(150) NOT NULL,
  `last_name` VARCHAR(150) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`));
```

Puede levantar directamente la instancia del servicio, mediante: 
* [Docker](https://hub.docker.com/r/dockerbasp/sds-identity-provider) - Servicio dockerizado

## Construido con 🛠️

Las herramientas utilizadas para la implementación de la solución son:

* [AWS](https://aws.amazon.com/) - Despliegue en la nube de los servicios y base de datos
* [MySQL](https://www.mysql.com/) - Gestor de base de datos
* [Java 8](https://www.java.com/es/download/help/java8.html) - Lenguaje programación
* [SpringBoot](https://spring.io/projects/spring-boot) - Framework de desarrollo
* [Gradle](https://gradle.org/) - Configuración del proyecto
* [Maven](https://maven.apache.org/) - Gestor de dependencias



## Autores ✒️


* **Grupo Ares** - *Diseño, desarrollo, implementación y documentación* - 