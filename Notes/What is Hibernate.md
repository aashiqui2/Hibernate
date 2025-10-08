- Hibernate is powerful anbd widely used opensource java based Object-Relational Mapping(ORM) framework.

- It simplifies the integration between java application and relational databases by mapping java objects to databases tables and vice versa.

- It provides easy to use methods for CRUD operations,helping developer to get rid of messy SQL.

- It has a strong query language which is called Hibernate Query Language(HQL) and supports native SQL as well.

- It supports caching of data making application faster.

- It implements JPA Specifications

# What is JPA ?

JPA stands for Java Persistance API, and its a java specification for managing relational data in application using java

# Hibernate Architecture

1. Presentation Layer
2. Business Logic Layer
3. Data Access Layer
4. Java Persistence API
5. Hibernate
6. JDBC

# Hibernate Mapping Technique

## Configure Hibernate

  <dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate.core</artifactId>
    <version>${hibernate.version}</version>
  </dependency>

## Hibernate Mapping

```java
public class Owner{
    private int id;
    private String firstName;
    private Gender gender;
    private LocalDate petBirthDate;
}
Owner.java
```

```xml
<hibernate-mappping package="com.aashqui.entity">
   <class name="Onwer" table="owner_table">
      <id name="id" type="int" column="id"/>
      <property name="firstName" type="java.lang.String" column="first_name" not-null="true"/>
      <property name="gender" column="gender" not-null="true">
         <type name="org.hibernate.type.EnumType">
            <param name="enumClass">com.aashiqui.enums.Gender</param>
         </type>
       </property>
       <property name="petBirthDate" type="java.time.LocalDate" column="pet_date_of_birth" not-null="true"/>
     </class>

</hibernate-mapping>
Owner.hbm.xml
```

```java
@Entity
@Table(name = "owner_table")
public class Owner {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;
	@Column(name = "mobile_number", nullable = false, unique = true, length = 10)
	private String mobileNumber;
	@Column(name = "email_id", nullable = false, unique = true)
	private String emailId;
	private Pet pet;
}
```

# Configuration Techniques

```xml
<hibernate-configuration>
    <session-factory>

        <!-- Database Connection Settings -->
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/notetaker</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">aashiqui</property>

        <!-- Hibernate Settings -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>

        <!-- Mapped Entity Classes -->
        <mapping class="com.notetaker.entities.Note"/>

    </session-factory>
</hibernate-configuration>
hibernate.cfg.xml
```

```java
public class DatabaseConfig {
      public static SessionFactory getSessionFactory() {
             Configuration configuration = new Configuration().Configure().addAnnotatedClass(Owner.class);
      }
}
DatabaseConfig.java
```

```java
public class DatabaseConfig {
      public static SessionFactory getSessionFactory() {
             Configuration configuration = new Configuration().
            .applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
            .applySetting("hibernate.connection.url", "jdbc:mysql://localhost:3306/notetaker")
            .applySetting("hibernate.connection.username", "root")
            .applySetting("hibernate.connection.password", "aashiqui")
            .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
            .applySetting("hibernate.hbm2ddl.auto", "update")  
            .applySetting("hibernate.show_sql", "true")
            .addAnnotatedClass(Owner.class);
      }
}
DatabaseConfig.java
```

```java
public class DatabaseConfig {
      public static SessionFactory getSessionFactory() {
             Configuration configuration = new Configuration()
             .addAnnotatedClass(Owner.class);
      }
}
DatabaseConfig.java
```
```java
hibernate.connection.url = jdbc:mysql://localhost:3307/petistaan
hibernate.connection.username = root
hibernate.connection.password = root
hibernate.hbm2ddl.auto = update
hibernate.show_sql = true
hibernate.cache.use_query_cache = true
```

hibernate.properties

Configuration
     |
     | (collects entity classes + settings)
     v
ServiceRegistry (built from settings)
     |
     v
SessionFactory (heavy, singleton)
     |
     v
Session (lightweight, per request/transaction)
     |
     v
Database (CRUD operations)


