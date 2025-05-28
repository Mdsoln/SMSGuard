# 📩 SMS Scam Detection API

A Flask-based REST API that uses an AI model to classify SMS messages as either **scam** or **trustworthy**.

---

## 🚀 Overview

This API takes an SMS message as input and returns whether it is a **scam** or **trust**. It is designed to help detect and prevent SMS-based fraud.

---

## 🛠️ Tech Stack

- **Python 3.7+**
- **Flask**
- **scikit-learn**
- **joblib** or **pickle** (for model serialization)

---

create the .env file in root of project same level as main.py file and provide the detailed reference .env.example in project structure

2. Create and activate a virtual environment (optional but recommended):
    - python -m venv venv
    - source venv/bin/activate  # Windows: venv\Scripts\activate
3. Install dependencies
    - pip install -r requirements.txt

4.  Run the Flask app
    - python main.py:  The API will be available at: http://{HOST}:{PORT}/

5.  API Endpoint
    - POST /api/predict
    -Request
    - URL: http://{HOST}:{PORT}/api/predict

    Method: POST

    Headers: Content-Type: application/json

    Body: { "sms": "mama shikamoo, hujambo mama yangu ile pesa naomba unitumie" }

    Response: { 
                "predict": "scam",
                "sms": "mama shikamoo, hujambo mama yangu ile pesa naomba unitumie"
               }
6.  Prediction Results:
    - predict: The prediction result, either "scam" or "trust"
    - sms: The original message text

7. Test with curl

curl -X POST http://{HOST}:{PORT}/api/predict \
  -H "Content-Type: application/json" \
  -d '{"sms": "mama shikamoo, hujambo mama yangu ile pesa naomba unitumie"}'





