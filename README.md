# 🛎️ JagoGrahak — Consumer Complaint Management System

A Spring MVC web application that lets consumers register complaints against a company and lets staff pull complaint reports by date range. Built to model a real-world "customer grievance" workflow end to end: validated forms, business-rule enforcement, layered architecture, persistence, and reporting.

> **Jago Grahak** ("Alert Consumer" in Hindi) — a nod to consumer-awareness campaigns, reflected in the app's purpose: give consumers an easy channel to raise and track complaints.

---

## ✨ Features

- 📝 **File a complaint** — customers submit their name, complaint type, date of incidence, description, and monetary loss through a validated web form
- 🚫 **Duplicate prevention** — a customer can't file two complaints of the same type; enforced in the service layer before it ever reaches the database
- ✅ **Server-side validation** — mandatory fields, past-date enforcement, and inline error messages next to the offending field
- 📊 **Complaint reporting** — pull every complaint filed within a chosen date range
- ⚠️ **Centralized exception handling** — any unhandled error routes to a friendly error page instead of a raw stack trace

---

## 🧰 Tech stack

| Layer | Technology |
|---|---|
| Web framework | Spring MVC |
| Persistence | Spring Data JPA + Hibernate |
| Database | MySQL |
| View | JSP + JSTL + Spring form tags |
| Build / IDE | Eclipse Dynamic Web Project (Java 8) |
| Server | Apache Tomcat 9 |

---

## 🏗️ Architecture

```
Browser
   │
   ▼
DispatcherServlet          Spring MVC front controller
   │
   ▼
Controller                 ComplaintRegisterController / ComplaintReportController
   │  validates input, resolves the view
   ▼
Service layer               ComplaintService / ComplaintServiceImpl
   │  business rules — e.g. no duplicate complaint type per customer
   ▼
DAO layer                   Spring Data JPA repositories + a raw EntityManager query
   │  entity ⇄ bean conversion, JPQL
   ▼
MySQL                        jagograhakcomplaintdb
```

The Spring context is deliberately split in two — a **root context** (`cst-root-ctx.xml`) wiring services and DAOs, and a **web context** (`cstspconf-web-servlet.xml`) wiring controllers and the JSP view resolver — so the business layer stays decoupled from the web layer.

---

## 📁 Project structure

```
JagoGrahak/
├── src/com/suraj/lkm/
│   ├── business/bean/       # Web-facing DTOs (ComplaintBean, DateRangeBean, ...)
│   ├── entity/               # JPA entities mapped to MySQL tables
│   ├── dao/                   # Spring Data JPA repositories + EntityManager access
│   ├── service/              # Business logic layer
│   └── web/controller/      # Spring MVC controllers
├── src/META-INF/orm.xml       # Named JPQL queries
├── WebContent/
│   ├── index.jsp              # Landing page
│   └── WEB-INF/
│       ├── jspViews/          # All JSP views (not directly web-accessible)
│       ├── web.xml            # Servlet deployment descriptor
│       └── *.xml              # Spring configuration files
└── test/                      # Unit tests
```

---

## 🚀 Getting started

### Prerequisites
- JDK 8
- Apache Tomcat 9
- MySQL Server
- Eclipse IDE for Enterprise Java (or VS Code with the Java Extension Pack + a Tomcat extension)

### Database setup
```sql
CREATE DATABASE jagograhakcomplaintdb;

CREATE TABLE ComplaintType (
  complaintTypeId INT PRIMARY KEY,
  complaintTypeName VARCHAR(100)
);

INSERT INTO ComplaintType VALUES
  (1, 'Billing Issue'),
  (2, 'Product Defect'),
  (3, 'Service Delay');
```
The `Complaint` table is created automatically by Hibernate on startup (`generateDdl=true`).

Connection settings live in `src/com/suraj/lkm/resources/cst_conn.properties` — update the username/password to match your local MySQL instance.

### Run it
1. Import the project into Eclipse as an existing Dynamic Web Project (or open the folder in VS Code)
2. Point the project's server runtime at your installed Tomcat 9
3. Run the project on the server
4. Open `http://localhost:8080/JagoGrahak/`

---

## 🧭 Usage

| Action | URL |
|---|---|
| Home page | `/` |
| File a complaint | `/loadComplaintPage.html` |
| Submit complaint | `/complaintForm.html` (POST) |
| Complaint report form | `/dateForm.html` |
| View report | `/complaintReportForm.html` (POST) |

---

## 🗺️ Roadmap ideas

- [ ] Add authentication so complaint history is scoped per logged-in customer
- [ ] Enforce the "no duplicate complaint type" rule with a DB-level unique constraint, not just application logic
- [ ] Expose a REST API alongside the JSP views
- [ ] Migrate build to Maven/Gradle for reproducible builds outside Eclipse

---

## 👤 Author

**Suraj Behera**
[GitHub](https://github.com/surajbehera230900)

---

## 📄 License

This project was built for training and educational purposes.
