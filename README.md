# disentangle
[![Build Status](https://travis-ci.org/scalderwood/disentangle.svg?branch=master)](https://travis-ci.org/scalderwood/disentangle)
This library provides support for writing tests for a lambda-based pattern that reduces coupling between layers (e.g., service layer and DAO layer). For example, a common pattern is the following: 
```java
public class Service {
  private final Dao dao;
  public Service(Dao dao) {
    this.dao = dao;
  }
}
```
Our service layer is now tightly coupled to the DAO layer. Furthermore, this lends itself to developers using code-completion driven development. With lambdas we can reduce the coupling and force developers to think about what they need the DAO to do.
```java
public class Service {
  private final Function<Long, Optional<Item>> findById;
  public Service(
      final Function<Long, Optional<Item>> findById) {
    this.findById = findById;
  }
}
@Configuration
public class Config {
  @Bean
  public Service service(final Dao dao) {
    return new Service(id -> dao.findById(id));
  }
}
```
The service layer does not directly depend on the DAO and only knows about the functionality it needs.
Now in our test, instead of mocking the DAO, we just use a HashMap:
```
Service service = new Service(id -> Optional.ofNullable(repository.get(id)));
```
There are several methods that are common, such as `findById` and `save`, and instead of having to create them for each test class, this project provides pre-defined so that you can avoid boilerplate and write your tests faster:
```
Service service = new Service(Disentangle.findById(dao));
```
For an example of how to use this, see [Disentangle Demo](https://github.com/scalderwood/disentangle-demo).
