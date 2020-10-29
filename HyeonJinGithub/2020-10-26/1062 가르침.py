import sys

def dfs(idx, cnt):
    global answer
    if cnt == K - 5:
        tmp = 0
        for word in s:
            for ch in word:
                if not check[ord(ch) - ord('a')]:
                    break
            else:
                tmp += 1
        answer = max(answer, tmp) if answer else tmp
        return
    for i in range(idx, 26):
        if check[i]: continue
        check[i] = True
        dfs(i, cnt + 1)
        check[i] = False


if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    s = [set(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    check = [False] * 26
    answer = 0
    if K < 5:
        print(0)
        exit(0)
    if K == 26:
        print(N)
        exit(0)
    for i in range(len(s)):
        s[i] = sorted(s[i])
    for y in ['a', 't', 'n', 'i', 'c']:
        check[ord(y) - ord('a')] = True
    dfs(0, 0)
    print(answer)