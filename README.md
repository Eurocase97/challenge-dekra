# Challenge DEKRA 🚀

API RESTful desarrollada con Spring Boot que implementa un sistema de gestión de productos con autenticación JWT y arquitectura hexagonal.



## �?Características

- **Autenticación JWT**: Sistema de autenticación seguro basado en tokens
- **CRUD de Productos**: Gestión completa de productos
- **Spring Security**: Implementación de seguridad robusta
- **Arquitectura Hexagonal**: Separación clara de responsabilidades
- **AOP (Aspect-Oriented Programming)**: Logging y manejo transversal de funcionalidades
- **Specification Query**: Búsquedas dinámicas y filtros complejos
- **Base de Datos H2**: Base de datos en memoria para desarrollo
- **MapStruct**: Mapeo automático de DTOs a entidades
- **Testing**: Tests unitarios y de integración con JUnit 5 y Mockito

## 🛠 Tecnologías

| Tecnología | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.5.10 |
| Spring Security | 6.5.7 |
| JWT | 0.12.5 |
| H2 Database | 2.3.232 |
| Lombok | 1.18.32 |
| MapStruct | 1.6.2 |
| JUnit | 5.12.2 |
| Mockito | 5.17.0 |
| Maven | 3.x |

## 🏗 Arquitectura

El proyecto sigue una **arquitectura hexagonal (puertos y adaptadores)**, separando las capas de la siguiente manera:

```
src/
├── main/
�?  ├── java/
�?  �?  └── com/dekra/challenge/
�?  �?      ├── domain/                 # Capa de Dominio
�?  �?      �?  ├── product/
�?  �?      �?  �?  ├── model/         # Entidades de dominio
�?  �?      �?  �?  └── ports/         # Interfaces (puertos)
�?  �?      �?  �?      ├── in/        # Casos de uso
�?  �?      �?  �?      └── out/       # Repositorios
�?  �?      �?  └── security/
�?  �?      �?      ├── model/
�?  �?      �?      └── ports/
�?  �?      �?          ├── in/
�?  �?      �?          └── out/
�?  �?      └── infrastructure/        # Capa de Infraestructura
�?  �?          ├── product/
�?  �?          �?  ├── adapter/
�?  �?          �?  �?  ├── in/        # Controllers REST
�?  �?          �?  �?  └── out/       # Implementaciones de repositorios
�?  �?          �?  ├── mapper/        # MapStruct mappers
�?  �?          �?  └── entity/        # Entidades JPA
�?  �?          └── security/
�?  �?              ├── adapter/
�?  �?              �?  └── in/
�?  �?              �?      └── controller/
�?  �?              ├── jwt/           # Filtros y servicios JWT
�?  �?              └── config/        # Configuración de seguridad
�?  └── resources/
�?      ├── application.properties
�?      └── data.sql                   # Datos iniciales
└── test/
    └── java/
        └── com/dekra/challenge/
            ├── infrastructure/
            �?  ├── product/
            �?  �?  └── adapter/
            �?  �?      └── in/
            �?  �?          └── controller/
            �?  └── security/
            �?      └── adapter/
            �?          └── in/
            �?              └── controller/
            └── domain/
```



### application.properties

```properties
# Server Configuration
server.port=8080

# Database H2
spring.datasource.url=jdbc:h2:mem:dekradb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true



### Consola H2

Accede a la consola de H2 en: `http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:dekradb`
- **Usuario**: `sa`
- **Contraseña**: (vacío)

## 💻 Uso

### 1. Registro de Usuario

```bash
POST http://localhost:8080/api/v1/users/register
Content-Type: application/json

{
  "email": "usuario@example.com",
  "password": "password123",
}
```

**Respuesta:**
```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### 2. Login

```bash
POST http://localhost:8080/api/v1/users/login
Content-Type: application/json

{
  "email": "usuario@example.com",
  "password": "password123"
}
```

**Respuesta:**
```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

```

## 🔌 Endpoints

### Autenticación

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/api/v1/users/register` | Registro de usuario | No |
| POST | `/api/v1/users/login` | Inicio de sesión | No |

### Productos

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/api/v1/products` | Listar todos los productos | Sí |
| GET | `/api/v1/products/{id}` | Obtener producto por ID | Sí |
| POST | `/api/v1/products` | Crear nuevo producto | Sí |
| PUT | `/api/v1/products/{id}` | Actualizar producto | Sí |
| DELETE | `/api/v1/products/{id}` | Eliminar producto | Sí |
| GET | `/api/v1/products/search` | Búsqueda con filtros (Specification) | Sí |

### Ejemplos de Productos

**Crear Producto:**
```bash
POST http://localhost:8080/api/v1/products
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "Laptop Dell XPS 15",
  "price": 1299.99,
  "description": "Laptop de alto rendimiento"
}
```

**Buscar Productos (Specification Query):**
```bash
GET http://localhost:8080/api/v1/products/search?name=Laptop&minPrice=1000&maxPrice=2000
Authorization: Bearer {token}
```
