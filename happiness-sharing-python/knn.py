import pandas as pd
import matplotlib.pyplot as plt
import math
import operator


def data_split(train_set, test_set, index):  # 分数据集为训练集和验证集
    data = pd.read_csv("data.txt", encoding="utf-8", engine='python', header=None)
    for i in range(len(data)):
        if index == (i % 5):
            test_set.append(data.iloc[i])
        else:
            train_set.append(data.iloc[i])
    return


def distance_compute(instance1, instance2, dimension):  # 计算实例间距离
    distance = 0
    for i in range(dimension):
        distance += pow((instance1[i] - instance2[i]), 2)
    return math.sqrt(distance)


def knn(train_set, instance, k):
    distances = []
    dimension = len(train_set[0])-1
    for i in range(len(train_set)):
        distance = distance_compute(train_set[i], instance, dimension)
        distances.append([train_set[i], distance])
    distances.sort(key=operator.itemgetter(1))
    neighbors = []
    for i in range(k):
        neighbors.append(distances[i][0])
    list_class = {}
    for i in range(len(neighbors)):
        c = neighbors[i][3]
        if c in list_class:
            list_class[c] += 1
        else:
            list_class[c] = 1
    results = sorted(list_class, key=operator.itemgetter(1), reverse=True)
    return results[0]


def k_select():
    data = pd.read_csv("data.txt", encoding="utf-8", engine='python', header=None)
    t = int(len(data)*0.8)
    err_set = [0] * (t+1)
    for j in range(5):
        train_set = []
        test_set = []
        data_split(train_set, test_set, j)
        for k in range(1, t+1):
            for i in range(len(test_set)):
                test_result = knn(train_set, test_set[i], k)
                if test_result != test_set[i][len(test_set[i])-1]:
                    err_set[k] += 1
    m = 1.0
    for i in range(1, t+1):
        err_set[i] = err_set[i] / float(len(data))
        if err_set[i] < m:
            m = err_set[i]
    for i in range(1, t+1):
        if err_set[i] == m:
            print(i, m)

    fig = plt.figure()
    ax1 = fig.add_subplot(111)
    ax1.plot(err_set)
    ax1.set_xlabel('k')
    ax1.set_ylabel('error_rate')
    plt.show()

    return


