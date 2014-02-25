/*****************************************************************************/
/*                       test individual rexx commands                       */
/*****************************************************************************/

say
parse version rxv
say rxv
say
save_result = result
say 'REXX statements / exit / <enter> :'
result = ''

restart:
signal on syntax

do forever
    call charout ,'Rexx> '
    parse linein __temp__
    if length(__temp__) = 0 then do
       say " "
       say " bye ! "
       say " "
       exit
    end
    result = save_result
    interpret __temp__
    save_result = result
    end
return

/* syntax error handler */
syntax:
say 'REXX error' rc 'in line' sigl':' errortext(rc)
if sourceline() > 0 & symbol('sigl') = 'VAR' then
    say '=====>' sourceline(sigl)
signal restart 
