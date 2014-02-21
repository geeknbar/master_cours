%{
	#include<stdio.h>
	#include"tableSymboles.h"
	void yyerror(char*s);
	llist table_sym = NULL;
%}
CHIFFRE [0-9]
LETTRE[a-zA-Z]

%%

{LETTRE}({LETTRE}|{CHIFFRE})* {
	table_sym = ajoutSymbole(table_sym, yytext);
}

[ \n \t] {

}

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
	if(f!=NULL)
		fclose(f);

	afficherListe(table_sym);
	liberationMemoire(table_sym);

	return 0;
}

void yyerror(char*s){
	fprintf(stderr, "Erreur %s\n", s);
}