# CRMGYM - Sistema de Gestión para Gimnasios 🏋️‍♂️ (Fase 1.b - Hibernate y Ampliación de Funcionalidades)

CRMGYM evoluciona en su Fase 1.b incorporando Hibernate como framework de persistencia, permitiendo una gestión más eficiente y flexible de los datos. Se suman nuevas entidades como 'Planes' y el 'historial de planes' (resultado de los cambios de planes de los Clientes y se refactoriza la arquitectura para mejorar la legibilidad, reutilización y organización del código. Este paso representa una transición clave hacia tecnologías más robustas como Spring Boot.

---

## 📌 Características Principales

### 🤝 Gestión de Clientes

* Registro de clientes con nombre, apellido, DNI, email, teléfono, estado y plan asociado.
* Asociación de clientes con un plan vigente.
* CRUD completo con validaciones.
* Reactivación y baja lógica.

### 📈 Gestión de Planes

* Registro de planes con nombre, días y horas habilitadas, valor y notas.
* CRUD completo con baja lógica.

### 🗓️ Historial de Planes

* Registro automático del historial cuando un cliente se registra o actualiza de plan.
* Consulta de historial por cliente, por plan o por estado.

### 📊 Interfaz de Consola Mejorada

* Menú interactivo con controllers separados.
* Listado tabular y organizado mediante `TablePrinterUtil`.
* Mensajes informativos y validaciones guiadas.

---

## 🛠️ Tecnologías Utilizadas

| Tecnología    | Descripción              |
| ------------- | ------------------------ |
| Java 21       | Lenguaje de programación |
| Hibernate JPA | Persistencia ORM         |
| MySQL         | Base de datos relacional |
| Git           | Control de versiones     |
| Maven         | Gestor de dependencias   |

---

## 🚀 Estructura del Proyecto

```
src/
├── controller/         --> Controladores de menú (UI consola)
├── dto/               --> Objetos de transferencia (DTO)
├── mappers/           --> Conversión entre Entity <-> DTO
├── model/             --> Entidades JPA (Hibernate)
├── repository/        --> Interfaces y clases Repository (DAO)
├── service/           --> Servicios con lógica de negocio
├── util/              --> Utilidades (menus, tablas)
├── validations/       --> Validaciones de datos por tipo
└── Main.java           --> Punto de entrada (usa AppController)
```

---

## 🛫 Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/Joni23s/CRMGYMfase1b.git
cd CRMGYMfase1b
```

### 2. Crear la base de datos

Ubicá el script SQL en `script_initialized_db` y ejecútalo en tu servidor MySQL.

> Este script crea las tablas: `clients`, `plans`, `historical_plans` con sus relaciones y datos iniciales.

### 3. Configurar variables de entorno (opcional)

Asegurate de tener configurado el usuario y contraseña de la base:

```bash
export DB_USER=tu_usuario
export DB_PASSWORD=tu_contraseña
```

### 4. Ejecutar el proyecto

Usá tu IDE preferido o desde terminal:

```bash
javac Main.java
java Main
```

---

## 📋 Ejemplo de Uso

### Listar Clientes:

Seleccioná la opción `1. Clientes` > `1. Listar Clientes` > `3. Todos los clientes`.

### Registrar Cliente:

Elegí `1. Clientes` > `3. Agregar cliente`, seguí las instrucciones y asociá un plan.

### Ver Historial por Cliente:

Elegí `3. Historial` > `3. Historial por Cliente` e ingresá su DNI.

### Modificar Plan:

`2. Planes` > `2. Modificar Plan` > seleccioná el ID y actualizá datos.

---

## 📧 Contacto

> Si tenés preguntas, sugerencias o feedback, escribime:

* **Nombre:** Jonathan Araujo
* **GitHub:** [Joni23s](https://github.com/Joni23s)
* **Email:** [jonathanaraujo232g@gmail.com](mailto:jonathanaraujo232g@gmail.com)
* **LinkedIn:** [Jonathan Araujo](https://www.linkedin.com/in/jonathan-araujo-750634181/)

---

## 🔗 Fases Futuras del Proyecto

* **Fase 1.b (Actual):** Hibernate + Historial de Planes + Refactor MVC Consola.
* **Fase 2:** Spring Boot + API REST + Integración con pasarelas de pago.
* **Fase 3:** Interfaz gráfica en Java Swing (escritorio) para administradores.

---

🎉 **Gracias por tu interés en CRMGYM. Vamos por más!** 🚀
