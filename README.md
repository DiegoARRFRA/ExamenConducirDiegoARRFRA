# ExamenConducirDiegoARRFRA
App encargada de realizar test de conducción de la dgt


INFORMACIÓN GENERAL
(NOTA) Para ver a más detalle la documentación, se recomienda verlo en el proyecto, donde mucha de la información es más visible con las variables y funcionamiento de procesos.

Esta app es una aplicación orientada al trabajo y práctica con la orientación de poder realizar y aprobar los exámenes teóricos de conducción
La app se divide en varias clases las cuales procederemos a explicar a continuación.

**La clase InterfazPrincipalAutoescuela: **
Esta es la clase principal de nuestra aplicación, es la clase encargada de dar la opción al usuario sobre qué tipo de examen quiere. Su misión es en esencia la de establecer el filtro para poder acceder a una u otra base de datos de las tres disponibles.
La clase se fundamenta en una interfaz muy visual. Esta interfaz está pensada para ser intuitiva y permitir que el usuario acceda al contenido deseado sin la necesidad de botones.
Se fundamenta principalmente en una variable estática llamada baseElegida la cual se modifica y cambia en función del elemento en el que el usuario haya seleccionado.
Por ejemplo, si se pulsa en la foto del coche, o el enunciado del coche baseElegida pasará a tener el valor “coche”. Este valor será entorno al cual estableceremos la función con una u otra base de datos en el futuro.

** La clase Conexión **
Mi clase Conexión tiene dos funciones, la primera es leer los datos almacenados en mi fichero Properties, he decidido crear un fichero Properties para las direcciones por dos motivos. El primero es que evito hardcodear el máximo código posible siempre, y el segundo es que así doy la libertad  de crear más bases de datos de una forma más cómoda. 
Al estar trabajando con SQLite sus sentencias son CREATE O OPEN IF EXISTS, eso quiere decir que en caso de querer implementar una nueva conexión solamente haría falta el añadir su variable en java.

El método de leerProperties extrae la información almacenada en el Properties y la pasa a unas variables estáticas, cada URL dentro del Properties es un puntero a la dirección de nuestra base de datos que en este caso es un fichero SQLITE.
Luego simplemente tenemos establecidos unos Getter que devuelven esa variable y así condicionamos el acceso a la Base de datos desde un solo método, independientemente de cual de los tres archivos se elijan.
El método leerProperties  Mediante un TryCatch extraemos el contenido del Properties y lo asignamos a las variables definidas como atributos.
Luego los métodos getter consecuentes son un return con dicha variable para dar el acceso (no olvidemos que el contenido del properties es la url del SQLITE y de la conexión). Estos getter están controlados mediante un switch por parte del demandante para evitar posibles complicaciones.


