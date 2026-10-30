# Evaluación práctica — Unidad II · Grupo 4A

**Materia:** Desarrollo Web  
**Modalidad:** Individual, presencial, sin internet durante el desarrollo  
**Duración:** 120 minutos (aproximadamente 85 de programación)  
**Proyecto:** Sistema de incidencias de soporte

## Objetivo

Completar una API REST para registrar incidencias de soporte técnico y cambiar su estado entre abiertas y resueltas. Se evaluarán endpoints REST, persistencia JPA, DTO, validaciones, errores HTTP y arquitectura por capas.

## Proyecto inicial

Se entrega un proyecto Spring Boot, Java 21, Maven y H2, con `Controller`, `Service`, `Repository`, entidad `Ticket`, un DTO de respuesta, una excepción 404 y un manejador de errores inicial.

Ya funciona:

`GET http://localhost:8080/api/tickets`

Responde **200 OK** con cinco registros. Campos:

- `id`: identificador autogenerado.
- `title`: asunto de la incidencia.
- `description`: descripción del problema.
- `open`: `true` para incidencia abierta y `false` para incidencia resuelta.

La base H2 es temporal: al reiniciar la aplicación se restablecen los cinco registros iniciales.

## Requerimiento 1 — Registrar incidencia (20 puntos)

Implementar `POST /api/tickets`.

Ejemplo de solicitud:

```json
{
  "title": "Pantalla sin imagen",
  "description": "El monitor del aula no muestra video"
}
```

Reglas:

1. Usar un DTO de solicitud.
2. `title` y `description` son obligatorios, no vacíos y no pueden contener solo espacios.
3. El servidor genera automáticamente el `id`.
4. Cada incidencia nueva se crea con `open = true`.
5. Guardar mediante JPA.
6. Responder **201 Created** con la incidencia creada y su `id`.
7. Responder **400 Bad Request** ante datos obligatorios inválidos.

El cliente no envía `id` ni `open` en el registro.

## Requerimiento 2 — Resolver o reabrir incidencia (35 puntos)

Implementar `PATCH /api/tickets/{id}/status`.

Ejemplo de solicitud:

```json
{
  "action": "RESOLVE"
}
```

Solo se permiten las acciones **RESOLVE** (resolver) y **REOPEN** (reabrir), exactamente en mayúsculas.

Reglas:

1. Buscar la incidencia por su `id`. Si no existe, responder **404 Not Found**.
2. Utilizar un DTO de solicitud.
3. `RESOLVE` solo es válida cuando `open = true`. Al resolver, guardar `open = false`.
4. `REOPEN` solo es válida cuando `open = false`. Al reabrir, guardar `open = true`.
5. Resolver una incidencia ya resuelta o reabrir una que ya está abierta debe responder **409 Conflict**, sin modificar su estado.
6. Si la acción está ausente, vacía o es distinta de las permitidas, responder **400 Bad Request**.
7. Para operaciones válidas, persistir el cambio mediante JPA y responder **200 OK** con la incidencia actualizada.

## Requisitos técnicos y evaluación

Respetar **Controller → Service → Repository**. La lógica de negocio debe estar en el `Service`. Utilizar DTO para las solicitudes, validaciones y manejo centralizado de excepciones. No se requiere agregar dependencias.

| Criterio | Puntos |
|---|---:|
| Registro de incidencias (POST) | 20 |
| Resolver y reabrir (PATCH) | 35 |
| DTO y validaciones | 15 |
| Manejo de errores y HTTP 400, 404, 409 | 15 |
| Arquitectura por capas | 10 |
| Git y entrega | 5 |
| **Total** | **100** |

Se calificará el comportamiento, no los nombres particulares que se elijan para clases o métodos.

## Inicio y comandos

1. Aceptar la invitación al repositorio **privado** antes de la evaluación.
2. Cuando el profesor lo indique, clonar el repositorio:

   ```bash
   git clone URL_DEL_REPOSITORIO
   ```

3. Entrar a la carpeta clonada, abrir el proyecto y **crear la rama individual antes de editar**:

   ```bash
   git switch -c examen/TU_MATRICULA
   git branch --show-current
   ```

4. Abrir la carpeta que contiene `pom.xml` con IntelliJ IDEA o VS Code y seleccionar Java 21.
5. **Con internet todavía habilitado**, ejecutar `SupportApplication` desde el IDE. También se puede arrancar desde CMD o PowerShell en la carpeta con `pom.xml`:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   En macOS o Linux:

   ```bash
   ./mvnw spring-boot:run
   ```

   Si Maven ya está instalado globalmente, también sirve:

   ```powershell
   mvn spring-boot:run
   ```

   El lanzador `mvnw.cmd` incluido prepara una distribución de Maven para el usuario si no está instalada (necesita internet la primera vez). También deben descargarse las dependencias del proyecto.

6. Antes de desconectar internet, detener la aplicación con `Ctrl+C` y **comprobar el modo offline**:

   ```powershell
   .\mvnw.cmd -o spring-boot:run
   ```

   Si el comando no puede ejecutarse, avisar al profesor antes de iniciar la evaluación. `mvnw.cmd` no es un script `.ps1`; **no se requiere cambiar `Set-ExecutionPolicy`**. También se puede usar CMD o ejecutar desde el IDE.

7. Verificar `GET http://localhost:8080/api/tickets` y confirmar los cinco registros.

## Durante el examen

- Desarrollar individualmente, sin internet, herramientas de IA ni consultar otras ramas o repositorios.
- Probar los endpoints y los códigos HTTP en Postman.
- No modificar `main`; trabajar únicamente en la rama propia.
- Se permite hacer commits locales.

## Entrega

Antes de restablecer internet, guardar los archivos y realizar el commit final:

```bash
git add .
git commit -m "Entrega examen Unidad II"
```

Cuando el profesor lo autorice, realizar push **solo de la rama individual**:

```bash
git push -u origin examen/TU_MATRICULA
```

Verificar en GitHub que la rama y el último commit estén publicados. No hacer merge ni pull request. La hora límite será indicada por el docente y se debe permanecer en el aula hasta el cierre.

**No es necesario adjuntar capturas ni subir datos de H2.**
