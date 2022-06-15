import pandas as pd
import knn
import text_process
import json
import requests
from flask_cors import CORS
from flask import Flask, request


app = Flask(import_name=__name__)
CORS(app, supports_credentials=True)


@app.route('/add_share', methods=["POST"])
def add_share():
    share = json.loads(request.data)  # 将json字符串转为dict
    text = share["title"]+share["text"]
    if text_process.is_garbage(text):
        return "您的分享含违禁词汇，分享失败"
    else:
        data = pd.read_csv("data.txt", encoding="utf-8", engine='python', header=None)
        train_set = []
        for i in range(len(data)):
            train_set.append(data.iloc[i])
        category = knn.knn(train_set, text_process.count(text), 2)
        share["category"] = category
        headers = {
            "Host": "127.0.0.1:8080",
            "Origin": "http://127.0.0.1:5000",
            "Content-Type": "application/json",
            "Accept-Language": "zh-CN, zh-Hans;q=0.9",
            "Accept-Encoding": "gzip, deflate",
            "Connection": "keep-alive",
            "Accept": "application/json, text/plain, */*",
            "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15(KHTML, "
                            "like Gecko) Version/15.3 Safari/605.1.15",
            "Authorization": request.headers["Authorization"],
            "Referer": "http://127.0.0.1:5000/",
            "Content-Length": "24"
        }
        requests.post("http://127.0.0.1:8080/sharer/share/add", data=json.dumps(share), headers=headers)
        return "分享成功，类别为：" + category


if __name__ == '__main__':
    app.run(debug=True)
