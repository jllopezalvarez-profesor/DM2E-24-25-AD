SRP:
    - Cada clase o módulo debe estar enfocada en cumplir una única responsabilidad específica.
    - Cada clase o módulo debe tener una sola razón para cambiar

Ejemplo incorrecto:
    - Clase Usuario que maneja tanto la lógica relacionada con la información del usuario como la lógica de autenticación.
    - Según SRP, esto es incorrecto, ya que la autenticación y la gestión de la información del usuario son dos responsabilidades diferentes.
    - Sería mejor dividirlas en dos clases, por ejemplo, Usuario y Autenticación.

Ejemplo correcto:
    - La clase Usuario que maneja solo la lógica relacionada con la información del usuario.
    - Añadimos una clase "Autenticacion" en la que se realiza la lógica de autenticación del usuario.