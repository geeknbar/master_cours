# #  TimeStamp  (dos) : 28 Octobre 04 11:41  --  x:\testebf.pl

     use XML::Parser;

     if ($#ARGV==-1) {
        print "   syntaxe : testebf fichier\n" ;
        exit(-1) ;
     } ; # fin de si

     $nf = $ARGV[0] ;
     my $parser= new XML::Parser( Style => 'Stream');
     eval {$parser->parsefile( $nf )};
     if ($@) { die "\n Document $nf mal formé : \n\t\t $@\n" ; } ;

     # si on arrive ici, c'est que tout va bien

     print(" le document $nf est bien formé.\n") ;

