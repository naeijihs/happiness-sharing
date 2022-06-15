import pandas as pd
import jieba


data = pd.read_csv("words.txt", encoding="utf-8", engine='python', header=None)


def count(text):
    school = data.iloc[0]
    home = data.iloc[1]
    company = data.iloc[2]
    result = [0, 0, 0]
    words = jieba.lcut(text)
    for i in range(len(words)):
        for j in range(len(school)):
            if words[i] == school[j]:
                result[0] += 1
        for j in range(len(home)):
            if words[i] == home[j]:
                result[1] += 1
        for j in range(len(company)):
            if words[i] == company[j]:
                result[2] += 1
    return result


def is_garbage(text):
    words = jieba.lcut(text)
    garbage = data.iloc[3]
    for i in range(len(words)):
        for j in range(len(garbage)):
            if words[i] == garbage[j]:
                return True
    return False
