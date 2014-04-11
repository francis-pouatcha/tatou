package cm.adorsys.gpao.model.excepions;

import java.math.BigDecimal;

import cm.adorsys.gpao.model.Product;

public class InsufficientRawMaterialException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal minimalExpected;
	private BigDecimal quantityFound;
	private Product rawMaterial;
	
	public InsufficientRawMaterialException(BigDecimal minimalExpected,
			BigDecimal quantityFound, Product rawMaterial,String message) {
		super(message);
		this.minimalExpected = minimalExpected;
		this.quantityFound = quantityFound;
		this.rawMaterial = rawMaterial;
	}
	@Override
	public String getMessage() {
		return super.getMessage()+"Quantite experee : "+minimalExpected+", Quantite trouvee : "+quantityFound+", Produit : "+rawMaterial.getReference()+" "+rawMaterial.getName();
	}
	
}
