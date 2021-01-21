import sys
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def bfs(x, y, color):
    global flag
    tmp_q = deque([(x, y)])
    l = deque([(x, y)])
    check = [[False] * 6 for _ in range(12)]
    check[x][y] = True
    while tmp_q:
        x, y = tmp_q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < 12 and 0 <= ny < 6:
                if board[nx][ny] == color and not check[nx][ny]:
                    check[nx][ny] = True
                    tmp_q.append((nx, ny))
                    l.append((nx, ny))
    if len(l) >= 4:
        while l:
            x, y = l.popleft()
            board[x][y] = '.'
        flag = True

def move_down():
    i, j = 11, 5
    while j >= 0:
        cnt = 0
        while i >= 0:
            if board[i][j] == '.':
                cnt += 1
                i -= 1
                continue
            if not cnt:
                i -= 1
                continue
            board[i + cnt][j] = board[i][j]
            board[i][j] = '.'
            i += cnt
            cnt = 0
        j -= 1
        i = 11

if __name__ == '__main__':
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(12)]
    q = deque()
    ans = 0
    for i in range(12):
        for j in range(6):
            if board[i][j] == '.': continue
            q.append((i, j))
    while q:
        size = len(q)
        flag = False
        for _ in range(size):
            x, y = q.popleft()
            if board[x][y] == '.': continue
            color = board[x][y]
            bfs(x, y, color)
        if flag: ans += 1
        else:
            print(ans)
            exit(0)
        move_down()
        for i in range(12):
            for j in range(6):
                if board[i][j] == '.': continue
                q.append((i, j))
    print(ans)