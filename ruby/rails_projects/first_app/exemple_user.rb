class User
  attr_accessor :nom, :email

  def initialize(attributes = {})
    @nom  = attributes[:nom]
    @email = attributes[:email]
  end

  def formatted_email
    "#{@nom} <#{@email}>"
  end

  #exercices 4.5
  def string_shuffle(s)
	  s.split('').shuffle.join
	end

	class String
	  def shuffle
	     self.split('').shuffle.join
	  end
	end

	def exercice3
		personne1 = { :nom => "coffinet", :prenom => "dorian" }
		personne2 = { :nom => "azerty", :prenom => "uiop" }
		personne3 = { :nom => "qsdfgh", :prenom => "hjklm" }
		params[:pere] = { personne1 }
		params[:mere] = { personne2 }
		params[:enfant] = { personne3 }

		puts "#{params.inspect}"
	end

end