# Sistema de Gestión de Cotizaciones Distribuido
## Proyecto final - Curso FullStack Java

### Descripción
Este proyecto es una arquitectura de software distribuida desarrollada como solución escalable para la gestión integral de cotizaciones, clientes e inventario. El sistema abandona el modelo de monolito tradicional en favor de **10 microservicios independientes** que colaboran mediante el cliente declarativo **OpenFeign** y peticiones HTTP.

---

### 🏗️ Arquitectura del Sistema
El ecosistema implementa el patrón de **Arquitectura de Microservicios**, lo que garantiza:
- **Aislamiento de fallos:** La caída de un servicio no afecta la integridad del resto del sistema.
- **Independencia de datos:** Cada microservicio gestiona su propio esquema de base de datos (MySQL) mediante Spring Data JPA.
- **Escalabilidad:** Cada componente puede escalar de forma independiente según la carga de trabajo.

### Tecnologías Utilizadas
- **Lenguaje:** Java 17
- **Framework:** Spring Boot 3.2.5
- **Gestión de Proyectos:** Maven
- **Comunicación:** OpenFeign (resolución síncrona entre servicios)
- **Base de Datos:** MySQL (XAMPP)

---

### 🗺️ Mapa de Flujo del Ecosistema (ASCII Art)

Así es como viajan los datos entre tus 10 microservicios:

```text
[ Thunder Client ]
       |
       |  (1. POST /api/clientes)  --> [ MS-Cliente :8084 ] --> [ cliente_db ]
       |
       |  (2. POST /api/equipos)   --> [ MS-Equipo :8086 ]  --> [ equipo_db ]
       |                                      ^
       |                                      | (Feign)
       |                                      v
       |  (3. POST /api/cotizaciones) --> [ MS-Cotizador :8082 ] --> [ cotizador_db ]
       |                                      |
       |                                      | (Feign: "Dame nombre del Cliente ID 1")
       |                                      v
       |                               [ MS-Cliente :8084 ]
       |
       |  (4. POST /api/items) --> [ MS-ItemCotizador :8083 ] --> [ itemcotizador_db ]
       |                                      |
       |                                      | (Feign: "Registra ítem de cotización")
       |                                      v
       |                               [ MS-Cotizador :8082 ]
       |
       |  (5. PUT /api/stock/1) --> [ MS-Stock :8085 ] --> [ stock_db ]
       |                                      ^
       |                                      | (Feign)
       |                                      v
       |                               [ MS-ItemCotizador :8083 ]
       |
       |  (6. POST /api/facturas) --> [ MS-Facturacion :8087 ] --> [ facturacion_db ]
       |
       |  (7. POST /api/notificaciones) --> [ MS-Notificacion :8089 ] --> [ notificacion_db ]
       |
       |  (8. POST /api/usuarios/login) --> [ MS-Usuario :8090 ] --> [ usuario_db ]
       v
[ Fin del Ciclo ]
```

---

## 🚀 Guía de Despliegue

### Configuración Inicial
Asegurar el servicio de MySQL en XAMPP.

### Bases de Datos
Crear las 10 bases de datos correspondientes:
- categoria_db
- cliente_db
- equipo_db
- cotizador_db
- itemcotizador_db
- stock_db
- facturacion_db
- proveedor_db
- notificacion_db
- usuario_db

### Ejecución
Cada microservicio es un módulo independiente; deben ejecutarse las 10 clases principales (`Ms[Nombre]Application.java`) simultáneamente en sus respectivos puertos.

---

## 👥 Equipo de Desarrollo

- Agustín Vásquez Castro
- Benjamín Arellano
- Nicolás Ruiz

### Docente
Profesor Sting

### Institución
Duoc UC

Proyecto desarrollado para el curso de FullStack Java.
