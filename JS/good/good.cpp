#include <stdio.h>
#include <iostream>
#include <set>
#include <map>
#include <queue>
#include <vector>
#include <string>
#include <math.h>
#include <algorithm>
using namespace std;
 
int N;
long long num[2010];
int Left, Right;
int ans;
 
int main(void)
{
//    freopen("B1253_input.txt", "r", stdin);
    
    cin >> N;
    
    for(int i = 1; i <= N; i++)
    {
        cin >> num[i];
    }
    
    sort(num+1, num+N+1);
    
    for(int i = 1; i <= N; i++)
    {
        long long find = num[i];
        Left = 1;
        Right = N;
        
        // 투포인터 알고리즘 활용 
        while(Left < Right)
        {    
            if(num[Left] + num[Right] == find)
            {
                // find는 서로 다른 두 수의 합이여야됨을 체크 
                if(Left != i && Right != i)
                {
                    ans++;
                    break;    
                }
                else if(Left == i)
                {
                    Left++;
                }
                else if(Right == i)
                {
                    Right--;
                }
            }
            else if(num[Left] + num[Right] < find)
            {
                Left++;
            }
            else
            {
                Right--;
            } 
        }
    }
    
    cout << ans;
    
    return 0;
}