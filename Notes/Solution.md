# Solution : OBJECT RELATIONAL MAPPING

* ORM resolves the Object Relational Impedence mismatch.
* Handles the Lower-Level interaction with the database.
* Helps the developer to get rid of messy SQL.
* Allows the developer to concentrate on business logic.
* ORM is a database independent.
* The are many popular like Hibernate,EclipseLink,MyBaits.etc.

# 🔹JPA (Java Persistence API)
* JPA is a specification (a set of rules, guidelines, and interfaces).
* It defines how Java objects should be persisted to relational databases.
* It does not provide the actual implementation.
Example:
    JPA says: “To mark a class as an entity, use @Entity.”But it doesn’t say how that class will be persisted.

* 👉 Think of JPA as an interface in Java — it defines the contract.

# 🔹ORM (Object–Relational Mapping)
* ORM is a concept / technique.
* It is the practice of mapping Java objects (OOP world) to database tables (RDBMS world).
* ORM tools/frameworks do this mapping automatically.
* 👉 Without ORM, you would manually write INSERT, UPDATE, JOIN queries in JDBC. With ORM, you just deal with objects, and the ORM tool handles SQL generation.
