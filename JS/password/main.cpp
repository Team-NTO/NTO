#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

bool comp(string s1, string s2){
    return s1 < s2;	//string 사전순
}

int ascending(string x[], int n)
{
    for(int i = 0; i<n-1; i++)
    {
        if(x[i]>=x[i + 1])
            return 0;
    }
    return 1;
}

int main (){
    int L, C;
    vector<char> n;
    vector<string> tmp;
    vector<string> res;

    cin >> L >> C;
    for(int i=0; i<C; i++){
        char c;
        cin >> c;
        n.push_back(c);
    }

    // 0과1을 저장 할 벡터 생성
    vector<int> ind;

    // k개의 1 추가
    for(int i=0; i<L; i++){
        ind.push_back(1);
    }

    // 2개(6개-2개)의 0 추가
    for(int i=0; i<n.size()-L; i++){
        ind.push_back(0);
    }

    // 정렬
    sort(ind.begin(), ind.end());

    //순열
    do{
        string str="";
        for(int i=0; i<ind.size(); i++){
            if(ind[i] == 1){
                str.push_back(n[i]);
            }
        }
        tmp.push_back(str);
    }while(next_permutation(ind.begin(), ind.end()));

    for(int i=0; i<tmp.size(); i++){
            if(ascending(&tmp[i], tmp[i].size()) == 1)
            {
                res.push_back(tmp[i]);
            }
    }

    for(int i=0; i<res.size(); i++){
        cout << res[i] << "\n";
    }

    return 0;
}