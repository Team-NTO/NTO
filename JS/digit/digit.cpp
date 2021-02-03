#include <iostream>

using namespace std;

int T, n;
int num[9] = {0, 0, 1, 7, 4, 2, 0, 8, 10};
long long Min_Dp[101];

void Min_Calculate(){
    for(int i=1; i<9; i++){
        Min_Dp[i] = num[i];
    }
    Min_Dp[6] = 6;

    for(int i=9; i<101; i++){
        Min_Dp[i] = Min_Dp[i-2]*10 + num[2];

        for(int j=3; j<8; j++){
            Min_Dp[i] = min(Min_Dp[i], Min_Dp[i-j]*10 + num[j]);
        }
    }
}

int main(){
    
    Min_Calculate();
    cin >> T;
    
    while(T--){
        cin >> n;
        
        cout << Min_Dp[n] << " ";

        string Max = "";
        while(n){
            if(n % 2 != 0){
                cout << 7;
                n -= 3;
            }
            else{
                cout << 1;
                n -= 2;
            }
        }

        cout << "\n";
    }
}