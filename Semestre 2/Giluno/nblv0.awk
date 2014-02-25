#  (gH)   -_-  nblv0.awk  ;  TimeStamp (unix) : 17 Novembre 2012 vers 22:24

END{ # j'adore commencer par END !
 printf("Fichier %s avec %d ligne(s), NF moyen = %8.2f \n", FILENAME, FNR, nc/FNR )
} # fin de END

(FNR==1) { nc = NF }   # ligne 1 du fichier, on initialise nc qui est le nombre de mots en tout

(FNR> 1) { nc += NF  } # après la première ligne, on cumule les NF dans nc
