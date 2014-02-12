%{
	#include<stdio.h>
	void yyerror(char*s);
	int nbMots, nbPoncts, nbNombres;
%}
CHIFFRE [0-9]
%%
[.!,] {
	nbPoncts ++;
}

{CHIFFRE}+(","{CHIFFRE}+)? {
	nbNombres ++;
}

[a-zA-Z]+ {
	nbMots ++;
}

.|\n { }



%%
int main(int argc, char* argv[]){
	FILE *f = NULL;
	if(argc >1) {
		f = fopen(argv[1],"r");
			if(f==NULL){
				return -1;
			}
			yyin=f;
	}
	yylex();
	printf("Nombre de mots : %d,Nombre de ponctuation : %d, Nombre de nombres : %d\n", nbMots, nbPoncts, nbNombres);
	if(f!=NULL)
		fclose(f);
}

void yyerror(char*s){
	fprintf(stderr, "Erreur %s\n", s);
}