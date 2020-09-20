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
  
## to run

```
mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.applbrounds.SparckWeb"
```
```
mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.logservice.App" 
```
