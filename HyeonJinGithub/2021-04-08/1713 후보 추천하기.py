import sys
from collections import defaultdict

if __name__ == '__main__':
    N = int(input())
    M = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    q = []
    picture_dic = defaultdict(lambda :0)
    picture_set = set()
    now = 0
    for picture in arr:
        flag = False
        for time, P in q:
            if P == picture:
                picture_dic[picture] += 1
                flag = True
                break
        if flag: continue
        if len(q) < N:
            picture_dic[picture] += 1
            q.append((now, picture))
            picture_set.add(picture)
            now += 1
            continue
        mn_N = sys.maxsize
        tmp = []
        for time, P in q:
            if mn_N > picture_dic[P]:
                tmp = []
                mn_N = picture_dic[P]
                tmp.append((time, P))
            elif mn_N == picture_dic[P]:
                tmp.append((time, P))
        tmp.sort()
        time, P = tmp[0]
        q.remove(tmp[0])
        picture_dic[P] = 0
        q.append((now, picture))
        picture_dic[picture] += 1
        now += 1
    ans = []
    for picture in q:
        ans.append(picture[1])
    ans.sort()
    print(' '.join(map(str, ans)))

