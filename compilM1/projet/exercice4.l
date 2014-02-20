%{
	#include<stdio.h>
	#include"tableSymboles.h"
	void yyerror(char*s);
	
%}
CHIFFRE [0-9]
LETTRE[a-zA-Z]

%%

{LETTRE}({LETTRE}|{CHIFFRE})* {
	ajoutSymbole(table_sym, yytext);
}

%%
int main(int argc, char* argv[]){

	llist table_sym = NULL;


	FILE *f = NULL;
	if(argc >1) {
		f = fopen(argv[1],"r");
			if(f==NULL){
				return -1;
			}
			yyin=f;
	}
	yylex();
	if(f!=NULL)
		fclose(f);

	afficherListe(table_sym);
}

void yyerror(char*s){
	fprintf(stderr, "Erreur %s\n", s);
}