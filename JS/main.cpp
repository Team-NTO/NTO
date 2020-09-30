#include<iostream>
#include<stdlib.h>
#include<math.h>
 
#define endl "\n"
#define MAX 3

using namespace std;

int CLASS; 
char Arr[MAX]={'O','L','A'};
char *Select;
int all=0;

void DFS(int Cnt)
{
    if (Cnt == CLASS)
    {
    	for(int i=0;i<CLASS;i++)
    	{
    		cout << Select[i] <<" ";
		}
		cout<<endl;
		cout<<all<<endl;
    	for(int i=0;i<CLASS-2;i++)
    	{
    		if(Select[i]=='A')
    		{
    			if(Select[i]==Select[i+1])
    			{
    				if(Select[i+1]==Select[i+2])
    				{
    					all--;
    					return;
					}
    			}
			}
		}
		int count=0;
		for(int i=0;i<CLASS;i++)
		{
			if(Select[i]=='L')
			{
				count++;
			}
		}
		if(count>=2)
			{
				all--;
				return;
			}
		return;
    }
 
    for (int i = 0; i < MAX; i++)
    {
        Select[Cnt] = Arr[i];
        DFS(Cnt + 1);
    }
}
 
int main(void)
{
	cin >> CLASS;
	all=pow(3,CLASS);
	Select=(char*)calloc(CLASS,sizeof(char)*CLASS);
    DFS(0);
    cout << all;
}

