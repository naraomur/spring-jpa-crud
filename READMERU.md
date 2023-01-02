# Spring-JPA-CRUD
### Базовый шаблон CRUD для управления отделами и сотрудниками с использованием REST API
**Содержание:**
- Введение 
- Требования к установке
- Начало работы 
- Структура проекта
- Модели и БД
- Вызовы API 
- Расширенный вызов 
- Исключения
- Заключение 
- Автор


### Введение
Spring-jpa-crud — это простой проект шаблона, который демонстрирует, как использовать Spring Boot и JPA для создания веб-приложения CRUD (создание,
чтение, обновление, удаление). Проект построен с использованием Maven и использует PostgreSQL для хранения данных.

### Требования к установке
Инструменты и среды: 
- JDK 11 или более поздней версии 
- PostgreSQL (например, PgAdmin) 
- Postman 
- IntelliJ Idea Ultimate Версия

### Начало работы
1. Откройте Git Commander или любое другое окно Commander, которое используется по умолчанию в вашей ОС (cmd в Windows, Terminal в Mac). Все
дальнейшие примеры будут показаны для Windows. Если вы работаете на Mac, вы всегда можете поискать альтернативные команды для Mac в
Интернете.

2. Перейдите в директорий, в котором вы хотите разместить этот проект.
```
C:\Users\ThinkPad>cd desktop
```

3. Напишите следующую команду в коммандной строке следующим образом:
```
C:\Users\ThinkPad\Desktop>git clone https://github.com/naraomur/spring-jpa-crud.git
```
если вы получили следующий ответ, вы готовы к работе!
```
Cloning into 'spring-jpa-crud'...
remote: Enumerating objects: 149, done.
remote: Counting objects: 100% (149/149), done.
remote: Compressing objects: 100% (89/89), done.
Receiving objects:  69% (103/149) 149 (delta 45), reused 136 (delta 32), pack-reused 0
Receiving objects: 100% (149/149), 75.75 KiB | 724.00 KiB/s, done.
Resolving deltas: 100% (45/45), done.

```

### Структура проекта
1.  В вашей среде IDE откройте этот проект, и вы должны увидеть структуру проекта, аналогичную приведенной ниже, и вы можете запустить
приложение с помощью **SpringJpaCrudApplication.java**
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── sprinjpacrud01
│   │   │           ├── controller
│   │   │           ├── dto
│   │   │           ├── model
│   │   │           ├── repository
│   │   │           ├── service
│   │   │           └── SpringJpaCrudApplication.java
│   │   └── resources
│   │       ├── application.properties   
│   │       └── text_bundles...
│   └── test
│       └── java
├── pom.xml
├──.gitignore
├── mvnw
├── mvnw.cmd
└── README.md
├── External Libraries...
...

```

### Модели и БД
- В этом приложении есть две модели: Department(отдел) и Employee(сотрудник). Эти две модели связаны друг с другом как Department,
имеющий много сотрудников, и Employee, принадлежащий только одному отделу.
- Для подключения к базе данных используйте application.properties в файле **main/resources**. Просто замените имя пользователя и
пароль своими для PostgreSql. 
- Создайте базу данных **departments** в вашем инструменте управления Postgres, где будут храниться модели отдела и сотрудника. Нет
необходимости создавать таблицы вручную, только саму базу данных **departments**.
```
##connection to the database
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/departments
spring.datasource.username={ваш логин}
spring.datasource.password={ваш пароль}
# Allows Hibernate to generate SQL optimized for a particular DBMS
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Вызовы API
1. Откройте Postman и введите следующую конечную точку и тело запроса.
- Отправьте запрос POST как добавление нового сотрудника в базу данных, используя ниже приведенный адрес и тело-запрос в JSON:

Адрес:
```
http://localhost:8080/employee
```
Тело-запрос в JSON:
```
   {
        "id": 1,
        "name": "Caroline",
        "position": "Manager"
    }
```
> Вы можете добавить несколько сотрудников, отправив по одному POST-запросу. 

- То же самое для отдела

Адрес:
```
http://localhost:8080/department
```

Тело-запрос в JSON:
```
   {
    "id": 1,
    "name": "Technical"
   }
```
> Вы можете добавить несколько отделов, отправив по одному POST-запросу. 

### Расширенный вызов
В директории **controller** вы можете просмотреть контроллеры и заметить MainController, который использует клиент okHttp для назначения
сотрудника в отдел, просто получив его идентификаторы в теле JSON. В Postman создайте новый запрос POST с адресом и телом JSON
следующим образом:
Адрес:
```
http://localhost:8080/main/post-get
```
Тело-запрос в JSON:
```
   {
    "em_id": 1,
    "dep_id": 1
   }
```
JSON ответ в Postman:
> Несколько сотрудников относятся к техническому отделу, включая новую сотрудницу Кэролайн.
```
{
    "id": 1,
    "name": "Caroline",
    "position": "Manager"
}
{
    "id": 1,
    "name": "Technical",
    "employeeHashSet": [
        {
            "id": 1,
            "name": "Caroline",
            "position": "Manager"
        },
        {
            "id": 11,
            "name": "Jack",
            "position": "Technician"
        },
        {
            "id": 2,
            "name": "Barbara",
            "position": "Manager"
        }
    ]
}
```
### Исключения
В директории **controller** вы можете изучить директорий **exceptions** и посмотреть **ResponseExcepton**, расширенный из класса
ResponseEntityExceptionHandler. Это помогает обрабатывать выброшенные исключения на стороне сервера, такие как NullPointerException,
IllegalArgumentException и т. д. В Postman отправьте GET-запрос сотрудника по идентификатору следующим образом:

> Вместо идентификатора типа Long мы отправляем идентификатор типа String.

```
http://localhost:8080/employee/id?id=r
```
Тело ответа, отправленное классом ResponseExcepton с кодом статуса в Postman:
```
{
    "status": "OK",
    "time": "02-01-2023 03:45:20",
    "msg": "Не удалось преобразовать id=r в требуемый тип число: 1, 2, 3..."
}
```
На стороне сервера вы получаете разрешенное исключение словом **Resolved**, **MethodArgumentTypeMismatchException** что значит исключение ошибочного типа данных.

```
: Resolved [org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long';
```

### Заключение
Предоставленная мной техническая документация описывает структуру и функции проекта spring-jpa-crud, но не включает все
конкретные примеры использования API.
Чтобы использовать API в полной мере, вам потребуется изучить исходный код и опробовать различные адреса и функции, предоставляемые приложением.

Некоторые вещи, которые вы можете попробовать, включают:
- Отправка HTTP-запросов на различные адреса (например, GET http://localhost:8080/employee/id?id=1), чтобы увидеть сотрудника по конкретному идентификатору.
- Изменение модели данных (т. е. класса Employee например), чтобы увидеть, как эти изменения влияют на поведение приложения.
- Настройка конфигурации приложения (например, файла application.properties), чтобы увидеть, как это влияет на поведение приложения.

Надеюсь, это поможет! Дайте мне знать, если у вас есть вопросы.

### Автор

:woman_technologist: @naraomur 
:e-mail: naraomur@gmail.com


