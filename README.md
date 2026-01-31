
# UI Automation Framework

## 📌 Overview
The project was created to practice test automation skills using the Selenium (Java) framework, utilizing skills learned in a course on the Udemy platform. The website under test is a website specifically designed for automated testing. The project contains a total of 26 automated tests, created based on test cases defined by the site's developers. After running and executing the tests, using the proper command, a test report can be generated, which describes in detail the activities performed and the statuses of each test.

<img width="1851" height="957" alt="image" src="https://github.com/user-attachments/assets/04e133b0-7082-4958-b54f-a437db1b5cd2" />


The framework is built to:
- Automate functional UI test cases.
- Support modular and reusable test design.
- Integrate with Jenkins and Docker for CI/CD.
- Provide easy configuration and extension for new test scenarios.

---

## 🛠️ Technologies Used
- **Programming Language**: Java  
- **Build Tool**: Maven  
- **Testing Framework**: TestNG  
- **Automation Tool**: Selenium WebDriver  
- **CI/CD**: Jenkins (via `Jenkinsfile`)  
- **Containerization**: Docker (`docker-compose.yaml`)  
- **Version Control**: Git & GitHub  

---

## 📂 Project Structure
```
UI_Automation_Framework/
│── src/test/           # Test classes and UI test cases
│── pom.xml             # Maven dependencies and build configuration
│── testing.xml         # TestNG suite configuration
│── Jenkinsfile         # CI/CD pipeline definition
│── docker-compose.yaml # Docker setup for test execution
│── .gitignore          # Ignored files
│── README.md           # Project documentation
```

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/pratiksawant1496/UI_Automation_Framework.git
cd UI_Automation_Framework
```

### 2. Install Dependencies
Ensure you have **Java (JDK 8+)** and **Maven** installed. Then run:
```bash
mvn clean install
```

### 3. Run Tests
Execute the TestNG suite:
```bash
mvn test -DsuiteXmlFile=testing.xml
```

---

## ⚙️ Configuration
- **testing.xml**: Defines which test classes to run.  
- **pom.xml**: Contains dependencies (Selenium, TestNG, etc.).  
- **docker-compose.yaml**: Enables containerized test execution.  
- **Jenkinsfile**: Automates test runs in CI/CD pipelines.  

---

## 📊 CI/CD Integration
- **Jenkins**: Pipeline defined in `Jenkinsfile` for automated builds and test execution.  
- **Docker**: Supports containerized test environments for consistent execution.  



✅ This README highlights **technologies used** and makes your framework look professional and ready for collaboration.  

Would you like me to also add a **sample Selenium test case snippet** (like a login test) to the README so new users can quickly see how to write tests in your framework?
