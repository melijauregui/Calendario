# 📅 Calendario

Motor de calendario en **Java puro**, sin frameworks, diseñado con foco en un modelo de dominio sólido, orientado a objetos y cubierto por tests unitarios.

Permite crear **eventos** (con repeticiones diaria, semanal, mensual y anual) y **tareas** (con o sin horario), gestionar **alarmas** con distintos canales de aviso (email, notificación, sonido) y consultar qué actividades ocurren en un rango de fechas dado — toda la lógica de negocio, sin UI ni persistencia externa de por medio.

Fue desarrollado como trabajo práctico de la materia *Algoritmos y Programación 3*, pero su diseño está pensado como si fuera el core de una app de calendario real.

## ✨ Características

- **Eventos recurrentes**: repeticiones diarias, semanales, mensuales y anuales, con fin por fecha, por cantidad de ocurrencias o infinitas.
- **Tareas**: de día completo o con hora de vencimiento, con estado de completado.
- **Alarmas configurables**: múltiples avisos por actividad (email, notificación, sonido), con cálculo automático de cuáles son las próximas a disparar.
- **Consulta por rango de fechas**: obtiene todas las actividades (eventos y tareas) que ocurren entre dos fechas.
- **Duración**: modelo independiente para el rango de fechas/horas de un evento, clonable para generar instancias de sus repeticiones.

## 🧠 Diseño

El proyecto está modelado con **patrones de diseño clásicos de POO**:

- **Template Method** en `Repeticion` (clase abstracta) para las distintas frecuencias de repetición (`RepeticionDiaria`, `RepeticionSemanal`, `RepeticionMensual`, `RepeticionAnual`).
- **Strategy** en `Aviso` para desacoplar el mecanismo de notificación de una alarma (`AvisoEmail`, `AvisoNotificacion`, `AvisoConSonido`).
- **Herencia + polimorfismo** en `Actividad` → `ActividadMutable` → `Evento` / `Tarea`, unificando el manejo de alarmas y consultas por fecha para ambos tipos de actividad.

Incluye diagrama de clases: [`Diagrama de clases.pdf`](Diagrama%20de%20clases.pdf).

## 🧪 Tests

Suite de tests unitarios con **JUnit** que cubre repeticiones, duración, alarmas, tareas y eventos (incluyendo sus instancias generadas por repetición).

```bash
mvn test
```

## 🛠️ Stack

- Java 19/20
- Maven
- JUnit 4

## 👥 Autoras

- Melina Jáuregui
- Sofía Gómez Belis
