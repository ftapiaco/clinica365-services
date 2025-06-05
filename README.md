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
- Docker
- GCP (Cloud Run Deploy)
- MongoDB (No Relacional)

## 🧪 Patron de diseño
- Solid
---
# Despliegue Automático en Cloud Run con GitHub Actions

Este documento describe cómo configurar el despliegue automático de un servicio en Google Cloud Run usando GitHub Actions y Artifact Registry.

---

## Paso 1: Crear Repositorio en Artifact Registry

```bash
gcloud artifacts repositories create clinica365-repo --repository-format=docker --location=us-central1 --description="Repositorio de imágenes Docker para microservicios clinica365"
```
---
## 📦 Paso 2: Crear repositorio en Artifact Registry (Docker)
```bash
gcloud iam service-accounts create github-actions-deployer --description="Despliegue automático desde GitHub Actions" --display-name="GitHub Actions Deployer"
```
---
## Paso 3: Asignar Permisos a la Cuenta de Servicio

```bash
gcloud projects add-iam-policy-binding clinica365-461902 --member="serviceAccount:github-actions-deployer@clinica365-461902.iam.gserviceaccount.com" --role="roles/run.admin"
```
```bash
gcloud projects add-iam-policy-binding clinica365-461902 --member="serviceAccount:github-actions-deployer@clinica365-461902.iam.gserviceaccount.com" --role="roles/artifactregistry.writer"
```
```bash
gcloud projects add-iam-policy-binding clinica365-461902 --member="serviceAccount:github-actions-deployer@clinica365-461902.iam.gserviceaccount.com" --role="roles/iam.serviceAccountUser"
```
---

## Paso 4: Crear Clave JSON para Autenticación en GitHub
```bash
gcloud iam service-accounts keys create ~/Downloads/clinica365-github.json --iam-account=github-actions-deployer@clinica365-461902.iam.gserviceaccount.com
```
###### Sube el archivo clinica365-github.json como secreto en GitHub con el nombre GCP_SA_KEY.

---

## Paso 5: Crear Workflow en GitHub Actions
- Para este caso el documento está creado en la ruta '.github/workflows/deploy.yml'

---

## Paso 6: Obtener URL del Servicio
- Una vez desplegado correctamente, ejecuta el siguiente comando para ver la URL:
```bash
gcloud run services describe clinica365-services --platform managed --region us-central1 --format="value(status.url)"
```
---


