# statdir.awk : gestion de répertoire (nb de fichiers par extension)

BEGIN { nbf = 0 }
# correction car sinon on ne trouve pas d'extension, c'est $9 et pas $8 sur les pc unix
/-/ &&  $1 !~ /^d/ && $9 != "dir.dir" {

  ip = index($9,".")
  if (ip > 0) { ext = substr($9,1+ip) } else { ext = "???" }
  tot[ext] += $5 ; nb[ext] ++ ; nbf++  ; tgen += $5
} # fin de traitement des lignes retenues

END  { print "---  ext            nb_fich        cumul_taille (kO)"
       for (ext in tot) {
           printf ("     %-15s  %3d     %12.3f\n" , ext , nb[ext] , tot[ext]/1024 )
       } # fin de pour
       if (nbf==0) {  print " mais je n'ai vu aucun fichier !! "} else {
          tgen = tgen/(1024*1024)
          print "     soit en tout " sprintf("%7d",nbf) " fichiers et   " sprintf("%5.5f",tgen) " Mo "
       }
} # fin du END
