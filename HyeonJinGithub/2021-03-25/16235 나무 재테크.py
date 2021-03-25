import sys
from collections import defaultdict

dx = [-1, -1, -1, 0, 0, 1, 1, 1]
dy = [-1, 0, 1, -1, 1, -1, 0, 1]

if __name__ == '__main__':
    N, M, K = map(int, sys.stdin.readline().split())
    A = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    trees = defaultdict(lambda :[])
    board = [[5] * N for _ in range(N)]
    for _ in range(M):
        x, y, z = map(int, sys.stdin.readline().split())
        trees[(x - 1, y - 1)].append(z)
    while K > 0:
        tmp_trees = defaultdict(lambda :[])
        # 봄
        for k in trees.keys():
            tmp = 0
            if not trees[k]: continue
            while trees[k]:
                min_age = trees[k].pop()
                if min_age > board[k[0]][k[1]]:
                    tmp += (min_age // 2)
                    continue
                tmp_trees[k].append(min_age + 1)
                board[k[0]][k[1]] -= min_age
            # 여름
            board[k[0]][k[1]] += tmp
        # 가을
        for k in tmp_trees.keys():
            cnt = 0
            for y in tmp_trees[k]:
                if y >= 5 and not y % 5:
                    cnt += 1
            if len(tmp_trees[k]) > 0:
                trees[k].extend(tmp_trees[k])
                if cnt > 0:
                    x, y = k[0], k[1]
                    for d in range(8):
                        nx, ny = x + dx[d], y + dy[d]
                        if 0 <= nx < N and 0 <= ny < N:
                            trees[(nx, ny)].extend([1] * cnt)
                trees[k].reverse()
        # 겨울
        for i in range(N):
            for j in range(N):
                board[i][j] += A[i][j]
        K -= 1
    ans = 0
    for k in trees.keys():
        if len(trees[k]) > 0:
            ans += len(trees[k])
    print(ans)
