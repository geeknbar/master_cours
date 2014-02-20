typedef struct element element;
typedef element* llist;

llist ajouterEnFin(llist liste, int valeur);
llist ajouterEnTete(llist liste, int valeur);
void afficherListe(llist liste);
int estVide(llist liste);
llist rechercherElement(llist liste, int valeur);



