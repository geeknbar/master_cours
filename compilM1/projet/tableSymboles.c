#include <stdlib.h>
#include <string.h>
#include <stdio.h>


typedef struct element element;
typedef element* llist;
struct element
{
    // int val;
    char* symb;
    struct element *nxt;
};


llist ajouterEnFin(llist table_symboles, char* symbole)
{
    /* On crée un nouvel élément */
    element* nouvelElement = malloc(sizeof(element));
 
    /* On assigne la valeur au nouvel élément */
    nouvelElement->symb = symbole;
 
    /* On ajoute en fin, donc aucun élément ne va suivre */
    nouvelElement->nxt = NULL;
 
    if(table_symboles == NULL)
    {
        /* Si la liste est videé il suffit de renvoyer l'élément créé */
        return nouvelElement;
    }
    else
    {
        /* Sinon, on parcourt la liste à l'aide d'un pointeur temporaire et on
        indique que le dernier élément de la liste est relié au nouvel élément */
        element* temp=table_symboles;
        while(temp->nxt != NULL)
        {
            temp = temp->nxt;
        }
        temp->nxt = nouvelElement;
        return table_symboles;
    }
}


llist ajouterEnTete(llist table_symboles, char* symbole)
{
    /* On crée un nouvel élément */
    element* nouvelElement = malloc(sizeof(element));
 
    /* On assigne la valeur au nouvel élément */
    nouvelElement->symb = symbole;
 
    /* On assigne l'adresse de l'élément suivant au nouvel élément */
    nouvelElement->nxt = table_symboles;
 
    /* On retourne la nouvelle liste, i.e. le pointeur sur le premier élément */
    return nouvelElement;
}

void afficherListe(llist table_symboles)
{
    element *tmp = table_symboles;
    /* Tant que l'on n'est pas au bout de la liste */
    int i = 1;
    while(tmp != NULL)
    {
        /* On affiche */
        printf("Symbole %d : %s\n", i, tmp->symb);
        // printf("%s \n", tmp->symb);
        /* On avance d'une case */
        tmp = tmp->nxt;
        i++;
    }
}

int estVide(llist table_symboles)
{
    return (table_symboles == NULL)? 1 : 0;
}

llist rechercherElement(llist table_symboles, char* symbole)
{
    element *tmp=table_symboles;
    /* Tant que l'on n'est pas au bout de la liste */
    while(tmp != NULL)
    {
        if(strcmp(tmp->symb, symbole)==0)
        {
            /* Si l'élément a la valeur recherchée, on renvoie son adresse */
            return tmp;
        }
        tmp = tmp->nxt;
    }
    return NULL;
}

llist ajoutSymbole(llist table_symboles, char* symbole)
{
    char* symb;
    symb = malloc(strlen(symbole) + 1);
    strcpy(symb, symbole);
    if (rechercherElement(table_symboles, symb)==NULL)
    {
        table_symboles = ajouterEnFin(table_symboles, symb);
    }
    return table_symboles;
}