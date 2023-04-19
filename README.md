# Hilos y Concurrencia
## Descripción del Proyecto
Creación de Hilos, para demostrar el uso de LOCK & UNLOCK como formas de garantizar la Atomicidad, Concurrencia, Aislamiento y la Durabilidad de los Datos, mostrando un proceso de Hilos sin su implementación, y otro implementandolo.

## Enunciado de la Práctica
Utilizando MySql, deberá crear una base de datos que contenga una tabla Movimiento con un campo que servirá para incrementar o decrementar movimientos.

A través de algún lenguaje de programación que soporte hilos, deberá crear dos hilos, el primero que será encargado de incrementar en X unidades el campo correspondiente en la tabla movimiento. El segundo hilo será el encargado de decrementar en Y unidades el campo correspondiente en la tabla movimiento. Tanto el primer hilo como el segundo, se podrán alterar el comportamiento para establecer cada cuanto se ejecute el primer y segundo hilo, así como las cantidades de incrementar y decrementar, además del tiempo total de ejecución de los hilos y el valor inicial de la tabla movimiento.

Utilizando la lógica anterior (Escenario1), deberá de realizar lo mismo utilizando el comando de lock y unlock para los incrementos y decrementos (Escenario2).

El objetivo es que utilizando los mismos parámetros tanto para el Escenario1 y Escenario2 ejecute diversas pruebas y pueda llegar a conclusiones, respaldadas en las diferentes pruebas que realice. Para comentarlas en un pdf.