#include<iostream>
#include<cstring>
#include<algorithm>
#define REP(i,n) for(int i=0;i<n;++i)
using namespace std;
 
int n,a[101][101],mn,mx,z[101][101],di[]={0,0,1,-1},dj[]={1,-1,0,0};
 
bool dfs(int i,int j){
    if(i==n-1&&j==n-1)return true;
    z[i][j]=1;
    REP(d,4){
        int r=i+di[d],c=j+dj[d];
        if(r<0||c<0||r>=n||c>=n||z[r][c]||a[r][c]<mn||a[r][c]>mx)continue;
        if(dfs(r,c))return true;
    }
    return false;
}
 
int main(){
    ios_base::sync_with_stdio(false);cin.tie(0);
    
    mn=200;mx=0;
    cin>>n;
    REP(i,n)REP(j,n){
        cin>>a[i][j];
        mn=min(mn,a[i][j]);
        mx=max(mx,a[i][j]);
    }
    
    int result=mx-mn,until=mx;
    mx=a[0][0];
    while(mn<=a[0][0]&&mx<=until){
        memset(z,0,sizeof(z));
        if(dfs(0,0)){
            result=min(result,mx-mn);
            mn++;
        }else mx++;
    }
    cout<<result;
}