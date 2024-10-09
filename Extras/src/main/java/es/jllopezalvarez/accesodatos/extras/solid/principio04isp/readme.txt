ISP:

    - Una clase no debe verse forzada a depender de interfaces que no necesita, ni a implementar sus métodos.
    - Es mejor tener muchos interfaces pequeños que uno grande que agrupe muchas funcionalidades.

Ejemplo incorrecto:
    - La interfaz "Trabajador" define métodos para diversas responsabilidades en una empresa.
    - Pero no todos los trabajadores cumplen con todas esas responsabilidades.
    - "Empleado" se ve forzada a implementar gestionarProyectos, incluso si no tiene sentido para su rol
    - La interfaz "Trabajador" es demasiado grande y contiene métodos innecesarios.

Ejemplo correcto:
    - Dividir la interfaz en interfaces más pequeñas y específicas: "Trabajador" y "Gestor de proyectos"
    - "Empleado" solo implementa una de las interfaces, mientras que "Gerente" implementa las dos.


    