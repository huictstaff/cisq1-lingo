# CISQ1: Lingo Trainer

This project is part of a sophomore Software Development 
*Continuous Integration and Software Quality 1 (CISQ1)* at the 
Institute for ICT of the [Hogeschool Utrecht](https://www.hu.nl/).
By cloning, copying or forking this repository and/or accepting a GitHub Classroom assignment based on this repository, you accept that copyright of any derived work and/or work build upon materials found in this repository, belong and remain to the HU as specified in [HU Copyright regulations (2017)](https://een.hu.nl/over-de-hu/rules-and-regulations-within-the-hu/hu-copyright-enforcement-regulations-pdf).

## How do I get started?

During the start of the course,
we will analyse the required use cases, domain 
and architecture. 

For now, let's see if we can get this project started!

1. Clone the repository and make it your own
1. Start a PostgreSQL instance, either via `docker-compose up` or manually
    - For manual instructions, see below
1. Check if everything works by running the tests, either via your IDE or via `mvnw verify`
1. Follow the instructions in the course assignments

⚠️ Make sure you have at least JDK17 installed

### PostgreSQL without Docker

First, make sure to set up the correct databases and users:

```sql
CREATE USER "cisq1-lingo" WITH CREATEDB PASSWORD 'cisq1-lingo';
CREATE USER "cisq1-lingo-test" WITH CREATEDB PASSWORD 'cisq1-lingo-test';

CREATE DATABASE "cisq1-lingo" OWNER "cisq1-lingo";
CREATE DATABASE "cisq1-lingo-test" OWNER "cisq1-lingo-test";
```

Then, run the import script found at 
[development/db/lingo_words.sql](development/db/lingo_words.sql).
You can import or copy-and-paste these queries into
a PostgreSQL client of your choosing.

## My integration tests work on my machine, but not in the CI pipeline

By default, there is no PostgreSQL database present during continuous integration.
Although it is a less realistic test, 
it suffices to replace it with an in-memory database like H2.
Our starter project already includes configuration for this, but you
need to let Maven know that it should activate the continuous integration profile
in Spring. The command to do this is as follows (quotes added for PowerShell):

```shell
./mvnw verify "-Dspring.profiles.active=ci"
```

### I'd rather use an actual database during CI
This is understandable! Although it might be slower,
it is a more realistic test. Do note that longer run times
might make you run out of 
[GitHub Action minutes](https://docs.github.com/en/actions/learn-github-actions/usage-limits-billing-and-administration#usage-limits).

Have a look at:
* [GitHub Actions Service Containers](https://docs.github.com/en/actions/using-containerized-services/creating-postgresql-service-containers) 
  for infrastructure managed by GitHub Actions
* [Test containers](https://www.testcontainers.org/) 
  for more control over infrastructure during tests
