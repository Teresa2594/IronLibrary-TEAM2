Iron-Library

Este programa permite al administrador crear una escuela entrando el numero de maestros, estudiantes y cursos así como crear cada uno de estos. Una vez todos los elementos de la escuela han sdo creados, podemos realizar diferentes gestiones como:

apuntar un estudiante a un curso
asignar un profesor a un curso
mostrar todos los estudiantes de la escuela
mostrar todos los cursos de la escuela
mostrar todos los profesores de la escuela
mostrar los alumnos de un curso concreto
mostrar los detalles de un profesor, un alumno o un curso en concreto
calcular los beneficios de la escuela
salir del programa
Para poder realizar cada una de estas funciones, el administrador ha de pasar comandos concretos por la consola. Toda entrada por consola tiene su control de errores diseñado en la clase Utils. A su vez, los diferentes métodos han sido testeados cuidadosamente en la carpeta test.

Diagrama de Clases

ironSchool

Caso de Uso

UsCaseIronSchool

A continuación pocederemos a explicar cada una de las clases que conforman este repositorio.

Clases

Clase Teacher

La clase Teacher incluye los parámetros

teacherId : identificador de tipo String generado automáticamente de teacher
code : integer que servirá para crear el teacherId
name : variable tipo string que muestra el nombre del profesor
salary : variable tipo double que define el salario del profesor
courses : array list de cursos que muestra los cursos a los que está adscrito el profesor
La variable code está únicamente implementada para crear el teacherId de manera que se genere automáticamente y para que teacherId sea un identificador único. Definimos code de manera que empieza en el 1 y que cada vez que se llama al constructor de Teacher este int incrementa en 1 unidad. En este momento, se crea el identificador único que es de la forma "teacher_code", es decir, un ejemplo seria "teacher_1".

Las demás variables representan el nombre de cada teacher (String), su salario (double) y los cursos a los que está adscrito (ArrayList). Las variables salario y el nombre son las únicas que se piden por parámetro en el constructor.

El comando assign pide por parámetro la escuela y los identificadores de un curso y de un profesor y asigna el curso al profesor, es decir, incluye el curso en el ArrayList de cursos de la clase Teacher. Si esta acción se realiza con éxito, imprime por consola que el profesor ha sido adscrito al curso correctamente.

El comando showTeachers pide por parámetro la escuela y imprime por consola todos los profesores que están adscritos al centro. Es decir, todos aquellos profesores que hemos introducido por consola. En este comando solo se muestran el nombre y el id de cada profesor.

El comando lookUpTeacher muestra toda la información recopilada sobre un profesor en particular que pedimos que pasen por parámetro a parte de la variable school. Por lo tanto, se muestra el id, el nombre, el salario y los cursos a los que está adscrito el profesor.

Clase Students

La clase Students incluye los parámetros

studentId : identificador de tipo String generado automáticamente de student.
code : integer que servirá para crear el studentId.
name : variable tipo string que muestra el nombre del estudiante.
address : variable tipo string que muestra la dirección del estudiante.
email : variable tipo string que muestra el correo electrónico del estudiante.
La variable code está únicamente implementada para crear el studentId de manera que se genere automáticamente y para que studentId sea un identificador único. Al igual que en el caso de la clase Teacher, definimos code de manera que empieza en el 1 y que cada vez que se llama al constructor de Student este int incrementa en una (1) unidad. En este momento, se crea el identificador único que es de la forma “student_code”, es decir, un ejemplo sería “course_1”. Las demás variables representan el nombre de cada estudiante (String), su dirección (String) y su correo electrónico (String). Las variables name, address y email son las únicas que se piden por parámetro en el constructor.

El comando enroll tiene como función inscribir a un estudiante en un curso, por lo que pide como parámetros tanto el id del estudiante como el del curso en el que se quiere inscribir. Además almacena el precio como money_earned para luego utilizarlo cuando sea necesario calcular el total de dinero recaudado por el curso según la cantidad de alumnos inscritos.

El comando showStudents pide por parámetro la escuela e imprime por consola todos los estudiantes inscritos en el centro. Es decir, todos aquellos estudiantes que se han introducido por consola. En este comando solo se muestran el nombre y el id de cada estudiante.

El comando lookUpStudent muestra toda la información recopilada sobre un estudiante en particular que se pide que se pase por parámetros además de la variable school. Por lo tanto, se muestra el id, el nombre, la dirección y el correo electrónico.

Clase Course

La clase Course incluye los parámetros

courseId : identificador de tipo String generado automáticamente de course.
code : integer que servirá para crear el courseId.
name : variable tipo string que muestra el nombre del curso.
price : variable tipo double que define el precio del curso.
money_earned: variable tipo double que representa el total del dinero ganado según la cantidad de alumnos inscritos a ese curso.
La variable code está únicamente implementada para crear el courseId de manera que se genere automáticamente y para que courseId sea un identificador único. Al igual que en el caso de la clase Teacher, definimos code de manera que empieza en el 1 y que cada vez que se llama al constructor de Course este int incrementa en una (1) unidad. En este momento, se crea el identificador único que es de la forma “course_code", es decir, un ejemplo sería “course_1”. Las demás variables representan el nombre de cada curso (String), su precio (double) y la cantidad de dinero ganado por ese curso (double). Las variables price y el name son las únicas que se piden por parámetro en el constructor.

