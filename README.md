# GlobeMed

GlobeMed is a **Java desktop healthcare management application** built using **Java 8 and JavaFX**.  
The project focuses on applying **Object-Oriented Programming principles and software design patterns** to build a maintainable and scalable application for managing hospital operations.

The system provides an intuitive graphical interface to manage patients, hospital staff, and medical records while demonstrating structured application architecture.

---

## 🚀 Features

- Patient registration and management
- Medical staff management
- Appointment scheduling
- Medical record tracking
- User-friendly desktop interface using JavaFX
- Persistent data storage using MySQL
- Modular architecture following design pattern principles

---

## 🏗️ Architecture

The application is designed using **layered architecture principles** to separate responsibilities and improve maintainability.

Typical layers include:

- **Presentation Layer**
  - JavaFX UI
  - Controllers handling user interactions

- **Business Logic Layer**
  - Application services
  - Business rules implementation

- **Data Access Layer**
  - JDBC-based database operations
  - Repository/DAO pattern for data handling

---

## 🧩 Design Patterns Used

This project demonstrates the use of common **Java design patterns** to maintain clean and reusable code.

Examples include:

- **MVC (Model–View–Controller)**  
  Separates UI, logic, and data models.

- **Singleton Pattern**  
  Used for shared resources such as database connection management.

- **Factory Pattern**  
  Used for controlled object creation.

- **DAO (Data Access Object)**  
  Abstracts database interaction logic.

These patterns help ensure the application remains **scalable, maintainable, and testable**.

---

## 🛠️ Technology Stack

| Technology | Purpose |
|------------|--------|
| Java 8 | Core programming language |
| JavaFX | Desktop UI framework |
| MySQL | Database |
| JDBC | Database connectivity |
| Git | Version control |

---

## 📂 Project Structure

```
GlobeMed/
│
├── src/
│   ├── controller/
│   ├── model/
│   ├── dao/
│   ├── service/
│   └── view/
│
├── resources/
├── database/
└── README.md
```

---

## ⚙️ Setup & Installation

### Prerequisites

- Java JDK 8
- MySQL Server
- Git

### Clone Repository

```bash
git clone https://github.com/yourusername/globemed.git
cd globemed
```

### Configure Database

1. Create a MySQL database
2. Import the SQL schema located in the `/database` directory
3. Update database credentials in the configuration file

### Run the Application

Compile and run the application from your IDE or using:

```bash
javac Main.java
java Main
```

---

## 📸 Screenshots

*(Add UI screenshots here for better presentation)*

Example:

```
![Dashboard](screenshots/dashboard.png)
```

---

## 🎯 Learning Objectives

This project was developed to practice:

- Java desktop application development
- JavaFX UI development
- Object-Oriented Design
- Implementation of software design patterns
- Database integration with JDBC
- Clean and modular software architecture

---

## 🔮 Future Improvements

- Role-based authentication system
- REST API integration
- Improved reporting system
- Cloud database integration
- Docker containerization

---

## 👨‍💻 Author

**Ravindu Dasanayaka**

Software Engineering Graduate focused on **Backend Development, Cloud Engineering, and DevOps**.

Technologies: Java | Spring Boot | Docker | Linux | AWS | Kubernetes

---

## 📄 License

This project is open-source and available under the **MIT License**.
