------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Clasificador de grafos simples especiales
PURPOSE OF PROJECT: Académico
VERSION or DATE: Last version
HOW TO START THIS PROJECT:
AUTHORS: Anahí Sanabria Ugarte
USER INSTRUCTIONS:

Este clasificador funciona con listas de adyascencias y
el ingreso del grafo se hace de dos maneras:

*Inserción del grafo manual: este se ingresa desde la clase main, mediante los
métodos de creación de la clase grafo y el método de agregar vecino que se comporta
como un conector de vértices y crea una arista. No es necesario meter, por ejemplo, para
un vértice que va de v1 a v2  los vecinos en ambos sentidos. Basta que se agregue el vecino en cualquier
sentido, el grafo lo maneja de manera bidireccional y se guarda para ambos vértices

*Inserción gráfica: se ejecuta desde la clase AplicacionFinal, donde se maneja el botón vértice que creará circulos en la zona
de dibujo con un número asignado del 0 a n. En el apartado de conectar aristas se debe
ingresar los vértices que serán conectados y al presionar el boton conectar vértice se hará
internamente el método agregarVecino para guardar las adyascencuias en la lista. Iguualmente
no es necesario hacerlo en ambos sentidos. Posterior al dibujado de las aristas se debe presionar
el botón listo que mostrará la clasificación del grafo y reseteará la zona de dibujo
