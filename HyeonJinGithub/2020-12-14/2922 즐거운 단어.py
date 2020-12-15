# _ 에 들어올 수 있는 글자는 모음, L이 아닌 자음, L 총 3가지 가능 => 3^10 이므로 브루트포스로 해결 가능
# 모음을 사용한 경우는 A 사용
# 자음을 사용한 경우는 B 사용
# L을 사용한 경우는 L 사용

def verify(s):
    mo = ['A', 'E', 'I', 'O', 'U'] # 모음
    ja = ['B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'] # 자음
    if 'L' not in s: return False # L이 없으면 false
    for i in range(len(s) - 2):
        if s[i] in mo and s[i + 1] in mo and s[i + 2] in mo: return False # 모음 3개 연속인 경우 false
        if s[i] in ja and s[i + 1] in ja and s[i + 2] in ja: return False # 자음 3개 연속인 경우 false
    return True

def go(s, idx, ch): # 문자열 s, 인덱스 idx, 사용한 단어 ch
    global ans
    if '_' not in s: # S에 _가 없는 경우
        if verify(s): # 문자열 s 검증
            tmp = 1
            for y in ch: # 사용한 단어 확인
                if y == 'A': tmp *= 5 # 모음을 사용한 경우 5가지
                elif y == 'B': tmp *= 20 # L이 아닌 자음을 사용한 경우 20가지
                else: tmp *= 1 # L을 사용한 경우 1가지
            ans += tmp
        return
    for i in range(idx, len(s)):
        if s[i] == '_':
            s = s[:i] + 'A' + s[i + 1:] # 모음 사용
            go(s, idx + 1, ch + 'A')
            s = s[:i] + 'B' + s[i + 1:] # L이 아닌 자음 사용
            go(s, idx + 1, ch + 'B')
            s = s[:i] + 'L' + s[i + 1:] # L 사용
            go(s, idx + 1, ch + 'L')
            return

if __name__ == '__main__':
    S = input()
    flag = False
    ans = 0
    for i in range(len(S)):
        if S[i] == '_': # S에서 처음으로 _ 발견하면 그때부터 재귀 시작
            flag = True
            go(S, i, '')
            break
    if not flag: ans = 1 # S에서 _가 한개도 없는 경우
    print(ans)