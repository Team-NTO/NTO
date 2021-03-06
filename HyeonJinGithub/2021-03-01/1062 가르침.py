import sys

def solution(idx, cnt):
    global ans
    if cnt == K - 5:
        tmp = 0
        for sentence in s:
            for word in sentence:
                if not check[ord(word) - ord('a')]:
                    break
            else:
                tmp += 1
        ans = max(ans, tmp) if ans else tmp
        return
    for i in range(idx, 26):
        if check[i]: continue
        check[i] = True
        solution(i, cnt + 1)
        check[i] = False

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    s = list(set(map(str, sys.stdin.readline().rstrip())) for _ in range(N))
    if K < 5:
        print(0)
        exit(0)
    if K == 26:
        print(N)
        exit(0)
    check = [False] * 26
    ans = 0
    for i in range(N): s[i] = sorted(s[i])
    for c in ['a', 'n', 't', 'i', 'c']:
        check[ord(c) - ord('a')] = True
    solution(0, 0)
    print(ans)
