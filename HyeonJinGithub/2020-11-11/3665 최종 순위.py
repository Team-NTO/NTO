import sys
from collections import defaultdict
from collections import deque

if __name__ == '__main__':
    for _ in range(int(input())):
        N = int(input())
        in_degree = [0] * (N + 1)
        adj_list = defaultdict(lambda: [])
        score = list(map(int, sys.stdin.readline().split()))
        for i in range(N - 1):
            for j in range(i + 1, N):
                in_degree[score[j]] += 1
                adj_list[score[i]].append(score[j])
        M = int(input())
        for _ in range(M):
            a, b = map(int, sys.stdin.readline().split())
            check = True
            for i in adj_list[a]:
                if i == b:
                    check = False
                    adj_list[a].remove(b)
                    adj_list[b].append(a)
                    in_degree[b] -= 1
                    in_degree[a] += 1
            if check:
                adj_list[b].remove(a)
                adj_list[a].append(b)
                in_degree[a] -= 1
                in_degree[b] += 1
        q = deque()
        for i in range(1, N + 1):
            if in_degree[i] == 0:
                q.append(i)
        res_flag = False
        ans = []
        if not q:
            res_flag = True
        while q:
            if len(q) > 1:
                res_flag = True
                break
            x = q.popleft()
            ans.append(x)
            for y in adj_list[x]:
                in_degree[y] -= 1
                if in_degree[y] == 0:
                    q.append(y)
                elif in_degree[y] < 0:
                    res_flag = True
                    break
        if res_flag or len(ans) < N:
            print('IMPOSSIBLE')
        else:
            print(*ans)