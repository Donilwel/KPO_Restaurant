## Большое домашнее задание
Работу выполнил: ```Маслов Данила Юрьевич```, группа ```БПИ-228```, семинарист ```Данилов Алексей```

Весь функционал приложения, представлен на ```сайте``` http://localhost:9092/swagger-ui/index.html#/.
Обратись обязательно к ```*``` желательно до запуска

Обработка заказа (создание, добавлению блюд в заказ, отмена, оплата) происходит асинхронно.

## Паттерны проектирования которые использовались при создании
``MVC (model view controller)``

```DTO (DTO-классы для передачи данных от пользователя системе)```

```Chain of Responsibility (цепочка фильтров аутентификации в Spring Security)```

```Singleton(единождое создание бинов в Spring-ом и помещение их в контекст)```

```DAO (отдельные интерфейсы для работы с БД)```

## Требования (напротив напишу сделано/не сделано для удобства проверки):

Реализуйте систему аутентификации для двух типов пользователей: посетителей и администраторов. ```сделано```

Администратор может добавлять и удалять блюда из меню, а также устанавливать их количество, цену и сложность выполнения (время, которое оно будет готовиться). ```сделано```

Посетители могут составлять заказ, выбирая блюда из актуального меню. ```сделано```

Заказы обрабатываются в отдельных потоках, симулируя процесс приготовления. ```сделано```

Посетители могут добавлять блюда в существующий заказ, пока он находится в обработке. ```сделано```

Посетители должны иметь возможность отменять заказ до того, как он будет готов. ```сделано```

Система должна отображать актуальный статус заказа (например, "принят", "готовится", "готов"). ```сделано```

По завершению выполнения заказа посетитель должен иметь возможность его оплатить. ```сделано```

Необходимо сохранять состояния программы: меню, сумму выручки, пользователей в системе, а также то, что вы посчитаете нужным. ```сделано```

### ```*```Полезная информация для корректной работы приложения
1. Необходимо запустить ```контейнер с БД.```
2. Как получить токен: токен можно получить при авторизации пользователя.
3. Для доступа к методам ```Feedback Controller```, ```Dish Controller```, ```Statistic Controller```, ```Order Controller```
   нужно в заголовке запроса указать ```токен```.
4. Методы, в пути который есть `````"/admin"`````, могут быть доступны только для администраторов.
5. ```Порт для контейнера приложения: 9092 ```
6. ```Порт для БД: 5432```
