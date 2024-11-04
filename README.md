# Microservicio de Registro de Usuarios

Este microservicio permite registrar nuevos usuarios con un nombre, correo electrónico, contraseña y una lista de teléfonos. Incluye validación de correo y contraseña, persistencia de datos en memoria usando H2, y un manejador de excepciones global que captura los errores en un formato de respuesta JSON.

## Tecnologías Usadas

- **Java 17**
- **Spring Boot 3**
- **Gradle**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **JUnit y Mockito para pruebas unitarias**

## Requisitos

- **Java 17** o superior
- **Gradle** 7.x o superior

## Configuración del Proyecto

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/josesanchezr/user-service.git
   cd user-service
## Ejecución de la Aplicación

1. Ejecuta la aplicación con Gradle:
   ```bash
   ./gradlew bootRun

2. Accede a la consola H2 para ver la base de datos en memoria: http://localhost:8080/h2-console

- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (deja vacío)

3. Swagger UI (si se ha habilitado en el proyecto) estará disponible en: http://localhost:8080/swagger-ui.html

## API Endpoints

- POST /api/users - Registrar un nuevo usuario
  Formato del JSON de entrada:

```
{
   "name":"Jose Rodriguez",
   "email":"jose@example.org",
   "password":"Password123!",
   "phones":[
      {
         "number":"1234567",
         "citycode":"1",
         "countrycode":"57"
      }
   ]
}
```

- Respuesta exitosa (201 Created):

```
{
  "id": "UUID del usuario",
  "name": "Jose Rodriguez",
  "email": "jose@example.org",
  "created": "2024-11-04T15:32:59",
  "modified": "2024-11-04T15:32:59",
  "last_login": "2024-11-04T15:32:59",
  "token": "JWT generado",
  "is_active": true
}
```
- Respuesta en caso de error (400 Bad Request):
```
{
  "mensaje": "El correo ya está registrado"
}
```

## Pruebas
Las pruebas unitarias están implementadas con JUnit y Mockito. Para ejecutarlas:
   ```bash
   ./gradlew bootRun
   ```

## Pruebas Principales

1. UserServiceTest - Contiene pruebas para el servicio de registro de usuario:

   - Prueba para el caso de registro exitoso.
   - Prueba para el caso de error cuando el correo ya está registrado.
2. UserControllerTest - Contiene pruebas para el controlador de usuario:

   - Prueba para el caso en que el registro es exitoso.
   - Prueba para el caso en que el correo ya existe en la base de datos y se devuelve un error.

## Script de Creación de Base de Datos

El script se encuentra en el archivo scrip_db.sql