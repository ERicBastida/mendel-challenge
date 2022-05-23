# Mendel challenge
## Servicio Web RestFul de transacciones

[![N|Solid](https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg)](https://nodesource.com/products/nsolid)


Proyecto realizado como parte del proceso de selección de personal para [Mendel](https://mendel.com/)
Tecnologías utilizadas

- Java 11
- SpringBoot
- Junit
- Docker

## Java Code Challenge

Nos gustaría tener un servicio web RESTful que almacene transacciones (en memoria está bien) y
devuelva información sobre esas transacciones.
Las transacciones a almacenar tienen un tipo y un monto. El servicio debe admitir la devolución de
todas las transacciones para su tipo. Además, las transacciones se pueden vincular entre sí (usando un
"parent_id") y, por otro lado, necesitamos saber el monto total involucrado para todas las transacciónes
vinculadas a una transacción en particular.



## Endpoints

### Obtención de transacciones
El servicio buscara en memoria las transacciones almacenadas al momento, que coincidan con el id ingresado. Caso contrario devolverá un NOT_FOUND 
```sh
GET /transactions/{transactionId} 
-> Response
{
    "amount": 10.0,
    "type": "cars",
    "parentId": null
}
```

```sh
"transactionId" : Long : Identificador únivoco de la transacción.
"amount": Double : Monto de la transacción.
"type": String : Tipo de transacción.
"parentId": Null | Long : En caso de no tener relación con otra transaccion devolvera null.

```

### Inserción o actualización  de una transacción
Este endpoint se encargará de almacenar en memoria una nueva transacción. Previamente confirmará la existencia del mismo, en este caso si el transactionId ya existe responderá con un NOT_CONTENT y actualizará el registro. En caso de no existir dicho registro lo almacenará y retornará un CREATED.
```sh
PUT /transactions/{transactionId} 
-> Request
{
    "amount": 10.0, 
    "type": "cars",
    "parentId": 1
}

```

### Busqueda de transacciones por Tipo 
Este endpoint se encargará de obtener todas las transacciones que hagan match con el tipo de transacción buscada. Enviando com respuesta un listado de transactionIds, caso contrario responderá con un NOT_FOUND.
```sh
GET /transactions/types/{type} 
-> Response
[1,2]

```
```sh
"Response" : Lis<Long> : Lista de transactionIds de las los registros que coincidan con el tipo buscado.
```

### Monto total de las transacciones
Este servicio se encarga de buscar todas las transacciones relacionadas al transactionId consultado mediante los parent_id's y obtiene como resultado la suma de los mismos.
```sh
GET /transactions/sum/{transactionId} 
-> Response
{
    "sum": 100.0
}

```



