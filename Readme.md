# EGA.Data.API.v3.FileDatabaseService

This is a Database Interaction Server. It (1) provides File/Dataset information and (2) performs basic database logging

This service is an abstraction layer to the local database, which keeps information about files and datasets, as well as some basic logging tables.

Dependency: 
* CONFIG (`https://github.com/EGA-archive/ega-config-server`). The `'bootstrap.properties'` file must be modified to point to a running configuration service, which will be able to serve the `application.properties` file for this service `FILEDATABASE`
* EUREKA (`https://github.com/EGA-archive/ega-eureka-service`). The FILEDATABASE service will contact other services via EUREKA and registers itself with it so that other services can contat FILEDATABASE..

Dependent:
* RES (TODO)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Installing

```
mvn package
```

This will produce the Config service jar file in the /target directory.

### Documentation

DOWNLOADER primarily interacts with the databases used by the API. It sits behind the edge services, which enforce Authentication and Authorization before any call to this service is made. This service peforms no further securoty checks.

[GET] `/stats/load` (not secured by token; used by the load balancer heartbeat.)
[GET] `/datasets/{dataset_id}/files`
[GET] `/files/{file_id}`
[GET] `/files/{file_id}/datasets`
[GET] `/files/{file_id}/index`
[POST] `/log/event`
[POST] `/log/download`

### Databases

There are 5 tables necessary for this service:

* File (read-only for this service): This table contains all files held at the current location. Files are identified by the unique EGAF File ID and are placed in Dtasets with unique EGAD IDs (permissions are granted on a dataset-level) This table also contains the absolute path/URL to the archived file.
* FileDataset: Necessary to link Datasets to Files (EGA permissions are based on datasets, so it is necessary to know which files are in a dataset)
* FileIndexFile: Links files to potential Index files, if present. This is necessary to provide htsget functionality

* DownloadLog: Logs downloads - both successes and failures

* Event: Generic table to log 'events' of interest

### Todos

 - Write Tests

### EXPERIMENTAL: Deploy

The service can be deployed directly to a Docker container, using these instructions:

`wget https://raw.github.com/elixir-europe/ega-data-api-v3-downloader/master/docker/runfromsource.sh`  
`wget https://raw.github.com/elixir-europe/ega-data-api-v3-downloader/master/docker/build.sh`  
`chmod +x runfromsource.sh`  
`chmod +x build.sh`  
`./runfromsource.sh`  

These commands perform a series of actions:  
	1. Pull a build environment from Docker Hub  
	2. Run the 'build.sh' script inside of a transient build environment container.  
	3. The source code is pulled from GitHub and built  
	4. A Deploy Docker Image is built and the compiled service is added to the image  
	5. The deploy image is started; the service is automatically started inside the container  

The Docker image can also be obtained directly from Docker Hub:  

`sudo docker run -d -p 9050:9050 alexandersenf/ega_downloader`  or by running the `./runfromimage.sh` file.