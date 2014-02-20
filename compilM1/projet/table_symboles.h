typedef struct element element;
typedef element* llist;

llist ajouterEnFin(llist table_symboles, char* symbole);
llist ajouterEnTete(llist table_symboles, char* symbole);
void afficherListe(llist table_symboles);
int estVide(llist table_symboles);
llist rechercherElement(llist table_symboles, char* symbole);
llist ajoutSymbole(llist table_symboles, char* symbole);