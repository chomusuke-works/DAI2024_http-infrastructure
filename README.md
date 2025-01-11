# DAI2024_http-infrastructure

Step 1: Static Web site
-----------------------

The web server is configured to listen for requests on port 80.
The website's root is located at `/web/start-bootstrap`.

This allows to add new websites in the `/web` directory down the line.

Step 2: Docker compose
----------------------

The static web server defined at the step 1 can be spun up using docker compose.

The Docker architecture consists of a Dockerfile that allows the administrator to
build an image containing the configuration as well as the contents of the website.
The docker-compose.yml file then uses this dockerfile to integrate it in a scalable
infrastructure.

The website is mapped to the port 565 on the host machine. This can be modified by 
editing docker-compose.yml.

Step 3: HTTP API server
-----------------------

Step 4: Reverse proxy with Traefik
----------------------------------



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