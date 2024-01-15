from flask import Flask, request, jsonify
import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.cluster import KMeans

app = Flask(__name__)

predictedClasses = {0: [], 1: [], 2: [], 3: [], 4: [], 5: [], 6: [], 7: [], 8: [], 9: []};

product_descriptions = pd.read_csv('static/product_descriptions.csv')

product_descriptions = product_descriptions.dropna()
product_descriptions1 = product_descriptions.head(500)

vectorizer = TfidfVectorizer(stop_words='english')
X1 = vectorizer.fit_transform(product_descriptions1["product_description"])

true_k = 10
model = KMeans(n_clusters=true_k, init='k-means++', max_iter=100, n_init=1)
model.fit(X1)

def classify(data):
    for i in data:
        Y = vectorizer.transform([i["name"]])
        predictedClasses[model.predict(Y)[0]].append(i)
    return predictedClasses

@app.route('/')
def home():
    return "Welcome to the Flask App!"

@app.route('/initialise', methods=['POST'])
def initialise():
    data = request.get_json()
    prediction = classify(data)
    return jsonify({'prediction': prediction})

@app.route('/recommend', methods=['POST'])
def recommend():
    data = request.get_json()
    print(data)
    Y = vectorizer.transform([data])
    recommendedProducts = predictedClasses[model.predict(Y)[0]]

    return jsonify({'prediction': recommendedProducts})

@app.route('/recommendByUser', methods=['POST'])
def recommendByUser():
    data = request.get_json()
    recommendedProducts = set()
    for p in data:
        Y = vectorizer.transform([p["productName"]])
        predictions = predictedClasses[model.predict(Y)[0]]
        # Assuming each dictionary has an "id" property
        hashable_predictions = {item["id"] for item in predictions}
        recommendedProducts.update(hashable_predictions)

    uniqueRecommendedProducts = list(recommendedProducts)
    return jsonify({'prediction': uniqueRecommendedProducts})

if __name__ == '__main__':
    app.run(debug=True)
