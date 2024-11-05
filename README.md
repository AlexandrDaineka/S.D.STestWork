# S.D.STestWork
Здравствуйте! Тут пошагаво я вам обьясню как запустить этот проект!
Шаг 1: Скачать проект
Шаг 2: подключиться к бд. Обратите внемание что у меня локально постгрес развернут на порте 5433! Если у вас не так поменяйте его
нужно создать бд с названием student_db 
Данные для бд:
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:postgres}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:qwerty}
Шаг 3: запускаем приложение в вашей IDE //обратите внимание что у меня локал хост на порту 8181 у вас может отличаться
Шаг 4: нам нужно получить токен для доступа к эндпоинтам!

В Postman создайте новый запрос для получения access_token.

Метод: POST
URL: http://localhost:8181/oauth2/token //обратите внимание что у меня локал хост на порту 8181 у вас может отличаться
Авторизация: Выберите тип Basic Auth, и укажите:
Username: client
Password: secret
Тело запроса:
В Body, выберите x-www-form-urlencoded и добавьте параметры:
grant_type: client_credentials
После отправки этого запроса вы получите ответ с access_token, который можно будет использовать для доступа к защищенным ресурсам.
Чтобы получить доступ к эндпоинтам в запросе к этому эндпоинту нужно в разделе Authorization выберите Bearer Token и вставьте access_token, полученный на предыдущем шаге.
![image](https://github.com/user-attachments/assets/8918192e-1f4b-4131-889a-8f72b7f0f15b)


Вот как выглядит получение токина в примере:
![image](https://github.com/user-attachments/assets/891e1d4d-14d6-40f3-9f77-21b6ced6a206)

Шаг 5 обращаемся ко всем эндпоинтам. Тут я приведу примеры в постмане ко всем 4 эндпоинтам: //обратите внимание что у меня локал хост на порту 8181 у вас может отличаться
Метод PUT:
пример json 
{
  "lastName": "Ivanov",
  "firstName": "Ivan",
  "middleName": "Ivanovich",
  "studentGroup": "A1",
  "averageGrade": 4.5
}
![image](https://github.com/user-attachments/assets/176d97a3-bf87-4414-a268-5ee358b2e05c)

Метод  DELETE
В хедерах указываем id студента который нам нарвится меньше всего и удаляем его! в ответ получаем статус 204 о том что контент успешно удален!
![image](https://github.com/user-attachments/assets/f6ed7b73-820a-4d3b-a689-b31aa1af4718)

Метод GET:
получаем спесок всех студентов! дополнительно указывать ничего не надо кроме токена авторизации!
![image](https://github.com/user-attachments/assets/d5cebf27-67ad-44f0-a007-8d9a53fad588)

Метод POST:
Изменяем студента
![image](https://github.com/user-attachments/assets/0568b57f-2579-49fb-8b00-e457476d64b8)
после изменения студента можем проверить методом гет действительно ли он изменился!
![image](https://github.com/user-attachments/assets/f873cb38-7d9f-4b24-bd2d-580796e55820)







