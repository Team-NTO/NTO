import sys

def check(a):
    if -(10**9) <= a <= 10**9: return True
    else: return False

if __name__ == '__main__':
    cmd_stack = []
    while True:
        cmd = list(map(str, sys.stdin.readline().split()))
        if cmd[0] == 'QUIT': break
        if cmd[0] == 'NUM':
            cmd_stack.append((cmd[0], cmd[1]))
        elif cmd[0] != 'END':
            cmd_stack.append(cmd[0])
        elif cmd[0] == 'END':
            N = int(input())
            ans = []
            for _ in range(N):
                stack = [int(input())]
                flag = False
                for c in cmd_stack:
                    if type(c) == type(()):
                        if check(int(c[1])):
                            stack.append(int(c[1]))
                        else:
                            ans.append('ERROR')
                            flag = True
                            break
                        continue
                    if c == 'DUP':
                        if stack:
                            stack.append(stack[-1])
                        else:
                            ans.append('ERROR')
                            flag = True
                            break
                    elif c == 'INV':
                        if stack:
                            stack[-1] = -stack[-1]
                        else:
                            ans.append('ERROR')
                            flag = True
                            break
                    elif c == 'POP':
                        if stack:
                            stack.pop()
                        else:
                            ans.append('ERROR')
                            flag = True
                            break
                    elif c == 'SWP':
                        if len(stack) > 1:
                            stack[-1], stack[-2] = stack[-2], stack[-1]
                        else:
                            ans.append('ERROR')
                            flag = True
                            break
                    elif c == 'ADD':
                        if len(stack) > 1:
                            a = stack.pop()
                            b = stack.pop()
                            tmp = a + b
                            if check(tmp):
                                stack.append(tmp)
                            else:
                                ans.append('ERROR')
                                flag = True
                                break
                        else:
                            ans.append('ERROR')
                            flag = True
                            break
                    elif c == 'SUB':
                        if len(stack) > 1:
                            a = stack.pop()
                            b = stack.pop()
                            tmp = b - a
                            if check(tmp):
                                stack.append(tmp)
                            else:
                                ans.append('ERROR')
                                flag = True
                                break
                        else:
                            ans.append('ERROR')
                            flag = True
                            break
                    elif c == 'MUL':
                        if len(stack) > 1:
                            a = stack.pop()
                            b = stack.pop()
                            tmp = a * b
                            if check(tmp):
                                stack.append(tmp)
                            else:
                                ans.append('ERROR')
                                flag = True
                                break
                        else:
                            ans.append('ERROR')
                            flag = True
                            break
                    elif c == 'DIV':
                        if len(stack) > 1:
                            minus = 0
                            a = stack.pop()
                            if a < 0: minus += 1
                            b = stack.pop()
                            if b < 0: minus += 1
                            if not a:
                                ans.append('ERROR')
                                flag = True
                                break
                            tmp = abs(b) // abs(a)
                            if minus == 1:
                                if check(tmp):
                                    stack.append(-tmp)
                                else:
                                    ans.append('ERROR')
                                    flag = True
                                    break
                            else:
                                if check(tmp):
                                    stack.append(tmp)
                                else:
                                    ans.append('ERROR')
                                    flag = True
                                    break
                        else:
                            ans.append('ERROR')
                            break
                    elif c == 'MOD':
                        if len(stack) > 1:
                            a = stack.pop()
                            b = stack.pop()
                            if not a:
                                ans.append('ERROR')
                                flag = True
                                break
                            tmp = abs(b) % abs(a)
                            if b < 0:
                                if check(tmp):
                                    stack.append(-tmp)
                                else:
                                    ans.append('ERROR')
                                    flag = True
                                    break
                            else:
                                if check(tmp):
                                    stack.append(tmp)
                                else:
                                    ans.append('ERROR')
                                    flag = True
                                    break
                        else:
                            ans.append('ERROR')
                            break
                if not flag:
                    if len(stack) > 1 or not stack:
                        ans.append('ERROR')
                    else:
                        if check(stack[0]):
                            ans.append(stack[0])
                        else:
                            ans.append('ERROR')
            cmd_stack = []
            input()
            if ans:
                print('\n'.join(map(str, ans)))
            print()
