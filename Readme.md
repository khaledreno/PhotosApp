# 🚀 Full-Stack Project (Spring Boot + Angular + PostgreSQL)

Welcome to the **Full-Stack Project**! This repository contains:
- 🏠 **Backend**: Java Spring Boot (Java 23)  
- 🎨 **Frontend**: Angular  
- 👨‍💻 **Database**: PostgreSQL (running in a Docker container)  

## 📌 Project Structure
```
📂 project-root
 ├── 📆 backend      # Spring Boot application
 ├── 📆 frontend     # Angular application
 ├── 📆 database     # Database scripts & Docker setup
 ├── 📝 docker-compose.yml  # PostgreSQL container configuration
 ├── 📝 README.md    # Project documentation
```

### 📂 `database/` Folder
The `database/` folder contains:
- `init.sql`: SQL script for initializing the database schema and tables.
- `docker-entrypoint-initdb.d/`: Folder where additional database initialization scripts can be placed.
- `pg_data/`: Persistent volume for PostgreSQL data storage (created by Docker).

---

## 🚀 Getting Started

### 🛠 1⃣ Prerequisites  
Before running the project, ensure you have the following installed:  
- **[Docker](https://www.docker.com/get-started)**  
- **[Java 23+](https://www.oracle.com/eg/java/technologies/downloads/#java23/)**  
- **[Node.js 18+](https://nodejs.org/)** (For Angular)  
- **[Angular CLI](https://angular.io/cli)** (Install via `npm install -g @angular/cli`)

---

## 🛄 2⃣ Setting Up PostgreSQL (Docker Container)

We use **Docker Compose** to run PostgreSQL in a container.  

1⃣ Open a terminal in the project root.  
2⃣ Run the following command to start the PostgreSQL container:  
```sh
docker-compose up -d
```
3⃣ Check if the container is running:  
```sh
docker ps
```
🎉 PostgreSQL is now running on **port 5432**!  

🔹 **Database Credentials (default):**  
- **Username:** `postgres`  
- **Password:** `1234`  
- **Database:** `khaled`  

If you need to stop the container:  
```sh
docker-compose down
```

---

## 🔥 3⃣ Running the Backend (Spring Boot)

1⃣ Navigate to the **backend** folder:
```sh
cd backend
```
2⃣ Build and run the Spring Boot application:
```sh
./mvnw spring-boot:run   # For Linux/macOS
mvnw.cmd spring-boot:run  # For Windows
```
3⃣ The backend will start on **port 8080**.  
You can test it by opening:  
```
http://localhost:8080
```

---

## 🎨 4⃣ Running the Frontend (Angular)

1⃣ Navigate to the **frontend** folder:
```sh
cd frontend
```
2⃣ Install dependencies:
```sh
npm install
```
3⃣ Start the Angular development server:
```sh
ng serve
```
4⃣ The frontend will be available at:
```
http://localhost:4200
```

---

## 🛠️ 5⃣ Stopping the Entire Setup
To stop everything (backend, frontend, and PostgreSQL), run:
```sh
docker-compose down
```
or manually stop the processes running in separate terminals.

---

## 📌 Environment Variables (Optional)
If needed, update `.env` files or `application.properties` (Spring Boot) to modify configurations.

---

## ❓ Troubleshooting

### 🔹 PostgreSQL is not starting?
- Run `docker logs postgres_container` to check logs.
- Make sure port **5432** is not used by another PostgreSQL instance.

### 🔹 Backend is not connecting to PostgreSQL?
- Update `application.properties` in the backend:
  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
  ```

### 🔹 Angular not loading?
- Ensure the backend is running first.
- Check the Angular API URL in `environment.ts`.

---

## 🏆 Contributors
👤 Khaled Mohamed
💎 kaldreno@gmail.com
🔗 [Your Portfolio/GitHub]  

---

## 📝 License
This project is licensed under the **MIT License**.  

