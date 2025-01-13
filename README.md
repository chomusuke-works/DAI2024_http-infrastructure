# DAI2024_http-infrastructure

Step 1: Static Web site
-----------------------

The web server is configured to listen for requests on port 80.
The website's root is located in the `/web/start-bootstrap` directory.

This structure allows to add new websites in the `/web` directory down the line.

`start-bootstrap.conf` replaces nginx's `default.conf` file in order to specify the website's location.

Step 2: Docker compose
----------------------

The static web server defined at the step 1 can be spun up using docker compose.

The Docker architecture consists of a Dockerfile that allows the administrator to
build an image containing the configuration as well as the contents of the website.
The `docker-compose.yml` file then uses this dockerfile to integrate it in a scalable
infrastructure.

Step 3: HTTP API server
-----------------------

The API represents a simple zoo database. It contains different animals
that have a name, a species, and a birth year. The user can insert, fetch, 
update or delete an animal by specifying its name.

They can also fetch the list of all the animals in the zoo.

The endpoints are the following:
- `POST /api/animals` - add a json representation of the animal to the body
- `GET /api/animals/{name}` - fetch an animal's data by its name, in json format
- `GET /api/animals` - fetch the list of all animals, in json format
- `PUT /api/animals/{name}` - update an animal's species or birth year, specified as query parameters
- `DELETE /api/animals/{name}` - removes an animal from the database

Step 4: Reverse proxy with Traefik
----------------------------------

Traefik will be used as a reverse proxy for our infrastructure.

It is configured through the `docker-compose.yml` file using labels.

The static website and the zoo api previously defined can be accessed with the following paths:
- `localhost` - static website
- `localhost/api` - zoo API

The zoo API manages its own endpoints past the localhost/api point, as defined in step 3.

Step 5: Scalability and load balancing
--------------------------------------

To start the infrastructure with multiple instances of the static web server, use the command
`docker compose up -d --scale start-bootstrap=<n>`, `n` being the number of instances wanted.

To scale the infrastructure up or down while it is running, use the command 
`docker compose scale start-bootstrap=<n>`.

Step 6: Load balancing with round-robin and sticky sessions
-----------------------------------------------------------

Step 7: Securing Traefik with HTTPS
-----------------------------------



Optional step 1: Management UI
------------------------------

Optional step 2: Integration API - static Web site
--------------------------------------------------