#  (gH)   -_-  elf.rb  ;  TimeStamp (unix) : 21 Janvier 2013 vers 20:55

require 'active_record'

ActiveRecord::Base.establish_connection(
   :adapter  => "mysql",
   :host     => "localhost",
   :database => "scripts",
   :username => "anonymous",
   :password => "anonymous"
)

class Elf < ActiveRecord::Base
  # mais il n'ya rien dans cette classe, enfin presque !
end # fin de classe Elf

#################################################################

puts "Solution 1\n"

pers =  Elf.find_by_sql "SELECT IDEN, AGE FROM elves WHERE SEXE=0 AND AGE>60"
pers.each { |enr|
  puts enr.IDEN + " " + enr.AGE.to_s
} #  fin each


#################################################################

puts "Solution 2\n"

sexe =  0
age  = 60
ch1  = "IDEN"
ch2  = "AGE"
cnd  = "SEXE=#{sexe} AND AGE>#{age} "
qry  = "SELECT #{ch1}, #{ch2} FROM elves WHERE #{cnd}"
pers = Elf.find_by_sql qry

pers.each { |enr|
  puts eval("enr.#{ch1}") + " " + eval("enr.#{ch2}.to_s")
} #  fin each

#################################################################

puts "Solution 3\n"

pers = Elf.all(:conditions => "#{cnd}")
pers.each { |enr|
     puts eval("enr.#{ch1}") + " " + eval("enr.#{ch2}.to_s")
} #  fin each

#################################################################

puts "Solution 4\n"

# ---------------------------------------------------------------

def affNumFlt(vsexe,vage)

   vch1 = "IDEN"
   vch2 = "AGE"
   cnd  = "SEXE=#{vsexe} AND AGE>#{vage} "
   pers = Elf.all(:conditions => "#{cnd}")
   pers.each { |enr|
     puts eval("enr.#{vch1}") + " " + eval("enr.#{vch2}.to_s")
   } #  fin each

 end # fin de fonction affNumFlt

# ---------------------------------------------------------------

affNumFlt(0,60)

#################################################################

puts "Solution 5\n"

# ---------------------------------------------------------------

def affNumFlt(vsexe,vage)

   Elf.where("SEXE=#{vsexe} AND AGE>#{vage}").select(:iden, :age).map{|e| puts "#{e.iden} #{e.age}"}

end # fin de fonction affNumFlt

# ---------------------------------------------------------------

affNumFlt(0,60)