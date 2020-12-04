import sys
from collections import deque
from collections import defaultdict

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def grouping(x, y, group):
    q = deque([(x, y)])
    group[x][y] = group_count
    c = set()

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if board[nx][ny] and group[nx][ny] == -1:
                    group[nx][ny] = group_count
                    q.append((nx, ny))
                if not board[nx][ny]: c.add((x, y))

    return c

def bfs(x, y, g):
    global ans
    q = deque([(x, y)])
    dist = [[0] * N for _ in range(N)]

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if group[nx][ny] == g: continue
                if not dist[nx][ny] and not board[nx][ny]:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                if board[nx][ny] and group[nx][ny] != g:
                    dist[nx][ny] = dist[x][y] + 1
                    if dist[nx][ny] == 2:
                        print(1)
                        exit(0)
                    ans = min(ans, dist[nx][ny] - 1)

if __name__ == '__main__':
    N = int(input())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    group = [[-1] * N for _ in range(N)]
    group_count = 0
    contour = defaultdict(lambda :set())
    ans = sys.maxsize

    for i in range(N):
        for j in range(N):
            if board[i][j] and group[i][j] == -1:
                contour[group_count] = grouping(i, j, group)
                group_count += 1
    for i in range(group_count):
        tmp = deque()
        for t in contour[i]:
            tmp.append(t)
        while tmp:
            tx, ty = tmp.popleft()
            bfs(tx, ty, group[tx][ty])
    print(ans)