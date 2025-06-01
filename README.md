# SMSGuard: AI-Powered SMS Spam Detection for Mobile Money Users

## Project Aim

**SMSGuard** is an AI-powered solution designed to protect mobile money users in Tanzania — particularly users of **M-Pesa**, **TigoPesa**, and **HaloPesa** — from **SMS-based fraud**.

The system automatically detects and filters **scam messages offline**, before they reach the user, and moves them to a separate **“Scam and Blocked”** section of the native messaging app.

 **Our mission**: Ensure all scam messages are detected offline and routed to the "Scam and Blocked" folder — protecting users, especially in rural areas, who lack internet access or advanced smartphones.

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Mdsoln/SMSGuard.git
```

This includes:

```
  Spring Boot backend
  Python-based AI model
  Sample configuration files
```

### 2. Set Up the AI Model

* Use **PyCharm** or any other Python IDE.
* Navigate to the `ai-model` directory.
* Set up your environment and install dependencies:

```bash
pip install -r requirements.txt
```

* Run the FastAPI server:

```bash
uvicorn main:app --host 0.0.0.0 --port 5000 --reload
```

* Make sure the model is available at:
  `http://localhost:5000/predict`

### 3. ☕ Set Up the Spring Boot Backend

* Open the backend project using **IntelliJ IDEA**.
* Ensure **Java 21** is installed and selected.

Configure your PostgreSQL database connection in `application.yml`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/smsguard
spring.datasource.username=postgres
spring.datasource.password=mdsoln
```

* Run the backend application using the IDE or terminal:

```bash
./mvnw spring-boot:run or click Run in your IDEA
```

### 4. Connect EnvayaSMS to Backend

* Ensure your **Android device running EnvayaSMS** and your **backend server** (AI + Spring Boot) are on the **same local network**.

#### On the EnvayaSMS app:

* Set the **Server URL** to:
  `http://<Your-IP-Address>:8080/incoming-sms/receive`

* Add your device **phone number** (must start with +255...)

* *(Optional)* Adjust the **poll interval** (e.g., every 30 seconds).
  Default is 1 minute.

---

## Future Insights

**SMSGuard is still under active development.** We're working to improve and expand the platform through:

```
 A custom Android app that embeds the full functionality of EnvayaSMS with offline classification and direct routing of scam messages to the 'Scam and Blocked' section.

 Improved spam filtering powered by localized datasets.

 Tools that allow users to report messages, helping refine the AI model.

 Integration with blockchain-based security and best database practices to minimize tampering and data breaches.
```

---
 **Join us on the mission to protect mobile money users across Tanzania and beyond!**
