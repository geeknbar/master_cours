# encoding: UTF-8
# == Schema Information
#
# Table name: users
#
#  id                     :integer          not null, primary key
#  nom                    :string(255)
#  email                  :string(255)
#  created_at             :datetime         not null
#  updated_at             :datetime         not null
#  dateNaissance          :date
#  poidsActuel            :integer
#  poidsIdeal             :integer
#  estSportif             :boolean
#  souhaitePratiquerSport :boolean
#  taille                 :integer
#

class User < ActiveRecord::Base

  has_attached_file :cvpdf ,:styles => {:thumb => ["100x100", :png]}


  attr_accessible :email, :nom, :dateNaissance, :poidsActuel, :poidsIdeal, :estSportif, :souhaitePratiquerSport, :taille, :cvpdf
  # attr_accessor :dateNaissance, :poidsActuel, :poidsIdeal, :estSportif, :souhaitePratiquerSport
	email_regex = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i

  validates :nom,  :presence => true, :length   => { :maximum => 50 }
  validates :email, :presence   => true,
                    :format     => { :with => email_regex },
                    :uniqueness => { :case_sensitive => false }

  validates_numericality_of :poidsActuel, :greater_than => Proc.new {|user| user.poidsIdeal }

 	def age
 		if self.dateNaissance > Date.today
 			"Visiteur du futur"
 		else	
	    now = Time.now.utc.to_date
	    now.year - self.dateNaissance.year - ((now.month > self.dateNaissance.month || (now.month == self.dateNaissance.month && now.day >= self.dateNaissance.day)) ? 0 : 1)
  	end
  end

  def imc
  	if taille.nil? then
  		imc_status = "données insuffisantes"
  	else
  		taille_mettre = (self.taille.to_f/100)
	  	imc = (self.poidsActuel / (taille_mettre*taille_mettre)).round(2)
	  	
	  	imc_status = case imc
							  	 when 0...16 then "dénutrition"
							  	 when 16...18 then "maigreur"
							  	 when 18...25 then "normal"
							  	 when 25...30 then "surpoids"
							  	 when 30...35 then "obésité modérée"
							  	 when 35...40 then "obésité sévère"
							  	 else "obésité massive"	
							  	 end
			imc_status += " : #{imc}"
		end
	end
end
