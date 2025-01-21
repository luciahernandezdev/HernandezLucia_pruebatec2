# Proyecto de Gestión de Ciudadanos y Turnos

Este proyecto es un ejemplo de cómo gestionar entidades de ciudadanos y turnos utilizando JPA, servlets en un entorno web y una base de datos MySQL. Las clases principales son `CiudadanoController` y `TurnoController`, que permiten realizar operaciones CRUD sobre las entidades `Ciudadano` y `Turno`, y `TurnoFormServlet` y `TurnoServlet`, que gestionan las solicitudes HTTP para la creación y listado de turnos.

## Prerrequisitos

- Java Development Kit (JDK) 8 o superior.
- Maven 3.6.0 o superior.
- Un entorno de desarrollo integrado (IDE) como IntelliJ IDEA o Eclipse.
- MySQL Server.

## Instalación

1. Clona el repositorio:
   git clone https://github.com/luciahernandezdev/HernandezLucia_pruebatec2
2. Apache Tomcat:
   Un servidor web y contenerdor servlets, diseñado para realizar aplicaciones web.
    1) Descarga el [Tomcat 10.1.34.zip](https://tomcat.apache.org/download-10.cgi)

3. Configura la base de datos en MySQL:
   CREATE DATABASE turnos_db;

4. Modifica el archivo src/main/resources/META-INF/persistence.xml con tus credenciales de MySQL si es necesario.

## Estructura del Proyecto
- src/main/java: Contiene el código fuente principal.
- src/test/java: Contiene las clases de prueba.
- src/main/resources/META-INF/persistence.xml: Archivo de configuración de JPA.
- pom.xml: Archivo de configuración de Maven.

## Configuración de JPA
El archivo persistence.xml contiene la configuración necesaria para conectar con la base de datos MySQL y manejar las entidades Ciudadano y Turno:

<!-- Configuración de la base de datos -->
```
<property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/turnos_db?serverTimezone=UTC" />
<property name="javax.persistence.jdbc.user" value="root" />
<property name="javax.persistence.jdbc.password" value="klander" />

            <!-- Mostrar sentencias SQL -->
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true" />

            <!-- Crear las tablas automáticamente -->
            <property name="hibernate.hbm2ddl.auto" value="update" />
        </properties>
    </persistence-unit>
</persistence>
```
## USO

CiudadanoController -> proporciona métodos para interactuar con la base de datos de ciudadanos.
TurnoController -> proporciona métodos para interactuar con la base de datos de turno.
Ciudadano -> La entidad ciudadano representa a un ciudadano en la base de datos y tiene una relación uno a muchos con la entidad Turno.
Turno -> La entidad Turno representa un turno en la base de datos y está relacionada con la entidad Ciudadano.