# Event Sourcing.

Написать 2 приложения, реализующие функционал асинхронной записи данных в БД. Приложение оперирует классом Person, содержащий данные о людях.

```java
public class Person {
    private Long id;
    private String name;
    private String lastName;
    private String middleName;
    // Getters, Setters, Constructor
}
```

Задача состоит в написании двух приложения, одно из которых содержит реализацию интерфейса взаимодействия с системой, второе &mdash; для взаимодействия с БД.

### Приложение DataProcessor.

Принимает из RabbitMQ сообщения о добавлении/удалении данных, затем выполняет в БД соответствующие запросы.
Необходимо, чтобы сообщения, отправленные первыми, обрабатывались также первыми (чтобы принцип FIFO не нарушался).

### Приложение API.

Приложение содержит реализацию следующего интерфейса:

```java
public interface PersonApi {
    void deletePerson(Long personId);
    void savePerson(Long personId, String firstName, String lastName, String middleName);
    Person findPerson(Long personId);
    List<Person> findAll();
}
```

1. deletePerson генерирует сообщение-команду на удаление персоны с заданным id. Далее это сообщение должно быть обработано соответствующим запросом, выполняя удаление данных. Если данных для определенного personId не найдено - выводить в лог сообщение, что была попытка удаления, но при этом данные не найдены. Exception или другую ошибку не выдавать.
2. savePerson генерирует сообщение-команду на сохранение данных персоны. Обработчик должен проверить, существует ли в БД персона с переданным personId. Если существует &mdash; необходимо выполнить обновление данных (обновить три поля firstName, lastName, middleName). Если не существует &mdash; создать персону с переданным personId.
3. findPerson. Генерирует запрос напрямую в БД и возвращает данные персоны, если персона для данного personId найдена, null в противном случае.
4. findAll. Генерирует запрос напрямую в БД и возвращает данные о ВСЕХ сохраненных в базе персонах.

### Замечания по реализации.

1. Формат сообщений для сохранения и удаления данных разработать самостоятельно.
2. Приложения можно разрабатывать в одном пакете, просто с двумя классами, в которых есть метод main(String[].. args). Тогда запуск каждого приложения &mdash; запуск соответствующего класса:
   * `io.ylab.intensive.lesson04.eventsourcing.api.ApiApp` и
   * `io.ylab.intensive.lesson04.eventsourcing.db.DbApp`
3. В случае возникновения ошибок и исключений &mdash; писать данные об этом в консоль.
4. Контролировать случай, что запрос данных возможен только после обработки всех сообщений не надо никак! Методы поиска должны возвращать то, что есть в БД на текущий момент.
5. Реализация остается на усмотрение студентов, проверка будет заключаться в проверке реализации интерфейса PersonApi.