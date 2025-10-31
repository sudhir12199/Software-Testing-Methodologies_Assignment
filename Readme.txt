📘 BookMyShow Web Application – Testing Project
🧩 Overview

This project involves manual and automation testing of the BookMyShow web application.
The main focus is on testing functionalities such as movie booking, event selection, payment flow, and user login/signup.

The testing code is written in Java and organized under the src/ folder.
The project is executed in Eclipse IDE, using .jar dependencies for automation and reporting.

⚙️ Project Setup Instructions
1. Prerequisites

Before you start, ensure the following tools are installed on your system:

Java JDK 8 or higher

Eclipse IDE for Java Developers

Selenium WebDriver JARs (if automation is included)

TestNG or JUnit JARs (for test execution)

Browser Drivers (e.g., ChromeDriver for Google Chrome)

2. Import Project into Eclipse

Open Eclipse IDE.

Go to File → Import → Existing Projects into Workspace.

Browse to the project folder containing the src directory.

Click Finish.

3. Add External JAR Dependencies

Right-click on the project → Build Path → Configure Build Path.

Go to the Libraries tab → click Add External JARs.

Add the required JAR files (for example):

selenium-server-standalone.jar

testng.jar or junit.jar

apache-poi.jar (if Excel file handling is used)

Any other custom library used in the project.

Click Apply and Close.

4. Run the Tests

Open the test file from the src/ folder (e.g., BookMyShowTest.java).

Right-click the file → Run As → Java Application or Run As → TestNG Test (depending on setup).

The test execution results will be displayed in the Eclipse Console or TestNG results window.

🧠 Folder Structure
BookMyShow_Testing_Project/
│
├── src/
│   ├── main/java/
│   │   └── (Application source files if any)
│   └── test/java/
│       ├── BookMyShowTest.java
│       └── Other test cases
│
├── lib/
│   ├── selenium-server-standalone.jar
│   ├── testng.jar
│   └── apache-poi.jar
│
└── README.md

✅ Features Tested

User Registration and Login

Movie Search and Seat Selection

Event Booking Flow

Payment Gateway Validation

Booking Confirmation and Email Verification

🧾 Reporting

Console Output shows pass/fail results.

If TestNG or Extent Reports are integrated, detailed HTML reports will be generated in the /test-output/ folder.

👩‍💻 Author
