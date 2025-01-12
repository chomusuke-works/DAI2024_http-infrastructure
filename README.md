# DAI2024_http-infrastructure

Step 1: Static Web site
-----------------------

The web server is configured to listen for requests on port 565.
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

Step 4: Reverse proxy with Traefik
----------------------------------

Traefik will be used as a reverse proxy for our infrastructure.

It is configured through the `docker-compose.yml` file using labels.

Step 5: Scalability and load balancing
--------------------------------------

Step 6: Load balancing with round-robin and sticky sessions
-----------------------------------------------------------

Step 7: Securing Traefik with HTTPS
-----------------------------------



Optional step 1: Management UI
------------------------------

Optional step 2: Integration API - static Web site
--------------------------------------------------