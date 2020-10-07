#include <iostream>

int main() {
    int r;
    std::cin >> r;

    int **mat;
    int *start = new int[((r*r)-r)/2];

    mat = new int*[r];

    for (int i = 0; i < r; i++)
        mat[i] = new int[r];

    for (int i = 0; i < ((r*r)-r)/2; i++)
        start[i] = 0;

    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < r; j++)
        {
            std::cin >> mat[i][j];
        }
    }

    int idx=0;
    for (int i = 0; i < r; i++)
    {
        for(int j=0;i-j>=0;j++) {
            if(mat[i][j]!=0) {
                start[idx] = mat[i][j] + mat[j][i];
            }
            if(start[idx]!=0) {
                idx++;
            }
        }
    }

    for (int i = 0; i < ((r*r)-r)/2; i++)
    {
        std::cout << start[i] << " ";
    }
    return 0;
}
