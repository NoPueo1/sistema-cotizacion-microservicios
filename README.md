# Sistema de Cotización Distribuido - Curso FullStack Java

## 📝 Descripción

Este proyecto consiste en una solución de software distribuida y
altamente escalable diseñada para la gestión integral de cotizaciones,
clientes, usuarios, facturación y control de inventario.

El ecosistema rompe el esquema del monolito tradicional, distribuyendo
sus responsabilidades en microservicios independientes que coexisten
mediante una arquitectura desacoplada, utilizando comunicación síncrona
con el cliente declarativo **OpenFeign** y enrutamiento centralizado.

------------------------------------------------------------------------

# 🏗️ Arquitectura del Sistema

El ecosistema implementa un patrón de arquitectura de microservicios
robusto que asegura:

-   **Punto de Entrada Único:** Todo el tráfico externo (frontend o
    herramientas de pruebas como Postman/Thunder Client) es
    administrado, validado y enrutado por un módulo pasarela central
    (**API Gateway**).
-   **Aislamiento de Fallos:** La caída o mantenimiento de un servicio
    específico no compromete la disponibilidad global del sistema.
-   **Persistencia Independiente:** Fiel al principio de
    **Database-per-Service**, cada microservicio gestiona su propio
    esquema de base de datos relacional.
-   **Configuración Legible (YAML):** Migración completa de propiedades
    hacia archivos `.yml`, mejorando la mantenibilidad.
-   **Calidad de Código (Testing):** Inclusión de pruebas unitarias con
    **JUnit 5** y **Mockito** para validar la lógica de negocio.

------------------------------------------------------------------------

# 🛠️ Tecnologías Utilizadas

-   **Lenguaje:** Java 17
-   **Framework:** Spring Boot 3.2.5
-   **Cloud:** Spring Cloud Gateway
-   **Testing:** JUnit 5 / Mockito
-   **Gestor de dependencias:** Maven
-   **Comunicación entre servicios:** Spring Cloud OpenFeign
-   **Base de datos:** MySQL (XAMPP)

------------------------------------------------------------------------

# 🔌 API Gateway y Gestión Simplificada de Puertos

## ¿Para qué sirve?

Sin Gateway, el cliente tendría que conocer el puerto de cada
microservicio:

-   Clientes → `8084`
-   Stock → `8085`
-   Facturación → `8087`

Esto aumenta la complejidad del frontend y genera problemas de seguridad
y CORS.

## Solución implementada

`ms-gateway` actúa como un **Reverse Proxy**.

Todo el tráfico entra por un único puerto (por ejemplo **8090**) y el
Gateway redirige internamente cada solicitud mediante rutas configuradas
en `application.yml`.

------------------------------------------------------------------------

# 🗺️ Estructura del Repositorio

``` text
sistema-cotizacion-microservicios/
├── .github/
├── ms-categoria/
├── ms-cliente/
├── ms-cotizador/
├── ms-equipo/
├── ms-facturacion/
├── ms-gateway/
├── ms-itemcotizador/
├── ms-notificacion/
├── ms-proveedor/
├── ms-stock/
├── ms-usuario/
└── README.md
```

------------------------------------------------------------------------

# 🚀 Guía de Despliegue

## 1. Requisitos Previos

-   Apache activo
-   MySQL activo (XAMPP)

## 2. Bases de Datos

Crear los siguientes esquemas:

-   categoria_db
-   cliente_db
-   cotizador_db
-   equipo_db
-   facturacion_db
-   itemcotizador_db
-   notificacion_db
-   proveedor_db
-   stock_db
-   usuario_db

> El Gateway no requiere base de datos.

## 3. Orden de Inicio

1.  ms-gateway
2.  ms-categoria
3.  ms-cliente
4.  ms-proveedor
5.  ms-usuario
6.  ms-stock
7.  ms-itemcotizador
8.  ms-cotizador
9.  ms-facturacion
10. ms-equipo
11. ms-notificacion

------------------------------------------------------------------------

# 👥 Equipo de Desarrollo

-   Agustín Vásquez Castro
-   Benjamín Arellano
-   Nicolás Ruiz

------------------------------------------------------------------------

# 👨‍🏫 Profesor

**Profesor Sting**

------------------------------------------------------------------------

# 🏫 Institución

**Duoc UC**

Proyecto de finalización de ciclo.
