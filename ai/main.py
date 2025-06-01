from flask import Flask, request
from flask_cors import CORS
import joblib
from dotenv import load_dotenv
import os

load_dotenv()

PORT = os.getenv('PORT')
HOST = os.getenv('HOST')

app = Flask(__name__)
model = joblib.load('scam_sms_predict_model.pkl')
CORS(app)


@app.route('/predict', methods=['POST'])
def predict_scam_sms():
    if request.is_json:
        sms = request.json.get('sms')
    else:
        sms = request.form.get('sms')

    input_sms = sms
    prediction = model.predict([input_sms])
    if prediction == 0:
        return {
            "prediction": "trust",
            "sms": input_sms
        }
    else:
        return {
            "prediction": "scam",
            "sms": input_sms
        }


if __name__ == '__main__':
    app.run(host=HOST, port=PORT, debug=False)
