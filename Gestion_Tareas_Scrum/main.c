#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    int dificultad;
    scanf("%d", &dificultad);

    srand(time(NULL));
    int num_aleatorio = 1 + rand() % 100;   // Generamos un n.º aleatorio entre 1 y 100

    int probabilidad;
    if (dificultad==1) probabilidad = num_aleatorio+99;
    else if (dificultad==2) probabilidad = num_aleatorio+85;
    else if (dificultad==3) probabilidad = num_aleatorio+70;
    else if (dificultad==4) probabilidad = num_aleatorio+30;
    else if (dificultad==5) probabilidad = num_aleatorio+15;

    int tareaCompletada; // Si este valor es 1 se completa, si no, no se completa

    if (probabilidad>=100) tareaCompletada=1;
    else tareaCompletada = 0;

    printf("%d", tareaCompletada);

    return 0;
}
