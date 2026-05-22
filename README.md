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
- **Gestión de Proyectos:** Maven (gestión de dependencias y estandarización)
- **Comunicación:** OpenFeign (resolución síncrona entre servicios)
- **Base de Datos:** MySQL (XAMPP)

---

### 🚀 Guía de Despliegue
1. **Configuración Inicial:** Asegurar el servicio de MySQL en XAMPP.
2. **Bases de Datos:** Crear las 10 bases de datos correspondientes (ej. `categoria_db`, `cotizador_db`, `cliente_db`, etc.).
3. **Ejecución:** Cada microservicio es un módulo independiente; deben ejecutarse las 10 clases principales (`Ms[Nombre]Application.java`) simultáneamente en sus respectivos puertos.

---

### 🧪 Flujo de Pruebas (Ecosistema)
Para validar la integración, se recomienda seguir este flujo:
1. **Poblar Inventario:** Crear categoría y equipo asociado.
2. **Gestionar Clientes:** Registrar cliente en el `MS-Cliente`.
3. **Emitir Cotización:** Crear cotización en el `MS-Cotizador` enviando solo el `clienteId`. El sistema resolverá automáticamente el nombre del cliente mediante **OpenFeign**.
4. **Finalizar:** Gestionar stock, facturación y notificaciones para cerrar el ciclo de venta.

---

### 👥 Equipo de Desarrollo
- **Agustín Vásquez Castro**
- **Benjamín Arellano**
- **Nicolás Ruiz**

**Docente:** Profesor Sting
**Institución:** Duoc UC
*Proyecto desarrollado para el curso de FullStack Java.*
