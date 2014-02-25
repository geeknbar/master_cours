#encoding utf-8
require 'optparse'
require 'active_record'

ActiveRecord::Base.establish_connection(
   :adapter  => "mysql",
   :host     => "localhost",
   :database => "test",
   :username => "anonymous",
   :password => "anonymous"
)

# Méthode permettant de parser les options et de créer la reqûete sql pour l'option --model
def create_model(options={})
	# Selection du nom de la table
  table = options.first
  # On regarde si la table est créée ou non
  is_created = ActiveRecord::Base.connection.table_exists? options.first
  if is_created
    query = "ALTER TABLE " + table + " ADD "
  else
    query = "CREATE TABLE " + table + " "
  end
  # Supression du premier élément des options qui ne sert que pour le nom de la table
  options.shift

  # Création de la requête sql
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
  
  # si une erreur est levé on l'affiche à l'utilisateur
  # sinon on affiche que les données ont bien été enregistré
  begin
    ActiveRecord::Base.connection.execute(query)
    puts query
    if is_created
      puts "table #{table} mise à jour"
    else
      puts "table #{table} crée avec #{options.count} champs"
    end
  rescue => e
    puts e
  end
end

# Méthode permettant de parser les options et de créer la reqûete sql pour l'option --data
def insert_data(options={})
	# Selection du nom de la table
  table = options.first
  query = "INSERT INTO " + table
  # Supression du premier élément des options qui ne sert que pour le nom de la table
  options.shift
  # Initilisation des tableaux qui vont contenir les champs et leurs valeurs
  fields, values = Array.new(2) { [] }
  # Création de la requête sql
  options.each do |opt|
    fields << opt.split(':')[0]
    values << '\'' + opt.split(':')[1] + '\''
  end
  query = query + "(" + fields.join(',') + ") VALUES (" + values.join(',') + ")"

  # si une erreur est levé on l'affiche à l'utilisateur
  # sinon on affiche que les données ont bien été enregistré
  begin
    puts query
    ActiveRecord::Base.connection.execute(query)
    puts "Valeurs #{fields.join(',')} insérées dans la table #{table}"
  rescue => e
    puts e
  end

end

# Utilisation de la classe OptionParser pour lire les arguments passé en paramètre
# Initailisation des options
options = {}
# Chaine contenant la forme de 'optmodel'
explanation_param = "
                                          optmodel de la forme : tbl [ chmp:val [chmp:val]* | str ]
                                          tbl désigne la table,
                                          chmp est un nom de champ,
                                          typ est le type du champ (chaine ou entier)
                                          str affiche la structure de la table."
# Début du parsage des options
OptionParser.new do |opts|
  opts.banner = "minifp, syntaxe : minifp --help | --model optmodel | --data optmodel"

  # L'affichage de help se fait automatiquement avec les renseignements donnés avec les autres options possibles
  opts.on("--help", "Afficher cette aide") do |h|
    puts opts
    exit
  end

  # Après avoir cherché je n'ai pas trouvé comment avoir des arguments d'options séparés par des espaces
  # Il semblerai que ce soit une convention de mettre des ',' mais j'ai peut être mal compris
  opts.on("--data optmodel",Array, "Ajout de donnée à une table." + explanation_param) do |d|
    options[:data] = d
  end

	opts.on("--model optmodel",Array, "Creation ou modification d'une table." + explanation_param ) do |m|
		options[:model] = m
	end
end.parse!

# Appel des méthodes suivant les paramètres passés
if options[:model]
	create_model(options[:model])
end
if options[:data]
	insert_data(options[:data])
end