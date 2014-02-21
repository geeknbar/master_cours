#ifndef TABS_H
#define TABS_H


typedef struct element element;
typedef element* llist;

llist ajouterEnFin(llist table_symboles, char* symbole);
llist ajouterEnTete(llist table_symboles, char* symbole);
void afficherListe(llist table_symboles);
int estVide(llist table_symboles);
int rechercherElement(llist table_symboles, char* symbole);
llist ajoutSymbole(llist table_symboles, char* symbole);
llist supprimerElementEnTete(llist liste);
void liberationMemoire(llist liste);

#endif