#  RoomCronoApp Android JetpackCompose: Bloque 13 JM

A new Android project Jetpack Compose.

## Getting Started

This application contains the following knowledge:

- Jetpack Compose
  
- Coroutines

- Database local with Room

- Clean Arquitecture

## Teoria

- Room :  Room es una capa de abstracción sobre SQLite que forma parte de las Android Jetpack Components. Facilita el trabajo con bases de datos de manera más sencilla, eficiente
  y con menos código repetitivo en comparación con el uso directo de SQLite.

- Características principales de Room:

- Anotaciones simplificadas: Permite definir entidades, consultas y relaciones con anotaciones como @Entity, @Dao (Data Access Object) y @Database.

- Verificación en tiempo de compilación: Room valida las consultas SQL en tiempo de compilación, evitando errores en tiempo de ejecución.

- Integración con LiveData y RxJava: Soporta observables para actualizaciones automáticas de la UI cuando cambian los datos.

- Manejo de hilos (threading): Recomienda ejecutar operaciones de base de datos en segundo plano (por ejemplo, usando Coroutines o RxJava).

- Este es el camino que seguiremos:
- Interface -> Repositorio -> Viewmodel -> View
- Data Access Observer, es el observador de acceso de datos.

- Clase Abstracta: Es una especie de super class, que no puede ser instanciada,pero sus metódos si.

### The application looks like this:
![WhatsApp Image 2025-06-23 at 12 42 08](https://github.com/user-attachments/assets/f25a0f16-df08-4d7c-931c-dd6315a56066)
![WhatsApp Image 2025-06-23 at 12 42 08 (2)](https://github.com/user-attachments/assets/5e088e70-e377-4e21-92ae-cc6beb9640dd)
![WhatsApp Image 2025-06-23 at 12 42 08 (1)](https://github.com/user-attachments/assets/a93a74a6-5299-48c5-8949-b25371b1f8ae)



