#encoding utf-8
require 'optparse'
require 'active_record'

CORRESPONDANCE_TABLE = {
  "chaine" => "VARCHAR(50)",
  "entier" => "INT",
  "booleen" => "BOOL"
}

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
  
  # On regarde si la table existe ou non
  is_created = ActiveRecord::Base.connection.table_exists? options.first
  
  # Supression du premier élément des options qui ne sert que pour le nom de la table
  # On construit la requête sql
  options.shift
  query = query_model(table, options, is_created)
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

# Méthode pour construire la requete sql pour l'option --model
def query_model(table, options, is_created)
  # Création de la requête sql
  # On regarde si la table est créée ou non
  if is_created
    query = "ALTER TABLE " + table + " ADD "
  else
    query = "CREATE TABLE " + table + " "
  end
  query += "("
  # On récupère chaque paramètres de l'option
  options.each do |opt|
    query += opt.split(':')[0] +" "+ CORRESPONDANCE_TABLE[opt.split(':')[1]]
    query += "," unless opt == options.last 
  end
  query += ");"
end

# Méthode permettant de parser les options et de créer la reqûete sql pour l'option --data
def insert_data(options={})
	# Selection du nom de la table
  table = options.first
  # Supression du premier élément des options qui ne sert que pour le nom de la table
  options.shift
  # et passage a la methode de construction
  query = query_data(table, options)
  # si une erreur est levé on l'affiche à l'utilisateur
  # sinon on affiche que les données ont bien été enregistré
  begin
    puts query
    ActiveRecord::Base.connection.execute(query)
    puts "Valeurs #{query.match(/\w+,\w+/)[0]} insérées dans la table #{table}"
  rescue => e
    puts e
  end
end

# Méthode pour construire la requete sql pour l'option --model
def query_data(table, options)
  # Initilisation des tableaux qui vont contenir les champs et leurs valeurs
  fields, values = Array.new(2) { [] }
  # Création de la requête sql
  query = "INSERT INTO " + table
  options.each do |opt|
    fields << opt.split(':')[0]
    values << '\'' + opt.split(':')[1] + '\''
  end
  query += "(" + fields.join(',') + ") VALUES (" + values.join(',') + ")"
end

# Méthode permettant de parser les options et de créer la reqûete sql pour l'option --data
def visualize(options={})
  ActiveRecord::Base.connection.tables.each do |table_name|
    options.each do |table|
      if table_name == table
        puts "\n" + table_name
        ActiveRecord::Base.connection.columns(table_name).each do |c|
          puts "- " + c.name + ": " + c.type.to_s + " " + c.limit.to_s
        end
      end
    end
  end
end

# Utilisation de la classe OptionParser pour lire les arguments passé en paramètre
# Initailisation des options
options = {}
# Chaine contenant l'explication de 'optmodel'
explanation_param_model_data = "
                                          optmodel de la forme : tbl [ chmp:val [chmp:val]* | str ]
                                          tbl désigne la table,
                                          chmp est un nom de champ,
                                          typ est le type du champ (chaine ou entier)
                                          str affiche la structure de la table."
# Chaine contenant l'explication de 'optview'                                       
explanation_param_view = "
                                          optview de la forme : tbl [champ/valeur] [--mode optmode ]
                                          table affiche toute la table dont le nom est passé en paramètre
                                            induit par un ET entre les arguments
                                            et où les options --mode sont show URI | go URI ;
                                          URI est une ressource écrite en REST ;
                                          show affiche l'URL associée alors que go l'affiche avec firefox."
# Début du parsage des options
OptionParser.new do |opts|
  opts.banner = "minifp, syntaxe : minifp --help | --model optmodel | --data optmodel | --view optview"

  # L'affichage de help se fait automatiquement avec les renseignements donnés avec les autres options possibles
  opts.on("--help", "Afficher cette aide") do |help|
    puts opts
    exit
  end
  # Après avoir cherché je n'ai pas trouvé comment avoir des arguments d'options séparés par des espaces
  # Il semblerai que ce soit une convention de mettre des ',' mais j'ai peut être mal compris
  opts.on("--data optmodel",Array, "Ajout de donnée à une table." + explanation_param_model_data) do |data|
    options[:data] = data
  end

	opts.on("--model optmodel",Array, "Creation ou modification d'une table." + explanation_param_model_data ) do |mdl|
		options[:model] = mdl
	end

  opts.on("--view optview",Array, "Visualisation de la table." + explanation_param_view ) do |view|
    options[:view] = view
  end
end.parse!

puts "AGRV #{options}"
# Appel des méthodes suivant les paramètres passés
if options[:model]
	create_model(options[:model])
end
if options[:data]
	insert_data(options[:data])
end
if options[:view]
  visualize(options[:view])
end
