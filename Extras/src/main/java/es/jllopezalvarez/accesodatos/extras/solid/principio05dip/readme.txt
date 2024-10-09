DIP:
    - Las clases o módulos deben depender de abstracciones, no de implementaciones concretas.
    - Los módulos de alto nivel deben depender de abstracciones de los módulos de bajo nivel, no de las implementaciones.
    - Este principio se aplica especialmente cuando estamos trabajando con una arquitectura n-capas.
    - Pero también, en general, cuando estamos usando inyección de dependencias.

Ejemplo incorrecto:
    - La clase "SistemaNotificaciones" depende directamente de las clases "NotificadorEmail" y "NotificadorSMS".
    - Esto hace que, si queremos añadir otro tipo de notificación (push), tendríamos que modificar "SistemaNotificaciones", rompiendo SRP.

Ejemplo correcto:
    - Introducimos una abstracción (interfaz) que define el comportamiento que las clases de notificación.
    - Hacemos que "SistemaNotificaciones" dependa solo de la interfaz, no de las implementaciones específicas.
    - Es más fácil añadir nuevos tipos de notificación sin el riesgo de romper el sistema de notificaciones.
