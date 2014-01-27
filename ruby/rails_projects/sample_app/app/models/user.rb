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
#

class User < ActiveRecord::Base
  attr_accessible :email, :nom, :dateNaissance, :poidsActuel, :poidsIdeal, :estSportif, :souhaitePratiquerSport
  # attr_accessor :dateNaissance, :poidsActuel, :poidsIdeal, :estSportif, :souhaitePratiquerSport
	email_regex = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i

  validates :nom,  :presence => true, :length   => { :maximum => 50 }
  validates :email, :presence   => true,
                    :format     => { :with => email_regex },
                    :uniqueness => { :case_sensitive => false }


 	def age
    now = Time.now.utc.to_date
    now.year - self.dateNaissance.year - ((now.month > self.dateNaissance.month || (now.month == self.dateNaissance.month && now.day >= self.dateNaissance.day)) ? 0 : 1)
  end

  def imc
  	taille = 1.60
  	imc = self.poidsActuel / (taille * taille)
  	
  	imc_status = case imc
						  	 when 0..16 then "dénutrition"
						  	 when 16..18 then "maigreur"
						  	 when 18..25 then "normal"
						  	 when 25..30 then "surpoids"
						  	 when 30..35 then "obésité modérée"
						  	 when 35..40 then "obésité sévère"
						  	 else "obésité massive"	
						  	 end
	end
end
