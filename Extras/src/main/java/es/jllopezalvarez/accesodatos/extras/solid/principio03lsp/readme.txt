LSP:
    - Los objetos de una subclase deben poder sustituir a los objetos de la clase base sin alterar el comportamiento esperado del programa.

Ejemplo incorrecto:
    - La clase base "Ave", tiene un método "volar()"
    - Aparece una clase "Pinguino", subclase de "Ave". Los pinguinos no pueden volar, por lo que lanzamos excepción.
    - Esto rompe LSP, porque si tenemos código que llama al método "volar" de la clase "Ave", si recibe un objeto de
    la sublcase "Pinguino" fallará.

Ejemplo correcto:
    - La clase abstracta (podría ser una interfaz) Ave tiene un método abstracto "mover()"
    - Tenemos una clase "AveVoladora", que extiende "Ave" y tiene un método "volar". La implementación de mover llama a volar.
    - En la clase "Pinguino" extendemos también "Ave" y tenemos un método "nadar". La implementación de mover llama a nadar.

