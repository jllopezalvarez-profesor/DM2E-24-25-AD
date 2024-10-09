OCP:
    - Open for extension, closed for modification.
    - Una clase debe estar abierta a la extensión: debe poder ampliarse sin necesidad de alterar su código fuente.
    - Una clase debe estar cerrada a la modificación: una vez que una clase/interfaz está terminada, probada y en uso, no debe modificarse.
    - Si hay que añadir funcionalidad, debería extenderse (herencia), o hacer nuevas implementaciones.

Ejemplo incorrecto:
    - La clase CalculadoraAreas tiene un método que, si se añaden nuevas figuras, hay que modificar para que pueda calcular sus áreas.
    - En concreto, se ha añadido la clase "Triángulo", y necesitaríamos otro if con comprobación de tipo.

Ejemplo correcto:
    - Las figuras implementan la interfaz "Figura", que tiene el método calcularArea.
    - La clase CalculadoraAreas usa este método abstracto para calcular las áreas.
    - Si añadimos nuevas figuras, al implementar este método estamos:
        - Ampliando la funcionalidad (abierto para extensión) de la calculadora de áreas.
        - Sin modificar su código (cerrado para modificación).