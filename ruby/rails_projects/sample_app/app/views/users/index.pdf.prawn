prawn_document() do |pdf|
    @users.each {|user| 
				pdf.text user.nom
        pdf.text user.email
        pdf.text user.dateNaissance.to_s
    }

    pdf.text "Créé le : " +  Time.now.strftime('%d-%m-%Y %H:%M')
end