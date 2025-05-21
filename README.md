# 🏥 API Clínica - Gestión de Citas y Médicos

Esta API permite gestionar citas médicas y médicos. Requiere autenticación mediante JWT para acceder a los recursos protegidos.

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
### 📅 Citas
| Método | Endpoint           | Descripción              | Autenticación |
| ------ | ------------------ | ------------------------ | ------------- |
| GET    | /api/v1/citas      | Lista todas las citas    | ✅ Sí          |
| POST   | /api/v1/citas      | Crea una nueva cita      | ✅ Sí          |
| GET    | /api/v1/citas/{id} | Consulta una cita por ID | ✅ Sí          |


 📌 Ejemplo de creación de cita (POST)
```
{
"pacienteId": "12345",
"medicoId": "54321",
"fecha": "2025-05-21T10:30:00",
"motivo": "Consulta general"
}
```

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


---


