#include <stdio.h>
int f(int x)
{
	int f;
	int i;
	while (i < x)
	{
		if (i % 2 == 0)
			f = f + 1;
	}
	return f;
}

int main(int argc, char* argv[])
{
	int x;
	fscanf("%d\n",&x);
	printf("%d\n",f(x));
	return EXIT_SUCESS;
}