# Movie Database.

Скачать файл [https://perso.telecom-paristech.fr/eagan/class/igr204/data/film.csv](https://perso.telecom-paristech.fr/eagan/class/igr204/data/film.csv)

В файле содержать данные о фильмах

Необходимо:

1. Реализовать код, читающий данные из файла и записывающий в таблицу через JDBC. Для добавления данных использовать PreparedStatement. В работе необходимо использовать следующий класс:

   ```java
   class Movie {
       private Integer year;
       private Integer length;
       private String title;
       private String subject;
       private String actors;
       private String actress;
       private String director;
       private Integer popularity;
       private Boolean awards;
   }
   ```

   Данные, считываемые из файла должны быть упакованы в экземпляр указанного класса. Затем этот экземпляр должен передаваться коду, который будет отвечать за сохранение данных в БД.

   Обратить внимание, что в файле некоторые значения могут отсутствовать. В таком случает надо вызывать `preparedStatement.setNull(<index>, java.sql.Types.<тип>)`.

   Создание таблицы уже добавлено в проект, реализацию необходимо добавить в класс `io.ylab.intensive.lesson04.movie.MovieLoaderImpl`.

2. Для созданной таблицы написать запрос, выводящий количество фильмов каждого жанра (GROUP BY). Запрос написать в комментариях к коду решения.

|     | subject         | count |
|:---:|:----------------|------:|
|  1  | Crime           |     1 |
|  2  | Westerns        |     6 |
|  3  | Romance         |     1 |
|  4  | Mystery         |   107 |
|  5  | Music           |    43 |
|  6  | Fantasy         |     1 |
|  7  | Short           |     1 |
|  8  | Science Fiction |    38 |
|  9  | Drama           |   655 |
| 10  | Horror          |    57 |
| 11  |                 |     2 |
| 12  | Action          |   205 |
| 13  | Western         |   119 |
| 14  | Comedy          |   385 |
| 15  | Adventure       |     4 |
| 16  | War             |    34 |
