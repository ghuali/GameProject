# Documentación del Proyecto

## Descripción General
Este proyecto es una aplicación Android desarrollada utilizando Kotlin y Jetpack Compose para la interfaz de usuario. La funcionalidad principal incluye la gestión de juegos y plataformas, con integración de un mapa interactivo utilizando la biblioteca **osmdroid**. Los usuarios pueden agregar, editar y eliminar juegos, así como visualizar un mapa con un marcador en ubicaciones específicas.

## Características Principales

### 1. Gestión de Juegos
- **Agregar juegos:**
  Los usuarios pueden añadir nuevos juegos especificando su nombre, precio, tipo y plataforma asociada.

- **Editar juegos:**
  Permite modificar los detalles de un juego existente.

- **Eliminar juegos:**
  Los usuarios pueden eliminar juegos seleccionados de la lista.

- **Lista interactiva:**
  Los juegos se muestran en una lista interactiva que permite seleccionar un elemento para editarlo o eliminarlo.

### 2. Visualización de Mapas
- **Integración con osmdroid:**
  Se utiliza la biblioteca osmdroid para mostrar mapas interactivos con diferentes fuentes de tiles (GoogleSat).

- **Marcador personalizado:**
  Un marcador se coloca en coordenadas específicas (`28.95891251067729, -13.555487365767284`) y muestra un ícono definido por el usuario.

- **Cámara del mapa:**
  La cámara inicial del mapa está configurada para centrarse en la ubicación del marcador con un nivel de zoom de `15.0`.

### 3. Navegación
- **Navegación entre pantallas:**
  Se implementa navegación entre una vista principal (`HomeView`) y la vista de mapa (`MapView`).

- **Botón de regreso:**
  La vista del mapa incluye un botón que permite regresar a la vista principal.

## Tecnologías Utilizadas

### 1. Jetpack Compose
Jetpack Compose se utiliza para construir la interfaz de usuario declarativa, proporcionando una experiencia más fluida y moderna.

### 2. osmdroid
La biblioteca osmdroid facilita la integración de mapas y la personalización de tiles y marcadores.

### 3. Arquitectura MVVM
- **ViewModel:**
  Gestiona los estados y datos de la aplicación.
- **Compose:**
  Se comunica directamente con el ViewModel para renderizar componentes de UI reactivos.

## Estructura del Código

### 1. `HomeView`
Muestra la lista de juegos con opciones para agregar, editar y eliminar elementos. Incluye también un botón para cambiar a la vista del mapa.

### 2. `MapView`
Muestra un mapa interactivo centrado en coordenadas predefinidas, con un marcador personalizado y opción para regresar a la vista principal.

### 3. ViewModel
Gestiona los datos y eventos relacionados con juegos, tipos y plataformas. Implementa `LiveData` o `StateFlow` para actualizar la interfaz de usuario de forma reactiva.

## Dependencias
- **osmdroid:** `org.osmdroid:osmdroid-android:6.1.14`
- **Jetpack Compose:** Incluido en el conjunto de bibliotecas de Jetpack.

## Mejoras Futuras
1. **Búsqueda avanzada:**
   Filtrar juegos por tipo, plataforma o rango de precios.
2. **Mapas interactivos:**
   Agregar soporte para varios marcadores con información adicional.
3. **Sincronización en la nube:**
   Guardar datos de juegos y mapas en un backend remoto.

## Conclusión
Este proyecto demuestra cómo combinar Jetpack Compose y osmdroid para crear una aplicación moderna y funcional. Su arquitectura modular y reactiva permite una fácil extensión y mantenimiento.


## Capturas
![image](https://github.com/user-attachments/assets/fd0dd6ee-5a3d-4a23-807b-fa3eb2ce8a2a)
