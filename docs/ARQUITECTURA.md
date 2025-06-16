# Arquitectura y patrón de diseño

## Arquitectura

El microservicio sigue una arquitectura multicapa bajo el stack de **Spring Boot** y **Spring WebFlux**, estructurando el código en:

- **Controller:** Exposición de endpoints REST.
- **Service:** Lógica de negocio.
- **Domain/Entity:** Modelos de datos.
- **Repository:** Acceso a datos.
- **DTO/Mapper:** Transferencia y conversión de datos.
- **Exception:** Manejo centralizado de errores.
- **Config:** Configuración y seguridad.

Se utiliza el enfoque reactivo (WebFlux) para alta escalabilidad y asincronía.

## Patrones de diseño aplicados

- **Inyección de dependencias:** Uso de `@Component`, `@Service`, `@Repository`, y constructor injection.
- **Responsabilidad Única (SRP):** Cada clase tiene un único propósito claramente definido.
- **Abierto/Cerrado (OCP):** El handler de excepciones permite agregar más sin modificar el código base.
- **Handler global de excepciones:** Mediante `@RestControllerAdvice`.
- **Externalización de configuración:** Uso de `@ConfigurationProperties`.

## Seguridad

La seguridad se basa en JWT y filtros reactivos (`JwtAuthenticationFilter`).  
Solo rutas públicas (como Swagger y login) están abiertas; el resto requiere autenticación.

## Herramientas de documentación

- **Swagger/OpenAPI:**  
  Integración mediante SpringDoc para documentación automática de la API REST.
    - Acceso vía `/swagger-ui.html` y `/v1/api-docs/**`
    - Requiere anotaciones como `@Operation`, `@Schema` en controladores y modelos.

- **Javadoc:**  
  Documentación en clases y métodos relevantes.

## Ubicación de este documento

Debes guardar este archivo como:

```
docs/ARQUITECTURA.md
```

en la raíz del proyecto.

---