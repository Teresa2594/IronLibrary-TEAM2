# Iron-Library

Este programa permite al usuario gestionar el sistema interno de una librería, pudiendo realizar diferente gestiones, como por ejemplo: 

1.- Añadir un libro.<br>
2.- Buscar libros por su título. <br>
3.- Buscar libros por su categoría. <br>
4.- Buscar libros por su autor. <br>
5.- Listar todos los libros de un autor. <br>
6.- Asignar libros a estudiantes. <br>
7.- Mostrar libros asociados a un estudiante. <br>
8.- Mostrar libros que deban entregarse ese día.<br> 
9.- Salir de la aplicación.<br>


Para poder realizar cada una de estas funciones, el usuario debe pasar por consola la opción numérica correspondiente. Toda entrada por consola tiene su control de errores.

## Packages: 

### 1.- Assets

Este paquete contiene las siguientes clases: 

Constants: Almacena las constantes relacionadas a los códigos de color de fuente. 

InputExcepcion: Almacena una excepción personalizada, a partir de la clase Excepcion, que será utilizada para el control de errores. 

InputKeyboard: Contiene todos los métodos relacionados al input de parámetros por parte del usuario. 

Utils: Contiene todos los métodos con la lógica necesaria para el desarrollo de la aplicación. 

### 2.- Models

Este paquete contiene las siguientes clases: 

Book

Author

Student

Issue 

Cada una de estas clases contempla las variables correspondientes a cada entidad de la base de datos. 

### 3.- Repository

Este paquete contiene los repositorios de cada una de las clase que son utilizados para la inyección de dependencias. 

## UML Diagram<br>
![My Image](UML.jpg)


Créditos

Este proyecto se ha realizado gracias a los siguientes colaboladores:

[Teresa Mira](https://github.com/Teresa2594)<br>
[Victor Sobrinos](https://github.com/VictorSobrinos)<br>
[Fernando Camacho](https://github.com/fernandojcm)<br>
[Martina Dorado](https://github.com/MartuDorado)<br>

