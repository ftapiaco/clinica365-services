# 🏥 API Clínica - Gestión de Citas y Médicos

Esta API permite gestionar citas médicas y médicos. Requiere autenticación mediante JWT para acceder a los recursos protegidos.

---
# Autores

#### - Fernando Tapia Coronado (Dev)

---
# Mentores

#### - Youtube y muchos tutoriales de diferentes fuentes :(.

---

# Advertencia

#### Estas implementaciones son modelos muy basicos para entender un poco de la programación, aun sigo desarrollando mil gracias.


---

## 🔐 Autenticación

### 📤 Solicitud de login

`POST /auth/login`

```
Request
{
  "username": "usuario",
  "password": "1234"
}
Response
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

📌 Guarda este token para futuras solicitudes.

- 📥 Encabezado requerido en llamadas autenticadas
  (Authorization: Bearer <token>)


## 📬 Endpoints disponibles

#### Para mas informacion de los servicios ingresar a la ruta config : path(localhost:8081) + /webjars/swagger-ui/index.html
### 📅 Citas
| Método | Endpoint           | Descripción              | Autenticación |
| ------ | ------------------ | ------------------------ | ------------- |
| GET    | /api/v1/citas      | Lista todas las citas    | ✅ Sí          |
| POST   | /api/v1/citas      | Crea una nueva cita      | ✅ Sí          |
| GET    | /api/v1/citas/{id} | Consulta una cita por ID | ✅ Sí          |


 📌 Ejemplo de creación de cita (POST)
```
curl --location 'http://localhost:8081/api/v1/citas' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzQ3ODY4NTQ2LCJleHAiOjE3NDc4NzIxNDZ9.pMUW4v_tQhKUm-p6fH5U0orfiwX6sstK753jye87hHs' \
--header 'Content-Type: application/json' \
--data '{
  "idMedico": "MD123",
  "pacienteNombre": "Fernando Tapia C",
  "especialidad": "Cardiologia",
  "medico": "Luis Andrade",
  "fechaHora": "2025-05-21T22:22:08.840Z",
  "motivo": "Cardio"
}'
```

📌 Ejemplo de obtener cita por Id (POST)
```
curl --location 'http://localhost:8081/api/v1/citas/682e583fe47a477a7f2c7616' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzQ3ODY4NTQ2LCJleHAiOjE3NDc4NzIxNDZ9.pMUW4v_tQhKUm-p6fH5U0orfiwX6sstK753jye87hHs'
```

📅 Medicos

| Método | Endpoint             | Descripción               | Autenticación |
| ------ | -------------------- | ------------------------- | ------------- |
| GET    | /api/v1/medicos      | Lista todos los médicos   | ✅ Sí          |
| POST   | /api/v1/medicos      | Crea un nuevo médico      | ✅ Sí          |
| GET    | /api/v1/medicos/{id} | Consulta un médico por ID | ✅ Sí          |


```
{
  "nombre": "Dra. Mariana López",
  "especialidad": "Cardiología",
  "cmp": "123456"
}
```

### ⚠️ Errores comunes

| Código | Descripción                |
| ------ | -------------------------- |
| 401    | Token faltante o inválido  |
| 403    | Acceso denegado            |
| 404    | Recurso no encontrado      |
| 500    | Error interno del servidor |

## 🧪 Tecnologías usadas
- Spring WebFlux
- JWT (JSON Web Tokens)
- Java 17
- RX
- Maven o Gradle
- Swagger
- Lombok (Para reducir los getters y setters)

## 🧪 Patron de diseño
- Solid
---


