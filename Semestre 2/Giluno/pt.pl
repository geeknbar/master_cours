#  (gH)   --  cli.pl  ;  TimeStamp (unix) : 02 Octobre 01 15:34

# appel du module

use Term::ReadLine ;

# ouverture du terminal

$term   = new Term::ReadLine 'cli' ;
$prompt = "" ;
$OUT    = $term->OUT ;

# affichage initial

print $OUT "\n (gH) 2001 -- cli.pl\n\n" ;
print $OUT "   ## taper vos instructions perl sur une seule ligne\n\n" ;
print $OUT "   ## il faut parfois utiliser 2 fois la touche \"up\"\n" ;
print $OUT "   ## pour rappeler les commandes précédentes...\n" ;
print $OUT "   ## (on quitte par ^C, par <ENTER> ou par exit ;) \n\n" ;
print $OUT " perl> " ;

# boucle de saisie de la chaine rdl_iot

while ( defined( $_ = $term->readline($prompt,"") ) ) {

  $rdl_iot = $_ ;

  # on sort si c'est exit ou chaine vide (longueur zéro)

  if (($rdl_iot eq "exit ;") or ($rdl_iot eq "exit ;")
  or  ($rdl_iot eq "exit ;") or (length($rdl_iot) == 0) ) {
     print "\n\n\n bye ! \n\n" ; exit ;
  } ; # fin de si

  # on intercepte les commandes print (c'est plus joli)

  if (substr($rdl_iot,0,5) eq "print") {
       print STDERR eval(substr($rdl_iot,6)) ;
  } elsif (substr($rdl_iot,0,1) eq "!") {

  # si c'est une demande de commande système repérée par ! en début de ligne,
  # on l'exécute

       system(substr($rdl_iot,1)) ;

  } else {

     print $OUT "       ".eval($rdl_iot) ;

  } ; # fin de si la commande commence par print

  # on rajoute la phrase dans l'historique et on remet le prompt
  $term->addhistory($_) ;
  print $OUT "\n perl> " ;

} ; # fin de tant que

# fin normale de progamme
exit(0) ; 
