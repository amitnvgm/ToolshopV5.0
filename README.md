# 🚀 ToolshopV5.0 - Test Automation Framework

### **🔹 Overview**
ToolshopV5.0 is a **Selenium WebDriver-based test automation framework** built using **TestNG, Maven, and Docker**. It is designed for **automating the Toolshop web application**, ensuring stability, efficiency, and maintainability in test execution.


📌 Key Features

✅ Selenium WebDriver + TestNG + Maven – Industry-standard test automation stack.

✅ Page Object Model (POM) – Enhances code reusability and maintainability.

✅ Data-Driven Testing – Uses TestNG @DataProvider and external Excel files.

✅ Parallel Execution – Runs tests concurrently across multiple browsers.

✅ Docker & Selenium Grid Integration – Enables remote and scalable test execution.

✅ Jenkins CI/CD Integration – Automated test execution with pipeline support.

✅ Extent Reports – Generates detailed HTML test reports.

✅ Thread-Safe Execution – Uses ThreadLocal<WebDriver> for stable parallel execution.



📌 Tech Stack

Technology	Usage

Selenium WebDriver	UI Automation

TestNG	Test execution & assertions

Maven	Dependency management & build automation

Extent Reports	Generates HTML test reports

Docker + Selenium Grid	Parallel & cross-browser testing

Jenkins	CI/CD pipeline execution



## 📌 Test Cases Implemented

TC001	Valid Login Test |Verify that a user can log in with valid credentials.|	User is successfully logged in and redirected to the dashboard.

TC002	Invalid Login Test|	Verify that a user cannot log in with incorrect credentials.|	User is not logged in and not to the dashboard.

TC003	Search for an Existing Product |	Verify that a product search returns correct results.|	The searched product appears in the search results.

TC004	Search for a Non-Existing Product |	Verify search functionality for a product that does not exist.|	no product dispayed in search reasults

TC005	Add Product to Cart	| Verify that a product can be added to the shopping cart. |	The product appears in the cart with the correct details.

TC006	Remove Product from Cart |	Verify that a user can remove a product from the cart.|	if product added then The product is successfully removed from the cart.

TC007	Successful Checkout	Verify | that a user can complete a checkout process. |	if product added then checkout procedd perform and order confirmation message is displayed.

TC008 Invalid Login Error Message Dispaly Check | verify if user enter invalid credential then error message should displayed | if error message not display then test should fail



Email: akp.154@gmail.com
