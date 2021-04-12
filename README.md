# CaWaStore

Esta aplicación consiste en una tienda de productos de calistenia (Crosfit).
Los usuarios adscritos a la página podrán comprar productos de toda clase relacionados con el deporte.

###### Función pública: 

- Se pueden visualizar todos los artículos de la tienda.

###### Funciones privadas:

- Se basan en las herramientas que tiene el vendedor para actualizar el catálogo y el administrador en caso de que una cuenta sea robada poder devolversela a su dueño.

###### Función de cada usuario:

- Los datos personales de cada uno, los productos adquiridos y el cesta de la compra.

## Las entidades de la aplicación son: 

- Usuario: debe contener su nombre, correo electrónico, identificador, avatar, método de pago y dirección de envío.
- Producto: debe contener identificador único, categoría de producto.
- Reseña: los clientes registrados pueden realizar una reseña de producto.
- Pedido: productos elegidos por el cliente, coste de la operación y disponibilidad.
- Promoción: descuentos aplicables a los productos.

## [Servicio interno](https://github.com/gonpg/CaWaStore-Rest)

- Envío de correo electrónico a los usuarios cuando se realiza una compra.

- Generación de pdf con el cálculo total del pedido con las promociones aplicadas. 

## Modelo E/R:

![](Documentos/modeloER.PNG)

## Diagrama de clases:

![](Documentos/UML.PNG)

## Diagrama de clases y templates:

![](Documentos/DDCT.PNG)

Rosa- Clases//
Morado- Repositorios//
Verde- Controladores//
Azul- Templates.

## Capturas de pantalla de la web
![](Documentos/Tienda.png)
![](Documentos/Login.png)
![](Documentos/Perfilusuario.png)
![](Documentos/Registro.png)
![](Documentos/Registrocompleto.png)
![](Documentos/Añadirproducto.png)
![](Documentos/Detallesdeproducto.png)
![](Documentos/Productoeliminado.png)
![](Documentos/Realizarpedido.png)
![](Documentos/Reseñaañadida.png)

## Diagrama de navegación:
![](Documentos/navegación.PNG)

## Despliegue de la máquina virtual:

1. Compilación

```
$ mvn package
```

Para omitir los tests:  
```
$ mvn clean package -DskipTests
```

2. Distribución
```
$ scp CaWaStore-0.0.1-SNAPSHOT.jar <user>@<host>:
$ scp CaWaStore-Rest-0.0.1-SNAPSHOT.jar <user>@<host>:
$ scp CaWaStore.service <user>@<host>:
$ scp CaWaStore-rest.service <user>@<host>:
```

3. Despliegue
```
$ sudo apt install openjdk-8-jdk-headless
$ sudo apt install mysql-server
$ sudo mysql_secure_installation
$ mysqld --initialize (En caso de que no cree el directorio)
$ sudo mysql
$ mysql> create database CaWaStore;
$ mysql> CREATE USER 'root'@'localhost' IDENTIFIED BY 'administrador';
$ mysql> GRANT ALL ON CaWaStore.* to 'admin'@'localhost';
$ sudo cp CaWaStore.service /etc/systemd/system/
$ sudo cp CaWaStore-Rest.service /etc/systemd/system/
$ sudo systemctl enable --now CaWaStore.service
$ sudo systemctl enable --now CaWaStore-Rest.service
```
------X------X-------

## Componentes del grupo: 

- Miguel Gonzalo Pérez González: mg.perez.2017@alumnos.urjc.es // https://github.com/gonpg

- José Ángel Sánchez Pérez: ja.sanchezp.2017@alumnos.urjc.es // https://github.com/joseangelsanchezperez



