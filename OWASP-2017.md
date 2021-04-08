# Vulnerability analysis
A report of the dependencies can be found after executing ```mvnw verify``` under ```/target/dependency-check-report```.

## A1:2017-Injection

### Description

Injection is the possibility for users to insert SQL that exposes more information than should be visible to that user.

#### Example

When a backend uses custom SQL like this:

```java
String query = "SELECT * FROM game WHERE id = '" + request.getParameter("id") + "'";
```

A user could cheat the system by entering ```' OR '1'='1```, which changes the statement to return everything in that
table in the database.

### Risk

This vulnerability is one that is very present in this project, since getting games from the database is a big part of
playing the game. A user could easily enter SQL breaking queries in the requests

#### Authentication

The risk could decrease with the addition of authentication, you could link up games to specific users and make sure
that games that users request are only games that user created and no more.

### Counter measures

In this project this risk is prevented by not accepting strings as id's. Since every id is a long, that's what is
expected on the input, and any other things would just throw an error. For inputs that are strings, there are strict
size checks.

---

## A5:2017-Broken Access Control

### Description

Broken access control means that a user can access account information, or other user specific item by changing their 
account name in the request to someone else's. This happens if the system does not check if the user is who they say
they are.

#### - Example

If a request to the server would be like this:
```
https://www.example.com/account?acct=...
```
A user could simply fill out another username and get that account's information.

### Risk

For this project it isn't a risk, it's a possibility. Since there is no authentication users can just type in a
different game id and see that game.

#### Authentication

With added authentication you could, just like with injection, force games to be connected with users, so only the user
that owns/started the game can access it.

### Counter measures

There are basically no counter measures taken in this project, so every game is available to everyone. A possible
countermeasure is authentication.

---

## A9:2017-Using Components with Known Vulnerabilities

### Description

An application uses many dependencies, but since those execute with (mostly) application level privileges, 
vulnerabilities in one dependency could cause the entire application to be vulnerable.

### Risk

This project uses a lot of dependencies, like Spring, which in and of itself already depends on a lot of other things,
such as Hibernate. This poses quite the risk/chance for one of these dependencies to be vulnerable.

#### Authentication

Authentication does not really affect this vulnerability, since it's not part of the user -> server interaction in the
way the other ones are.

### Counter measures

In order to prevent vulnerable dependencies from being in this project, Dependabot has been added to this project, 
alongside OWASP dependency check to ensure that the used dependencies have no vulnerabilities and are always up to date.
