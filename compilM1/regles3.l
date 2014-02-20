%{
	#include<stdio.h>
	void yyerror(char*s);
	int nbMotsClef, nbNombres, nbIdent, nbOperateur, nbLignes;
%}
CHIFFRE [0-9]
LETTRE[a-zA-Z]

%x COMMENTAIRE
%option yylineno
%%

"begin"|"end" {
	nbMotsClef ++;
	printf("Mot clef %s\n", yytext);
}

{LETTRE}({LETTRE}|{CHIFFRE})* {
	printf("indentificateur %s\n", yytext);
	nbIdent ++;
}

\"([^\"\n]|\\\")*\" {
	printf("chaine caractere %s \n", yytext);
}

\{[^\}]*\} {
	printf("commentaire {} %s\n", yytext);
}

"/*" {
	BEGIN(COMMENTAIRE);
	printf("Commentaire /**/ :");
}
<COMMENTAIRE>"*/" {
	BEGIN(INITIAL);
	printf("%s\n",yytext);
}
<COMMENTAIRE>. {
	printf("%s",yytext);
}
<COMMENTAIRE>\n {
	printf("%s",yytext);
	nbLignes++;
}
<COMMENTAIRE><<EOF>> {
	BEGIN(INITIAL);
	printf("commentaire non terminé");
}

"+"|"-"|"*"|"**" {
	printf("Operateur %s\n", yytext);
	nbOperateur ++;
}

{CHIFFRE}+(","{CHIFFRE}+)? {
	nbNombres ++;
	printf("Nombre %s\n", yytext);
}

[ \t] {

}

[\n] {
	nbLignes ++;
}
. { 
	printf("error, caractère inconnu %s \n", yytext);
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
	printf("Nombre de mots clef : %d, Nombre de nombres : %d, Nombre de identificateurs : %d, Nombre d'opérateur : %d, Nombre de lignes : %d\n", nbMotsClef, nbNombres, nbIdent, nbOperateur, yylineno);
	if(f!=NULL)
		fclose(f);
}

void yyerror(char*s){
	fprintf(stderr, "Erreur %s\n", s);
}