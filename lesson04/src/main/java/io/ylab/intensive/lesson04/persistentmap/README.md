# Persistent Map.

Необходимо реализовать Map, хранящий свое состояние исключительно в базе данных. То есть, любое изменение данных Map (добавление и удаление), а также получение данных должно транслироваться в соответствующие SQL запросы. Данные необходимо хранить в таблице следующего вида:

```
CREATE TABLE persistent_map (
    map_name varchar,
    key varchar,
    value varchar
);
```

name &mdash; имя экземпляра Map
key &mdash; ключ в экземпляре Map
value &mdash; значение, соответствующее ключу в текущем экземпляре Map

Реализовать интерфейс:

```java
public interface PersistentMap {
    void init(String name);
    boolean containsKey(String key) throws SQLException;
    List<String> getKeys() throws SQLException;
    String get(String key) throws SQLException;
    void remove(String key) throws SQLException;
    void put(String key, String value) throws SQLException;
    void clear() throws SQLException;
}
```

init. Метод используется для инициализации нового экземпляра Map. Принимает имя текущего экземпляра. Данные всех экземпляров хранятся в одной таблице, и имя используется для того, чтобы отделять данные одного экземпляра от данных другого

containsKey. Возвращает true тогда и только тогда, когда существует значение, связанное с данным ключом, false &mdash; в противном случае

getKeys. Возвращает список ключей, для которых есть значения в БД

get. Возвращает значение, связанное с переданным ключом

remove. Удаляет пару ключ/значение из Map

put. Служит для добавления новой пары ключ-значение. В своей работе сначала удаляет существую пару из Map (если она есть), а затем добавляет новую

clear. Удаляет все данные из текущего экземпляра Map

Допущение: можно считать, что одновременно только одно приложение будет работать с конкретным экземпляром. То есть, соблюдение строгой транзакционности и реализация многопоточной работы не обязательны!

Создание таблицы производится отдельно. То есть в код создание таблицы добавлять не нужно!