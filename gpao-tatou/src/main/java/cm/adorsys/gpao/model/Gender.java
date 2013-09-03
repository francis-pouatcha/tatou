package cm.adorsys.gpao.model;


public enum Gender {

	  Masculin("Mr.","M"), Feminin("Mme.","F"),Mademoiselle("Mlle.","Mlle"),Enfant("Enft.","Enft"),Docteur("Dr.","Dr"),Neutre(" ","N");
	    
	    private final String salutation ;
	    private final String shortened;

		private Gender(String salutation, String shortened) {
			this.salutation = salutation;
			this.shortened = shortened;
		}

		public String getSalutation() {
			return salutation;
		}

		public String getShortened() {
			return shortened;
		}
		
		public static Gender getGenderByName(String gender){
			if (gender.equals(Gender.Feminin.name())) return Gender.Feminin ;
			if (gender.equals(Gender.Masculin.name())) return Gender.Masculin ;
			if (gender.equals(Gender.Mademoiselle.name())) return Gender.Mademoiselle ;
			if (gender.equals(Gender.Enfant.name())) return Gender.Enfant ;
			if (gender.equals(Gender.Docteur.name())) return Gender.Docteur ;
			if (gender.equals(Gender.Neutre.name())) return Gender.Neutre ;

			return Gender.Neutre ;

			
		}
}
