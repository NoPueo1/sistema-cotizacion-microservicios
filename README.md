Markdown
# Sistema de Cotización Distribuido - Curso FullStack Java

## 📝 Descripción
Este proyecto consiste en una solución de software distribuida y altamente escalable diseñada para la gestión integral de cotizaciones, clientes, usuarios, facturación y control de inventario. El ecosistema rompe el esquema del monolito tradicional, distribuyendo sus responsabilidades en **microservicios independientes** que coexisten mediante una arquitectura desacoplada, utilizando comunicación síncrona con el cliente declarativo **OpenFeign** y enrutamiento centralizado.

---

## 🏗️ Arquitectura del Sistema
El ecosistema implementa un patrón de arquitectura de microservicios robusto que asegura:
* **Punto de Entrada Único:** Todo el tráfico externo (frontend o herramientas de pruebas como Postman/Thunder Client) es administrado, validado y enrutado por un módulo pasarela central (**API Gateway**).
* **Aislamiento de Fallos:** La caída o mantenimiento de un servicio específico no compromete la disponibilidad global del sistema.
* **Persistencia Independiente:** Fiel al principio de *Database-per-Service*, cada microservicio gestiona su propio esquema de base de datos relacional.
* **Configuración Legible (YAML):** Migración completa de las propiedades heredadas desde formatos planos hacia archivos **YAML (`.yml`)**, mejorando drásticamente la mantenibilidad de rutas, filtros de red y predicados del Gateway.
* **Calidad de Código (Testing):** Inclusión de cobertura mediante **pruebas unitarias** para validar la lógica de negocio y asegurar la estabilidad de los endpoints antes del despliegue.

### 🛠️ Tecnologías Utilizadas
* **Lenguaje de Programación:** Java 17
* **Framework Base:** Spring Boot 3.2.5
* **Herramientas Cloud:** Spring Cloud Gateway & Spring Boot Dashboard Extension
* **Framework de Testing:** JUnit 5 / Mockito (Pruebas Unitarias)
* **Gestión de Dependencias:** Maven
* **Comunicación Inter-servicio:** Spring Cloud OpenFeign
* **Motor de Base de Datos:** MySQL (XAMPP)

---

## 🔌 El API Gateway y la Gestión Simplificada de Puertos

### ¿Para qué sirve realmente el Gateway en este proyecto?
En una arquitectura sin Gateway, el cliente (por ejemplo, una aplicación Angular/React o Postman) tendría que conocer y pegarle directamente al puerto específico de cada microservicio para pedir datos:
* Clientes en el `:8084`
* Stock en el `:8085`
* Facturación en el `:8087`

Esto genera problemas de seguridad, CORS y una enorme complejidad en el Frontend.

**La Solución implementada:**
El **`ms-gateway`** actúa como un **Proxy Reverso**. Se adueña de un único puerto visible hacia el exterior (por ejemplo, el puerto centralizado `8090`). El cliente externo solo interactúa con este puerto único, y el Gateway se encarga de redirigir la petición internamente al puerto del microservicio correspondiente usando patrones de rutas configurados en su archivo `application.yml`.

---

## 🗺️ Estructura del Repositorio

El espacio de trabajo unificado contiene la suite completa de desarrollo:

```text
sistema-cotizacion-microservicios/
├── .github/                 # Flujos de integración / Configuración remota
├── ms-categoria/            # Gestión de clasificaciones de ítems (Puerto Interno)
├── ms-cliente/              # Gestión y registro de clientes (Puerto Interno)
├── ms-cotizador/            # Motor principal de generación de cotizaciones (Puerto Interno)
├── ms-equipo/               # Gestión interna de equipos de trabajo (Puerto Interno)
├── ms-facturacion/          # Procesamiento de documentos tributarios y cobros (Puerto Interno)
├── ms-gateway/              # 🛡️ Pasarela Central y enrutador (PUERTO ÚNICO DE ENTRADA)
├── ms-itemcotizador/        # Detalle y desglose de productos cotizados (Puerto Interno)
├── ms-notificacion/         # Servicio transversal de mensajería/alertas (Puerto Interno)
├── ms-proveedor/            # Registro de abastecedores (Puerto Interno)
├── ms-stock/                # Control dinámico de inventarios y existencias (Puerto Interno)
├── ms-usuario/              # Autenticación, credenciales y perfiles de acceso (Puerto Interno)
└── README.md                # Documentación del sistema
🚀 Guía de Despliegue y Ejecución
1. Requisitos Previos
Asegurar que el panel de XAMPP tenga los módulos de Apache y MySQL activos.

2. Configuración de Base de Datos
Es necesario crear de forma local los siguientes esquemas en MySQL (notar que el módulo Gateway no requiere persistencia):

categoria_db

cliente_db

cotizador_db

equipo_db

facturacion_db

itemcotizador_db

notificacion_db

proveedor_db

stock_db

usuario_db

3. 🕒 Orden Crítico de Arranque
Para evitar excepciones de comunicación, fallos de enlace por FeignClients o denegaciones de servicio, los módulos deben iniciarse a través del Spring Boot Dashboard de VS Code siguiendo estrictamente este orden secuencial:

ms-gateway: Se levanta primero para dejar la pasarela perimetral lista y escuchando en su puerto.

Catálogos Base (Independientes): ms-categoria, ms-cliente, ms-proveedor y ms-usuario.

Core de Inventario: ms-stock.

Módulos Transaccionales: ms-itemcotizador, ms-cotizador y ms-facturacion.

Servicios Transversales (Soporte): ms-equipo y ms-notificacion.

👥 Equipo de Desarrollo
Agustín Vásquez Castro

Benjamín Arellano

Nicolás Ruiz

👨‍🏫 Cuerpo Docente
Profesor Sting

🏫 Institución
Duoc UC Proyecto de finalización de ciclo - Ingeniería en Informática 