**La clase CreadorExamen**
Esta clase tiene muchas de las funciones principales y esenciales de los exámenes.
Entre ellas están las siguientes.
1.	Establecimiento de la conexión (Es donde tenemos nuestra variable URL que es controlada mediante un Switch con el parámetro recibido de InterfazPrincipalAutoescuela, la clase Creador es la que establece por lo tanto la URL sobre la cual vamos a hacer todas las conexiones del examen.
2.	 Delimitar el número de preguntas que tendrá el test que se ejecutará a continuación, esto se hace mediante un radioButtonGroup creado para el control de este parámetro que será clave en la creación de los exámenes.
Este elemento lleva incorporados varios métodos de control que sirven para evitar errores, como que por ejemplo se intente crear un examen sin delimitar el número de preguntas, aunque se podría haber puesto un default en caso de no seleccionar decidí no hacerlo así para darle más sentido a este conjunto de botones.

3.	Implementa un botón de creación de preguntas, si bien no es una función relevante decidí facilitar la tarea de la introducción de datos en nuestras bases de datos para agilizar los procesos de creación de las mismas. Esta clase de creación sería implementada en otra Clase o incluso proyecto más relacionado con la parte de administración, pero decidí implementarla aquí también para facilitar la corrección y la comprobación de insertar nuevas preguntas y ver si funcionan o no.




**La clase InsertarPreguntas**
La clase InsertarPreguntas es una clase auxiliar que tiene una función complementaria. Esta clase como explicamos arriba estaría implementada en una parte de mi aplicación orientada al administrador, pero por motivos de corrección y de comodidad la implementé aquí.
Esta clase esta conformada en su totalidad por componentes, cuya misión es la de recoger los parámetros requeridos para la conformación de nuevas preguntas.  Estos parámetros son introducidos mediante JDBC en nuestra base de datos, con el try-with Resources para cerrar automáticamente la conexión y con el código de inserción en la base de datos diseñado para evitar las inyecciones SQL.

Cada componente tiene asignado un elemento clave de las tablas que conforman las preguntas,  y por lo tanto todos deben de ser rellenados, salvo el de las imágenes el cual es Opcional y en caso de dejarlo en blanco se pondrá nuestro logo de autoescuela en la pregunta.
La creación del componente consistió en un label con un textfield y establecer los getter y setter pertinentes para permitirnos obtener y modificar el código interno de los elementos del componente.
Luego hicimos un clean and build y mediante tools y paleta lo añadimos a nuestros beans. Este componente considero que es de los más útiles en la creación de los frames ya que nos facilitan mucho trabajo.


**Clase EsquemaExamen ** 
 Nuestra clase EsquemaExamen es la clase donde se desarrolla todo el contenido del examen. Podemos decir que es la clase que trata con todos los objetos y clases relacionadas con todo el contenido de nuestros exámenes.
 Nuestros atributos y sus funciones son las siguientes:
opcionesBotones  Para comprobar que se han marcado todas las opciones.
respuestaCorrecta  Para saber que contenido se ha seleccionado la opción correcta en el boton marcado por el usuario
numeroPreguntaActual = 1  Numero de la pregunta actual, empezando por 1
contador = 0   Parecido a lo de arriba, pero pensado para mandar datos a los array (empienzan en 0)




    Sus funciones son las siguientes
1-	Limpiar los elementos volátiles guardados en memoria, permitiendo poder realizar varios test sin salirnos de la aplicación.
2-	Es la encargada de entrelazar las clases de ObjetoPregunta,ObjetoRespuesta,ObjetoImagen.
3-	Es la encargada de mostrar por pantalla todo lo relacionado con dichos objetos
4-	Coloca de manera Random las preguntas y las respuestas en los botones.
5-	Aumenta los contadores necesarios para la lectura y tratamiento de los datos
6-	Rellena la información recogida en nuestra tabla examen_usuario que es la que examina el rendimiento del usuario durante el examen, mostrando luego toda pregunta por pregunta
7-	Distingue las preguntas acertadas y falladas y las refleja.
8-	Variables de control del flujo del programa.


MÉTODOS.
VaciarTabla() -->   Método de vaciado de tabla y de Arraylist (Estos últimos son una doble capa de aseguración de borrado). Consiste en una sentencia de borrado de la tabla de examen_usuario con su trywith-resources.
obtenerExamen();-->   El método encargado de recoger las preguntas y respuestas.          
establecerElementos();  -->   El método encargado de establecer los datos en nuestro frame.
obtenerExamen ()  --> Rellenamos todos los recipientes de nuestros preguntas y respuestas
establecerElementos () --> Cosas que hace este método:
        // 1- Poner la imagen de la foto
        // 2- Poner el texto de la pregunta
        // 3- Hace una llamada a establecer
        

establecer()--> Funciones:
    1- Elige un número random del 1 al 3 sin que se repitan para poner los diferentes enunciados posibles de la respuesta
    2- Elige las respuestas incorrectas posibles relacionadas al id_pregunta que está actualmente
    3- Elige de forma random el tipo de respuesta que tendrá cada botón en función de lo marcado en el array random que operamos arriba.
    4- Vacia de nuevo el array para operar con el en la siguiente pregunta

ponerImagenes () -->  Establece las imagenes y nos las pone en nuestro label, gracias al método del objeto imagen y el contador de esta clase siempre están sincronizados.

numeroRandom () -->   Este método se encarga de rellenar el arrayList opcionesBotones, es un arraylist compuesto de 3 elementos que son el numero 1,2,3. -->
                      que Son generados de manera random entre sí mismos y se guardan de manera aleatoria en cada posición de nuestro arrayList.
                      
Los métodos :  Nos elige de manera Random que va a ir en nuestros botones.
  elegirRandomOpcionUno ()
  elegirRandomOpcionDos ()
  elegirRandomOpcionTres () -->
  
Estos métodos de selección random de los botones uno, dos y tres se fundamentan en la idea de que cada botón tendrá asociado siempre un get de nuestro arraylist.
Al no haber repetidos y realizar este proceso con todas las posibilidades en esa posición nos aseguramos que el contenido siempre quede relleno y sea totalmente aleatorio.

botonMarcado () -->
Este es un método que se encarga de darnos la información sobre que posición seleccionó el usuario al elegir la pregunta. Es lo que nos va a permitir luego reconstruir las preguntas.


todoMarcado () --> Método de comprobación de marcado. En caso de que no esten marcados no dejaremos pasar de pregunta.
insertarRespuestaUsuario () --> Comprobamos a ver que boton se ha marcado, y con las variables de recogida que hemos ido almacenando introducimos toda la información completa
                           // Sobre todos los aspectos relevantes que ha elegido el usuario, y nos permite reconstruir cada pregunta de manera idéntica.
                           // En caso de que el usuario elija lo mismo que donde la posición donde estaba la respuesta correcta, podemos decir que acertó. 
                            // Si acierta llamamos a insertarTablaAcierto y sino a insertar un fallo.
                            
 insertarEnTablaAcierto () && insertarEnTablaFallo () --> Insertamos en nuestra tabla examen_usuario la columna acierto o fallo en función de si lo es. Lo hacemos mediante
                                                    // Try with-resources
 
aumentarMarcadores () -->  Cada vez que pasamos de pregunta, aumentamos los marcadores para la próxima.

boton_avanza --> // Si las preguntas actual es la misma que el numero máximo, podemos afirmar que no hay más preguntas
                 // Si el número de pregunta actual mayor que la máxima definida para el test entonces:
                        boton_avanzar.setEnabled(false); // En caso de que se intente proseguir se deshabilita el botón.
                 // Si las preguntas no son iguales, sabemos que tienen que ser menores por el control anterior del elseIf.
                //Comprobamos los marcados
                       todoMarcado ();    
            // Si la variable es falsa, no dejamos continuar
            //Si la variable es correcta, insertamos los datos recogidos de esta pregunta, aumentamos los marcadores y establecemos los nuevos elementos de la siguiente.
            
            
boton_finalizar -->  Insertamos la respuesta del usuario, si la pregunta actual es menor que la máxima, es que el exámen no ha terminado todavia por lo que no lo dejamos actuar.
                    En caso de que no, introducimos los ultimos parámetros (los referentes a la ultima pregunta y cambiamos a la clase de ver errores y aciertos del examen.
                    
                    
******CLASE OBJETO PREGUNTA*********

El objeto Preguntas es el objeto que trata todo lo relacionado con la tabla preguntas de nuestras bases de datos
Está conformado principlamente por dos arraylist que representan el ID y el Contenido de las preguntas
Se conforma también de los getter de los arrayList y su "Setter" que está fundamentado en una consulta de nuestra base de datos y obtención de manera random de las preguntas para el examen actual.

 obtenerPreguntas () --> Este método sirve para obtener las preguntas de la base de datos, las obtenemos de forma random en función del numero de preguntas que haya querido seleccionar el Usuario.
    // Lo primero que hacemos es un borrado de memoria de los arrayList, sirve para deshechar residuos que pudieran quedar de ejecuciones pasadas de estos métodos. Al borrarlo
    // Nos aseguramos que todo el contenido de nuestras sentencias va a entrar en arraysLists nuevos.
    // Hacemos la Select en la que establecemos como limit el parámetro numeroPreguntas, que es aquel que definimos en la clase CreadorExamen y nos marcaba numero de preguntas que seleccionamos
    // Con el raddio button.
    
*****CLASE OBJETO RESPUESTAS *********
 
 // Este objeto respuestas es el objeto que tiene todo lo relacionado con las respuestas de las preguntas que hemos obtenido anteriormente con el objeto Preguntas.
 // Está conformado en su esencia por seis arrayList, uno contiene las respuestas correctas y los otros 5 respuestas incorrectas, todas relacionadas con los ID_Preguntas.
 // Este objeto tiene muchas misiones y funcionalidades, para empezar podemos definir entre sus funciones la de obtener las respuestas de la tabla de respuestas de la base
 // Obtener de manera random las dos respuestas incorrectas y pasar los datos. 
 
 obtenerRespuestas() --> Este es el método que llamamos desde el EsquemaExamen, es el que va a activar todo el funcionamiento de nuestras clases de objeto respuestas.
 // Los getter, en este caso serán solamente tres a pesar de tener 6 arraylist. Esto ocurre porque siempre mandaremos solo 3 datos, una respuesta correcta y 2 incorrectas.
 
 vaciarElementos () --> // Método de control de vaciado, para evitar residuos de operaciones anteriores y trabajar con arraylists "nuevos".
    // Lo llamaremos siempre antes de extraer los datos de la base de datos y rellenar el contenido.
    
 obtenerRespuestaCorrecta () -->  Este método sirve para rellenar en nuestro arraylist todas las respuestas correctas extraidas de las preguntas que hemos sacado de la base de datos
                               // Establecemos todas las respuestas que vamos a obtener apartir de los arraylist IDpreguntas que ya obtuvimos antes, así guardamos la relación.

obtenerRespuestasIncorrectas () --> // Este método sirve para rellenar en nuestros arraylists con todas las respuestas incorrectas relacionadas con cada pregunta, sacamos 5 para ir alternando entre posibles erroneas.

elegirRandomIdRespuestaIncorrecta () -->   
     // Este es el método que nos va a elegir de manera random 2 numeros diferentes del 1 al 5, en función de los números seleccionados elegiremos un arraylist y su contenido 
    // erroneo para mostrarlo, de esta forma nos aseguramos de que pueda salir literalmente cualquier respuesta incorrecta de las 5 sin importar el orden, es un proceso similar
    // Al que hicimos en nuestra EsquemaExamen para delimitar que botones tendran las respuestas y cual  tendran la correcta y cual no de las opciones.
    
obtenerRespuestaFalsaUno (int id) --> En función del numero obtenido en la posicion 0 del 1 al 5 seleccionaremos la respuesta falsa random de ese arraylist.
obtenerRespuestaFalsaDos (int id) --> En función del numero obtenido en la posicion 0 del 1 al 5 seleccionaremos la respuesta falsa random de ese arraylist.

******** CLASE ResultadoFinal ***************

 // Esta es la clase ResultadoFinal, es la clase encargada de mostrar los resultados del examen y de mostrar de manera detallada que respuestas hicimos bien y cual no.
 // Implementa dos listas como las que vimos en clase personalizadas, cada una de ellas está asociado a un campo de nuestra tabla de examen_usuario.
 // Por un lado esta las respuetas acertadas y por otro las falladas. 
 // Esta clase se encarga principalmente de rellenar esas listas, calcular con contadores los fallos y establecer mediante una operación el resultado si es aprobado o no.
 // También hace de puente a nuestra clase de vista personalizada, que con el click permite al usuario recrear la pregunta y nos muestra toda la información sobre la misma
 
   int contadorAciertos, contadorFallos; // Los contadores, que van a delimitar si hemos acertado o fallado.
   public static String idItem; // Este es el ID del item que se selecciona en la lista para ver a fondo
   static DefaultListModel listaAciertos,listaFallos ; // Las dos listas.

    // Rellenar las listas y comprobar si esta aprobado.
        rellenarListaAciertos();
        rellenarListaFallos();
        comprobarExamenAprobado();

rellenarListaAciertos () -->
// Rellenamos la lista mediante una consulta, y en el bucle del cursor añadimos los elementos que obtenemos de dicha consulta a la lista.
 // Establecemos un contador, cada vez que ese contador se suma es que se ha añadido un item a la lista.
 
 // Añadimos el modelo y mostramos en la etiqueta cual han sido los aciertos gracias al contador.
        jListaAciertos.setModel(listaAciertos);   
        mostrar_Aciertos.setText("NUMERO DE ACIERTOS :  " + contadorAciertos);
        
 rellenarListaFallos () -->  Mismo proceso con la otra lista.
 
comprobarExamenAprobado () -->  // Comprobamos si el examen está acertado, en función del número de preguntas tendrá unas carácterísticas para aprobar.

*****CLASE MostrarPreguntaContestada ********
// El funcionamiento de esta clase se basa en recrear la pregunta exactamente en el estado en que el usuario la marcó, y mostrar las opciones falsas y correctas 
 // Estas variables son las que van a recoger los datos que leemos de la tabla del examen del usuario.
 
 rellenarContenido () -->  
 // El método rellenar contenido es el que va a operar con todos los submetodos
 // La mision de este método es
       1. Asignar a la etiqueta numero_pregunta su numero, para informar al usuario
       2. Leer la tabla del examen, recogiendo todos los datos relativos a las preguntas aparecidas para mostrarlas
       3. Rellenar los elementos objetos y foto en función de los parametros leidos

leerTablaExamen () --> 
 Hacemos una SELECT de la tabla y recogemos los parametros obtenidos, los almacenamos en variables
 Estos parametros definiran el como estructuramos el frame

rellenarPreguntas () --> 
// Si se ha acertado la pregunta entonces lo primero es asignar el texto que habia en la pregunta cuando se formuló a la recreación actual
// Despues comprobamos cual es la marcada por el usuario
// Deshabilitamos los botones, ya que esta interfaz no esta diseñada para operar, solo para mostrar contenido
// Despues comprobamos cual es la correcta, la asignamos un color verde a la correcta y un rojo a las erroneas

rellenarFoto () -->  // Obtenemos la foto que había en el momento.
boton_volver --> Volver al frame anterior.





