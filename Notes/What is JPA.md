# What is JPA? 
   JPA is stands Java Persistence API.
   It is a Java Specification that allows Java application to manage data in relational databases using Object Relational Mapping(ORM).

1. Presentation Layer
2. Business Logic Layer
3. Data Access Layer
4. Java Persistance API(JPA)
5. Hibernate/Eclipse Link
6. JDBC

```xml
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">

    <persistence-unit name="org.hibernate.tutorial.jpa">   
        <description>
            Persistence unit for the Jakarta Persistence tutorial of the Hibernate Getting Started Guide
        </description>

        <class>org.hibernate.tutorial.em.Event</class>     

        <properties>    
            <!-- Database connection settings -->
            <property name="jakarta.persistence.jdbc.url" value="jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1" />
            <property name="jakarta.persistence.jdbc.user" value="sa" />
            <property name="jakarta.persistence.jdbc.password" value="" />

            <!-- Automatically export the schema -->
            <property name="jakarta.persistence.schema-generation.database.action" value="create" />

            <!-- Echo all executed SQL to console -->
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true" />
            <property name="hibernate.highlight_sql" value="true" />
       </properties>

    </persistence-unit>

</persistence>
```
META-INF/peristence.xml