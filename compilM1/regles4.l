%{
	#include<stdio.h>
	#include"table_symboles.h"
	void yyerror(char*s);
	
%}
CHIFFRE [0-9]
LETTRE[a-zA-Z]

%%

{LETTRE}({LETTRE}|{CHIFFRE})* {
	table_symboles(t,yytext);
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
}

void yyerror(char*s){
	fprintf(stderr, "Erreur %s\n", s);
}