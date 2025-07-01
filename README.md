# 🧾 GestionFactura

Microservicio para la gestión y administración de facturas, desarrollado en Spring Boot.

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Java-17-blue" alt="Java">
</p>

---

## 📋 Descripción

GestionFactura es un microservicio que permite crear, consultar, editar y anular facturas, integrándose fácilmente con otros servicios. Incluye endpoints REST y pruebas unitarias.

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Maven
- JPA/Hibernate
- H2/MySQL (según configuración)
- JUnit 5 & Mockito

## 🏗️ Estructura del proyecto

```
GestionFactura/
├── src/
│   ├── main/
│   │   ├── java/com/GestionFactura/GestionFactura/
│   │   │   ├── Controller/
│   │   │   ├── Model/
│   │   │   ├── Repository/
│   │   │   └── Service/
│   │   └── resources/
│   └── test/
└── pom.xml
```

## ⚙️ Configuración y ejecución

1. Clona el repositorio:
   ```bash
   git clone <url-del-repositorio>
   ```
2. Ingresa al directorio del proyecto:
   ```bash
   cd GestionFactura
   ```
3. Configura la base de datos en `src/main/resources/application.properties`.
4. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

## 🧪 Pruebas unitarias

Las pruebas unitarias están en `src/test/java/com/GestionFactura/GestionFactura/` e incluyen cobertura para el controlador y el servicio de facturas.

- Consulta la plantilla de registro de pruebas unitarias aquí: [Plantilla Registro Pruebas Unitarias](https://docs.google.com/spreadsheets/d/1VvFzIWowWaRAmZS9k_1y5o9UFkz-SBsD7Jb2J7BKkJo/edit?usp=sharing)


---

