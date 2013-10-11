/*  TimeStamp  (dos) : 28 Octobre 04 11:47  --  x:\testebf.rex */

parse arg nf
if words(nf) = 0 then do
   say
   say  " syntaxe :  testebfr fichier "
   say
   exit 0
end /* fin de si */

/* chargement de la librairie */

rc1 = rxfuncquery('xmlloadfuncs')
if rc1 = 1 then do
   rc2 = rxFuncAdd('xmlloadfuncs', 'rexxxml', 'xmlloadfuncs')
   if rc2 = 1 then do
                fail('load' rxfuncerrmsg())
                exit 0
              end
end
call xmlloadfuncs

tbf = xmlParseXML(nf)
if tbf\=0
   then say " le document " nf " est bien formé."
   else do
     say " le document " nf " est mal  formé."
     say xmlError()
   end /* fin si */

