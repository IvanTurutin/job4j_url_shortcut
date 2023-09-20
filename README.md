# job4j_url_shortcut

### Описание проекта:
Приложение представляет собой сервис по подмене URL ссылок.
Сервис работает через REST API. 

### Применяемые технологии: 
- Spring boot 2.7.14;
- Hibernate 5.6.11;
- PostgreSql 12;
- Lombok 1.18.22
- Liquibase 4.15.0;
- H2 2.1.214.

### Требования к окружению: 
- Java 17;
- Maven 3.8;
- PostgreSQL 12.

### Запуск проекта:
- Настроить окружение и подключение к серверу БД.
- Создать базу данных, например через утилиту psql:
``` 
create database url_shortcut
``` 
- Упаковать проект в jar архив. Для этого выполнить:
``` 
mvn package
```
- Запустить приложение командой:
```
java -jar url_shortcut-0.0.1-SNAPSHOT.jar
```

### Взаимодействие с приложением:


---

### Контакты
email: [ivan.turutin@gmail.com](mailto:ivan.turutin@gmail.com)