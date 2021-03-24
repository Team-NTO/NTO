import sys

if __name__ == '__main__':
    cnt = 0
    dic = dict()
    flower_list = sys.stdin.read().split('\n')
    for flower in flower_list:
        if flower != '':
            if flower not in dic:
                dic[flower] = 1
            else:
                dic[flower] += 1
            cnt += 1
    ans = []
    for k, v in dic.items():
        ans.append((k, '%.4f' % ((v / cnt) * 100)))
    ans.sort()
    for y in ans:
        print(f'{y[0]} {y[1]}')