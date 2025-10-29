
## 🏗 Старт проекта

### 1. Клонирование репозитория

git clone <repo_url>
cd bank_rest


### 2. Настройка окружения через переменные
Приложение использует переменные окружения для конфигурации:

Переменная	                Описание	                        Пример значения
SPRING_SECURITY_USER_NAME	    Имя обычного пользователя	    user
SPRING_SECURITY_USER_PASSWORD	Пароль обычного пользователя	123
JWT_SECRET	                    Секрет для JWT	                demo-secret-key
JWT_EXPIRATION	                Время жизни токена в мс	        3600000
APP_ADMIN_NAME	                Имя администратора	            admin
APP_ADMIN_PASSWORD	            Пароль администратора	        admin
SPRING_DATASOURCE_URL	        JDBC URL базы данных	        jdbc:postgresql://localhost:5432/Bank_DB
SPRING_DATASOURCE_USERNAME	    Имя пользователя БД	            postgres
SPRING_DATASOURCE_PASSWORD	    Пароль БД	                    postgres

### 3. Запуск через Docker Compose
docker-compose up --build

### 4. Документация API
Swagger UI доступен по адресу:
http://localhost:8080/api/swagger-ui/index.html
