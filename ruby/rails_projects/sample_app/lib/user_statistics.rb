class UserStatistics

	attr_accessor :poids_ac, :poids_id, :datas

	def initialize(users)
		@users = users
		@poids_ac = Array.new
		@poids_id = Array.new		
		@datas = Array.new
  end

  def poids_ac_users
  	#pour tous les utilisateurs on va chercher les poids idéaux
		@users.each do |user|
			@poids_ac << user.poidsActuel
		end

		@poids_ac
  end
	
	def poids_id_users
  	#pour tous les utilisateurs on va chercher les poids idéaux
		@users.each do |user|
			@poids_id << user.poidsIdeal
		end

		@poids_id
  end

  def repartition_imc
		repartition_imc = []
		#pour tous les utilisateurs on remplis les tableau
		@users.each do |user|
			repartition_imc << user.imc.split(/\s:\s/)[0]
		end

		#pour la répartiton on compte les occurences de chaque imc
		counts = Hash.new(0)
		for imc in repartition_imc
			counts[imc] += 1
		end

		#on formate les imc pour le graphique de higjcharts
		counts.each do |k,v|
			@datas << [k,v]
		end

		@datas
  end
	
end