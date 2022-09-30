# Employees kiindulási projekt

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432  --name employees-postgres postgres
docker build -t employees .
docker run -d -p 8080:8080 --name my-employees employees
```

```shell
docker network create employees-net
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432  --name employees-postgres --network employees-net postgres
docker build -t employees .
docker run -d -e SPRING_DATASOURCE_URL=jdbc:postgresql://employees-postgres/employees -p 8080:8080 --name my-employees --network employees-net employees
```