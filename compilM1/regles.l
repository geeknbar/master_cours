%{
	#include<stdio.h>
	void yyerror(char*s);
%}
%%
^[ \t]+ {
}
[ \t]+$ {
}
[ \t]+ {
	printf(" ");
}
[^ \t] {
	printf("%c", yytext[0]);
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