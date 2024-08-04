# TODO список - чтобы учесть все дела
## Стек технологий: 
- Spring boot 2.7.6, 
- Thymeleaf, 
- Bootstrap, 
- Hibernate 5.6.11, 
- PostgreSql @14,
- log4j 1.2.17

## Требования к окружению
- Java 17
- Maven 3.8
- PostgresSQL 14

## Запуск приложения 
Для инициализации проекта необходимо создать бд:
- create database todo; 
и запустить проект:
- mvn spring-boot:run

В приложении есть главная страница, где можно просмотреть список всех дел, 
также есть вкладки с выполненными и теми задачами, к которым не удалось приступить.

При переходе по ссылке в списке можно посмотреть описание или отметить выполненным. 
Если задачу нужно отредактировать - при нажатии на кнопку "редактировать" появится отдельная форма, 
чтобы внести изменения.

![1](https://github.com/Kemochka/job4j_todo/blob/master/img/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202024-08-03%20%D0%B2%2018.33.22.png)
![2](https://github.com/Kemochka/job4j_todo/blob/master/img/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202024-08-03%20%D0%B2%2018.33.32.png)
![3](https://github.com/Kemochka/job4j_todo/blob/master/img/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202024-08-03%20%D0%B2%2018.33.39.png)
![4](https://github.com/Kemochka/job4j_todo/blob/master/img/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202024-08-03%20%D0%B2%2018.33.47.png)
![5](https://github.com/Kemochka/job4j_todo/blob/master/img/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202024-08-03%20%D0%B2%2018.34.42.png)
![6](https://github.com/Kemochka/job4j_todo/blob/master/img/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202024-08-03%20%D0%B2%2018.34.49.png)