El comando showCourses pide por parámetro la escuela e imprime por consola todos los cursos que se ofrecen en el centro. Es decir, todos aquellos cursos que se han introducido por consola. En este comando solo se muestran el nombre y el id de cada curso.

El comando lookUpCourse muestra toda la información recopilada sobre un curso en particular que se pide que pasen por parámetro además de la variable school. Por lo tanto, se muestra el id, el nombre, el precio y el total del dinero recaudado por ese curso.

El comando showProfit calcula la diferencia entre el total de dinero recaudado por los diferentes cursos ofrecidos en la escuela menos el total de los salarios cobrados por todos los profesores, obteniendo como resultado la ganancia total de la escuela.

Clase App

En la clase App solamente tenemos el método start que es el método principal para crear la escuela con todos los comandos requeridos.

Lo primero que nos aparece al ejecutar el método start es que introduzcamos un nombre para nuestra escuela. Al tener control de errores, el input tiene que ser de tipo String ya que si no lo es, no podemos pasar al siguiente paso y nos lo seguira pidiendo hasta que reciba un String.

A partir de ahí, la aplicación nos va a pedir que introduzcamos todos los datos necesarios, como el número de profesores, el número de cursos, el número de alumnos, los nombres de los mismos y todos los datos que se especifican en el Homework. Al introducir todos los datos válidos, la aplicación nos devuelve el menú con todos los comandos disponibles. A continuación veremos unos ejemplos de como funciona la aplicación. Solicitud_datos Menu Succesful_command

Clase Commands

A la clase Commands pertenecen todos los métodos que hacen accesible para el usuario cada una de las funcionalidades de la app.

MÉTODO	PARÁMETROS1	DESCRIPCIÓN
menu()	none	Imprime el menu de opciones
commands()	String command	Identifica el command insertado
enroll()	String studentId, courseId	Inserta student en course
assign()	String teacherId, courseId	Inserta teacher en course
showCourses()	none	Muestra todos los cursos
lookUpCourse()	String courseId	Muestra detalles del curso
showStudents()	none	Muestra todos los students
lookUpStudent()	String studentId	Muestra detalles del student
showTeachers()	none	Muestra todos los teachers
lookUpTeacher()	String teacherId	Muestra detalles del teacher
showStudentsEnrolled()	String courseId	Muestra los estudiantes del curso
showProfit()	none	Calcula el profit total
Clase School

La clase School contiene atributos de tipo Map que sirven como almacenamiento a cada uno de los objetos instanciados de las otras clases. De modo que puedan ser creados y posteriormente consultados con cada uno de los Commands insertados por nuestro usuario en consola.

Propiedades 2

schoolName
numberOfTeachers
teacherMap
numberOfCourses
courseMap
numberOfStudents
studentMap
isActive
El atributo isActive está directamente relacionado con el método start() de la clase. Dependiendo de su valor, y al estar dentro de un while, va a hacer que se ejecute o no el bloque de código al interior de la función. De cumplirse, repetirá la línea de output que se mostrará al usuario con las instrucciones, pidiéndole que ingrese nuevamente su respuesta si no ha ingresado un valor válido.

Clase Utils

A la clase Utils pertenecen todos los métodos que controlan los errores que se pueden producir cuando el usuario mete datos por consola.

MÉTODO	PARÁMETROS	DESCRIPCIÓN
isString()	String input	Devuelve una excepción si es un número o un String si no lo es
isInt()	String input	Devuelve una excepción si no es un número o un int si lo es
isDouble()	String input	Devuelve una excepcion si no es un doubl o un double si lo es
isCommand()	String command	Devuelve una excepcion si no froma parte de la list de commands posibles o el String del command si forma parte
checkString()	String input, String question	Maneja la excepción si isString() lanza la excepción y en caso que no lo lanze devuelve un String
checkInt()	String input, String question	Maneja la excepción si isInt() lanza la excepción y en caso que no lo lanze devuelve un int
checkDouble()	String input, String question	Maneja la excepción si isDouble() lanza la excepción y en caso que no lo lanze devuelve un double
checkCommand()	String command, String question,School school	Maneja la excepción si isCommand() o isId() lanzan la excepción y en caso que no lo lanzen devuelve el command(String)
checkId()	String id ,School school	Comprueba que el id pasado perenezca al map de la clase concreta (Student, Teacher o Course)
Esta clase tiene dos atributos, un scanner para que el usuario pueda responder a la preguntas formuladas por consola, y una variable booleana (exceptionIsActive) que nos ayuda a controlar las excepciones para que el programa siga su curso ho vuelva ha preguntar los datos en caso de no ser estos correctos. Además, también tiene tres onstantes que sirven para formatear el color de la fuente para realizar las preguntas por consola.

Creditos

Este proyecto se ha realizado gracias a los siguientes colaboladores:

Manel Espinosa
Maria Ray
Natalia Pinzon
Fernando Camacho
Ovidiu Stanescu
