
## Старт проекта

## 1. Скачать с репозитоия

Клоннирование репозитория

```bash
git clone https://github.com/Artem-Vasyurin/Bank_REST
```


## 2. Переменные среды
Чтобы запустить этот проект, вам необходимо добавить следующие переменные среды в файл .env
 
`SPRING_SECURITY_USER_NAME`

`SPRING_SECURITY_USER_PASSWORD`

`JWT_SECRET`

`JWT_EXPIRATION`

`APP_ADMIN_NAME`

`APP_ADMIN_PASSWORD`

`SPRING_DATASOURCE_URL`

`SPRING_DATASOURCE_USERNAME`

`SPRING_DATASOURCE_PASSWORD`

## 3. Запуск через Docker Compose

```bash
docker-compose up --build
```

## 4. Документация API
Swagger UI доступен по адресу:
http://localhost:8080/api/swagger-ui/index.html
