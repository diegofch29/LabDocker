# Taller de de modularización con virtualización e Introducción a Docker y a AWS

Con el uso de virtualizacion con docker se creo una micro-aplicacion web de logs que puede correr en una instancia EC2 de AWS.

## Como obtener
Use la siguiente linea para obtener el proyecto
  ```
  git clone https://github.com/diegofch29/LabDocker
  ```
## Para instalar y correr
  Antes de probar el proyecto debe poner a correr un contenedor con mongoDB en el para este ingrese a LabDocker/AppLbRounds e introdusca la siguiente linea
  ```
  docker-compose up -d
  ```
  
  En el repositorio se encuentra dos proyectos uno recibira las peticiones y las mostrara para correr este entre a LabDocker/AppLbRounds e instale
  ```
  mvn clean install
  ```
  y ejecute
  ```
  mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.applbrounds.SparckWeb"
  ```
  
  El segundo se encarga de conectarce a la base de datos para lo cual de ingresar a LabDcoker/LogService e instale
  ```
  mvn clean install
  ```
  y ejecute
  ```
  mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.applbrounds.SparckWeb"
  ```
  
  ingrese a http://localhost:4567/data y pruebe.
  

## Usando Docker
  Para crear la imagenes debe ingresar a LabDocker/LogService e ingrese la siguiente linea
  ```
  sudo docker build --tag imagedockerlogservice
  ```
  Ahora ingrese a LabDocker/AppLbRounds e ingrese
  ```
  sudo docker build --tag imagedockerapplbrounds
  ```
  Para crear los contenedores:
  * Contenedor AppLbRounds
   ```
   docker run -d -p 4567:6000 --name dockerapplbrounds imagedockerapplbrounds 
   ```
  * Conetenedores Logservice(Ejecutar 3 veces omo se muestra).
  ```
  docker run -d -p 34000:34000 --name dockerlogservice1 imagedockerlogservice
  ```
  ```
  docker run -d -p 34001:34000 --name dockerlogservice2 imagedockerlogservice
  ```
  ```
  docker run -d -p 34002:34000 --name dockerlogservice imagedockerlogservice
  ```
  
  Ingrese a http://localhost:4567/data y deberia poder ver lo siguiente.
  
  ![Principal](/images/proof.png)
  
  ## Corriendo en una instancia EC2 de AWS
  
  * Primero se creo una instancia EC2 en AWS (actualmente terminada).
  * Se descargo un contenedor de Mongodb.
    ![mongodb](/images/MongoContainer.png)
  * Desde dockerhub se descargaron y se corrienron los conteiners
  * Lbrounds
    ![lbrounds](/images/lbrounds.png)
  * Logservice
    ![logservice](/images/logservice1.png)
  * Resultado docker
    ![AWSdocker](/images/AWSDocker.png)
  * Resultado final
    ![proof](/images/goof.png)
  
  ## Construido  
  
  
    * [Java 8](https://www.java.com/) - Leguaje de programacion
    * [Maven] (https://maven.apache.org/) - Manejador de dependencias
    * [Docker] (https://www.docker.com/) - Virtualizacion en contenedores
    * [AWS] (https://aws.amazon.com/) - Computacion en la nube
    * [Github] (https://github.com/) - Manejador de versiones
    


