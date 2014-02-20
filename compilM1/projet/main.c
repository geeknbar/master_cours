#include <stdlib.h>
#include <string.h>
#include "tableSymboles.h"

int main(int argc, char **argv)
{
	char chaine1[] = "Salut";
	char chaine2[] = "Toto";
	char chaine3[] = "azerty3";
	char chaine4[] = "Toto";
	char chaine5[] = "Salut";
	char chaine6[] = "vélo";

	llist liste1 = NULL;

	liste1 = ajoutSymbole(liste1, chaine1);
	liste1 = ajoutSymbole(liste1, chaine2);
	liste1 = ajoutSymbole(liste1, chaine3);
	liste1 = ajoutSymbole(liste1, chaine4);
	liste1 = ajoutSymbole(liste1, chaine5);
	liste1 = ajoutSymbole(liste1, chaine6);

	afficherListe(liste1);
  return 0;
}
