
## Старт проекта

## 1. Скачать с репозитоия

Клоннирование репозитория

```bash
git clone https://github.com/Artem-Vasyurin/Bank_REST
```

## 2. Переменные среды
Чтобы запустить этот проект, вам необходимо создать в корне проекта файл `.env`
и задать в нём переменные окружения.

(для удобства можно взять значения отсюда)
 
`SPRING_SECURITY_USER_NAME = user`

`SPRING_SECURITY_USER_PASSWORD = user`

`JWT_SECRET = super-docker-secret-key-hochu-rabotu!` *Не менее 32 символов*

`JWT_EXPIRATION = 3600000`

`APP_ADMIN_NAME = = admin`

`APP_ADMIN_PASSWORD = admin`

`SPRING_DATASOURCE_URL = jdbc:postgresql://localhost:5432/Bank_DB`

`SPRING_DATASOURCE_USERNAME = postgres`

`SPRING_DATASOURCE_PASSWORD = postgres`

## 3. Запуск через Docker Compose

```bash
docker-compose up --build
```

## 4. Документация API
Swagger UI доступен по адресу:
http://localhost:8080/api/swagger-ui/index.html

## 5. Тестовые данные

Изначально в таблице два пользователя c аналогичными паролями:
- user
- admin

Для получения JWT-токена выполните в Swagger запрос:

#### auth-controller

```http
    POST /auth/login/
```
| Request Body | Тип      | Описание                         |
|:-------------|:---------|:---------------------------------|
| `username`   | `string` | **Обязателно**. Имя пользователя |
| `password`   | `string` | **Обязателно**. Имя пользователя |

После успешного входа в Response body вы получите JWT-токен,
который нужно вставить в поле Authorize (вверху страницы Swagger UI).