#encoding utf-8
require 'optparse'
require 'active_record'

ActiveRecord::Base.establish_connection(
   :adapter  => "mysql",
   :host     => "localhost",
   :database => "scripts",
   :username => "anonymous",
   :password => "anonymous"
)

def get_help
	print "minifp, syntaxe : minifp --help | --model optmodel | --data optmodel\n
      - le paramètre --help affiche cette aide\n
     
      - les options du paramètre --model sont tbl [ chmp:typ [chmp:typ]* | str ]\n
        où tbl désigne la table,\n
               chmp est un nom de champ,\n
               typ est le type du champ (chaine ou entier)\n
               str affiche la structure de la table\n"
end

def create_model(options={})
	print "creation du model #{options}\n"

  if ActiveRecord::Base.connection.table_exists? options.first
    query = "ALTER TABLE " + options.first + " ADD "
  else
    query = "CREATE TABLE " + options.first + " "
  end
  #on enleve le premier element
  options.shift

  query = query + "("
  options.each do |opt|
    if opt.split(':')[1] == "string"
      query = query + opt.split(':')[0] + " VARCHAR(50)"
    end
    if opt.split(':')[1] == "int"
      query = query + opt.split(':')[0] + " INT"
    end
    query = query + "," unless opt == options.last 
  end

  query = query + ");"
  
  puts query
  rslt = ActiveRecord::Base.connection.execute(query)
  rslt.each {|mysql_result| puts mysql_result}

end

def insert_data(opt={})
	print "insertion de données #{opt}\n"
end


#Utilisation de la classe OptionParser pour lire les arguments passé en paramètre
options = {}
OptionParser.new do |opts|
  opts.banner = " minifp, syntaxe : minifp --help | --model optmodel | --data optmodel"

  opts.on("--help", "display help") do |h|
    options[:help] = h
  end

  opts.on("--data", "add data") do |d|
    options[:data] = d
  end

	opts.on('--model name,opt1,op2',Array, 'create model') do |m|
		options[:model] = m;
	end

end.parse!

p options

if options[:help]
	get_help
end

if options[:model]
	create_model(options[:model])
end
if options[:data]
	insert_data
end