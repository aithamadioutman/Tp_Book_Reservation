# 📘 Online Book Borrowing System

### 🧩 Practical Evaluation — Architecture of Information Systems

**Environment:**  
- IDE: Visual Studio Code  
- JDK: 11  
- Build Tool: Maven  
- Server: Apache Tomcat 10  
- Pattern: MVC (Model–View–Controller)

---

## 🧠 Project Overview

This mini web application simulates an **Online Book Borrowing / Reservation System**, built using **Jakarta Servlets**, **JSP/JSTL**, and **JavaBeans**.  

It follows the **MVC architecture**:
- **Model:** JavaBeans (Book, BorrowedList, Library)
- **View:** JSP pages using JSTL
- **Controller:** Servlets (CatalogServlet, BorrowServlet, CheckoutServlet)

---

## 🎯 Objectives

The system allows users to:

- 📚 View a catalog of available books  
- 📖 Reserve (borrow) books  
- 🧾 View the list of borrowed books  
- 🔁 Return borrowed books  
- 💳 Checkout and view total monthly cost  

> **Note:** Data is stored in memory (no database connection).

---

## 🏗️ Architecture Overview

| Layer | Components | Description |
|-------|-------------|--------------|
| **Model** | `Book`, `BorrowedList`, `Library` | Represents business entities and data logic |
| **View** | `index.jsp`, `catalog.jsp`, `borrowed.jsp`, `checkout.jsp` | JSP pages that render data |
| **Controller** | `CatalogServlet`, `BorrowServlet`, `CheckoutServlet` | Handle user requests and business flow |

---

## ⚙️ Maven Configuration

**Group ID:** `fr.univtours.polytech`  
**Artifact ID / Project name:** `tpeval`  

---

## 🌐 URL Mapping

| Page | URL | Description |
|------|-----|-------------|
| Home | `/tpeval/` | Main entry point |
| Catalog | `/tpeval/catalog` | Displays available books |
| Reserve | `/tpeval/reserve` | Handles reservation actions |
| Borrowed | `/tpeval/borrowed` | Displays borrowed books list |
| Return | `/tpeval/return` | Handles returning of books |
| Checkout | `/tpeval/checkout` | Displays summary and clears session |

---

## 💼 Business Logic

- Each **Book** has:
  - `id` (ISBN)
  - `title`
  - `author`
  - `availableCopies`
  - `description`
  - `format` (physical / online)

- **Pricing:**
  - Physical Book: **€10/month**
  - Online Book (PDF): **€5/month**

- **Rules:**
  - Users cannot borrow when `availableCopies == 0`.
  - A user cannot borrow more than **2 books** at once.
  - After checkout confirmation, the session is cleared.

---

## 🧩 Example Data

| ISBN | Title | Author | Available Copies | Format | Description |
|------|--------|--------|------------------|---------|--------------|
| 1234567890 | Java Programming | John Doe | 3 | physical | A guide to Java |
| 0987654321 | Web Development | Jane Smith | 2 | online | Learn web basics |

---

## 🚀 How to Run the Project

1. Open the project in **VS Code** or **Eclipse**  
2. Run `mvn clean package` to build the WAR file  
3. Deploy `tpeval.war` to **Tomcat 10** (`webapps` folder)  
4. Start the Tomcat server  
5. Access [http://localhost:8080/tpeval](http://localhost:8080/tpeval)

---

## 🧾 Exercises Summary

| Exercise | Description | Deliverable |
|-----------|--------------|-------------|
| 1 | Display Book Catalog | `/catalog` |
| 2 | Manage Borrowed Books in Session | Borrow persists |
| 3 | Borrow/Return Servlets | `/borrowed` |
| 4 | Checkout Logic & Summary | `/checkout` |
| 5 | Short Report | PDF with browser screenshots |

---

## 📄 Submission Notes

- Submit project `.zip` including `target/tpeval.war`
- Include a **short report (PDF)** explaining your MVC design + screenshots
- Deadline: **October 26 at 22:00**
- Late submissions or emailed work will not be accepted

---

## 🚫 Academic Integrity

Use of **Generative AI tools** (ChatGPT, Copilot, Gemini, etc.) or code copied from GitHub/external sources is **not authorized**.  
Your implementation must reflect your **own understanding** of the MVC pattern and Servlets/JSPs.

---

## 👨‍💻 Author

**Name:** Outman Aithamadi  
**Institution:** Polytech Tours  
**Course:** Architecture of Information Systems  
**Year:** 2025  
