import sys

def dfs(idx, s):
    global answer
    if idx == 11:
        answer = max(answer, s)
        return
    for i in range(11):
        if not check[i]:
            if not board[idx][i]: continue
            check[i] = True
            dfs(idx + 1, s + board[idx][i])
            check[i] = False

if __name__ == '__main__':
    for _ in range(int(input())):
        board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(11)]
        check = [False] * 11
        answer = 0
        dfs(0, 0)
        print(answer)