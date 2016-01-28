-- INSERT INTO `COURCES`.`GROUPS` (`id`, `active`, `name`, `startDate`) VALUES
-- (1, true, 'Proff7', '02.04.2013'),
-- (2, true, 'Proff8', '02.04.2013');
DELETE FROM `TASKS`;
DELETE FROM `USERS`;
DELETE FROM `GROUPS`;
DELETE FROM `TASK_TEMPLATES`;
DELETE FROM `SPRINTS`;

INSERT INTO cources.`USERS`(`LOGIN`,`PASSWORD`,`REGISTER_DATE`,`group_id`)VALUES
('anonymous','pass','02.04.13',null);

INSERT INTO `cources`.`SPRINTS`(`active`,`name`,`sprint_type`)VALUES
(true, 'Weekend1 class', 'IT_CENTRE'),
(true, 'Weekend1 home', 'IT_CENTRE'),
(true, 'Weekend2 class', 'IT_CENTRE'),
(true, 'Weekend2 home', 'IT_CENTRE'),
(true, 'Weekend3 class', 'IT_CENTRE'),
(true, 'Weekend3 home', 'IT_CENTRE'),
(true, 'Weekend4 class', 'IT_CENTRE'),
(true, 'Weekend4 home', 'IT_CENTRE'),
(true, 'Weekend5 class', 'IT_CENTRE'),
(true, 'Weekend5 home', 'IT_CENTRE'),
(true, 'Base Weekend1 class', 'IT_CENTRE'),
(true, 'Base Weekend1 home', 'IT_CENTRE'),
(true, 'variables', 'ANONYMOUS'),
(true, 'conditions', 'ANONYMOUS');

INSERT INTO `cources`.`TASK_TEMPLATES`(`materials`,`TASK_TEMPLATE_ID`, `name`,`taskText`) VALUES
(null,
 (SELECT id FROM `cources`.`SPRINTS` WHERE `name` = 'Weekend2 home'),
 'Многопоточный поиск файла','Написать приложения, выполняющее поиск файла в файловой системе многопоточно'),
(null,
 (SELECT id FROM `cources`.`SPRINTS` WHERE `name` = 'Weekend2 home'),
 'Поиск файла','Написать приложения поиска файла в файловой системе'),
(null,
 (SELECT id FROM `cources`.`SPRINTS` WHERE `name` = 'Weekend2 home'),
 'Улучшеный тест','Добавить в Java Test следующие возможности:
 1. В меню добавить пункт - Статистика, показывающий на какие вопросы какое было дано количество правильных ответов.
 Так же в меню Статистика добавить возможность просмотреть даты и время всех попыток прохождения теста

 Добавить несколько тестов и возможность выбора проходимого теста
 После старта приложения дать пользователю выбор - зайти/зарегистрироваться'),
(null,
 (SELECT id FROM `cources`.`SPRINTS` WHERE `name` = 'Weekend3 class'),
 'Criteria','Создать класс User со следующими полями:

 id
 name
 age
 login
 pass
 date (дата регистрации)

 Создать UserHibernateDaoImpl класс с реализацией CRUD методов и следующих дополнительных методов:
 1. Получение пользователей по имени
 2. Получение пользователей по возрасту в диапазоне
 3. Получение пользователей по дате создания
 4. Получение пользователей по логину и паролю
 5. Проверка слабости пароля (login = pass)'),
(null,
 (SELECT id FROM `cources`.`SPRINTS` WHERE `name` = 'Weekend3 home'),
 'Hibernate dao','Переделать задачу кладовщика с использованием Hibernate.
1. Выводить все продукты до указанной цены.
2. Выводить все продукты в диапазоне цен
3. Выводить все продукты у которых количество больше нуляю'),
(null,
 (SELECT id FROM `cources`.`SPRINTS` WHERE `name` = 'Weekend3 home'),
 'Java Test','Написать приложение проверяющее знание студентов языка Java.
После запуска приложения пользователь проходит аутентификацию.
После удачной аутентификации, пользователь попадает в меню:

1. Начать тест.
2. Посмотреть результат.
3. Выход

При прохождении теста пользователю необходимо показывать тексты вопросов и варианты ответов
В каждом вопросе может быть только один правильный ответ
При правильном ответе пользователь получает 1 бал, за не правильный 0.
На ответ пользователю выделяется одна минута. Если ответ не дан, то ответ считается неправильным
Условие прохождения теста - не менее 60% правильных ответов

Протестировать работоспособность приложения на 2х пользователях.
Сделать конфигурацию через .properties файл:
процент прохождения, балл за правильный ответ, время на ответ
Выводить историю прохождения тестов: дата, ответы, бал.
'),
(null,
 (SELECT id FROM `cources`.`SPRINTS` WHERE `name` = 'Base Weekend1 class'),
 'Hello world','Написать приложения, выводящее в консоль сообщение Hello World!')
;

INSERT INTO `cources`.`TASKS`
(`status`,`taskTemplate_id`,`user_id`,`result`, `sprint_id`)VALUES
('NEW',
	(SELECT id FROM `cources`.`TASK_TEMPLATES` WHERE `name` = 'Simple bean'),
	(SELECT id FROM `cources`.`USERS` WHERE `LOGIN` = 'anonimous'),'0%',
	(SELECT id FROM `cources`.`SPRINTS` WHERE `name` = 'variables')),
('NEW',
	(SELECT id FROM `cources`.`TASK_TEMPLATES` WHERE `name` = 'Simple bean1'),
	(SELECT id FROM `cources`.`USERS` WHERE `LOGIN` = 'anonimous'),'0%',
	(SELECT id FROM `cources`.`SPRINTS` WHERE `name` = 'variables'));
