# API Documentation

## 1. Загальна інформація
**Опис проєкту:**
Цей бекенд забезпечує функціональність для платформи тестування. Він дозволяє:
- Керувати користувачами (реєстрація, логін, перевірка ролей).
- Створювати, отримувати, і переглядати тести.
- Працювати з категоріями тестів.

**Технології:**
- Java
- Spring Boot
- PostgreSQL (зберігання даних через Supabase)
- Maven

**URL серверу:**
- Локальна версія: `http://localhost:8080`
- В хмарі: (вставте ваш домен)

**Версія API:**
- Поточна версія: `/api/v1`

---

## 2. Авторизація та аутентифікація
**Опис механізму:**
- Авторизація заснована на ролях (адміністратор/користувач).
- Вхід користувача виконується через логін і пароль.

**Як отримати токен:**
- Запити на логін (`POST /api/users/login`).

**Приклад заголовка запиту:**

Authorization: Bearer <токен>


---

## 3. Опис ресурсів API

**Ресурси: Користувачі**

- **POST /api/users/register**
    - Опис: Реєстрація нового користувача.
    - Тіло запиту:
      ```json
      {
        "login": "user_login",
        "fullName": "John Doe",
        "password": "secure_password",
        "isAdmin": false
      }
      ```
    - Відповідь:
      ```json
      {
        "message": "User registered successfully"
      }
      ```
    - Статуси: 200, 400.

- **POST /api/users/login**
    - Опис: Логін користувача.
    - Тіло запиту:
      ```json
      {
        "login": "user_login",
        "password": "secure_password"
      }
      ```
    - Відповідь:
      ```json
      {
        "message": "Login successful"
      }
      ```
    - Статуси: 200, 401.

- **POST /api/users/getUserInfo**
    - Опис: Отримання інформації про користувача.
    - Тіло запиту:
      ```json
      {
        "login": "user_login"
      }
      ```
    - Відповідь:
      ```json
      {
        "fullName": "John Doe",
        "isAdmin": true,
        "password": "secure_password"
      }
      ```
    - Статуси: 200, 404.

**Ресурси: Категорії**

- **GET /api/categories/list**
    - Опис: Отримання списку категорій.
    - Відповідь:
      ```json
      [
        "java",
        "python",
        "math",
        "biology"
      ]
      ```
    - Статуси: 200, 404.

**Ресурси: Тести**

- **POST /api/tests/create**
    - Опис: Створення нового тесту.
    - Тіло запиту:
      ```json
      {
        "category": "java",
        "testName": "java_basics",
        "author": "admin_user",
        "data": {
          "questions": [
            ["@", "What is the age of the Earth?"],
            ["#", "Who was Caesar?"]
          ],
          "options": [
            [["1 year", "555 billion years"]],
            [["Philanthropist", "Playboy"]]
          ],
          "correctAnswers": [
            [["555 billion years"]],
            [["Philanthropist"]]
          ]
        }
      }
      ```
    - Відповідь:
      ```json
      {
        "message": "Test created successfully"
      }
      ```
    - Статуси: 200, 400.

- **POST /api/tests/getByAuthor**
    - Опис: Отримання тестів за автором.
    - Тіло запиту:
      ```json
      {
        "author": "admin_user"
      }
      ```
    - Відповідь:
      ```json
      [
        "java_basics",
        "advanced_java"
      ]
      ```
    - Статуси: 200, 404.

- **POST /api/tests/getByCategory**
    - Опис: Отримання тестів за категорією.
    - Тіло запиту:
      ```json
      {
        "category": "java"
      }
      ```
    - Відповідь:
      ```json
      [
        "java_basics",
        "advanced_java"
      ]
      ```
    - Статуси: 200, 404.

---

## 4. Приклади запитів
**Реєстрація користувача:**
```bash
curl -X POST http://localhost:8080/api/users/register \
-H "Content-Type: application/json" \
-d '{
  "login": "new_user",
  "fullName": "Jane Doe",
  "password": "password123",
  "isAdmin": false
}'

Отримання тестів за категорією:

curl -X POST http://localhost:8080/api/tests/getByCategory \
-H "Content-Type: application/json" \
-d '{
  "category": "java"
}'

5. Коди помилок
404 Not Found: Ресурс не знайдено (наприклад, категорія, автор, або тест).
400 Bad Request: Некоректний запит (наприклад, дубль тесту, невірна категорія).
401 Unauthorized: Немає доступу до ресурсу.
6. FAQ
Q: Що робити, якщо сервер повертає 404?
A: Перевірте, чи існує ресурс, до якого ви намагаєтесь звернутися.

Q: Як перевірити, чи я адміністратор?
A: Використовуйте POST /api/users/getUserInfo і перевірте поле isAdmin.