package cm.adorsys.gpao.utils;

import java.math.BigDecimal;

import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.excepions.UnmatchUnitOfMesureGroupException;

public class UdmUtils {
	private static BigDecimal  convert(UnitOfMesures sourceUdm ,UnitOfMesures targetUdm,BigDecimal value) throws UnmatchUnitOfMesureGroupException {
		if (!hasSameGroup(sourceUdm, targetUdm)) throw new UnmatchUnitOfMesureGroupException("the Both Unit of Mesure Are Incompatible !");
		BigDecimal toReferenceUdm = convertToReferenceUdm(sourceUdm, value);
		return toReferenceUdm.divide(targetUdm.getRatio());
	}

	private static boolean hasSameGroup(UnitOfMesures sourceUdm ,UnitOfMesures targetUdm){
		return sourceUdm.getUnitGroup().equals(targetUdm.getUnitGroup());
	}

	private static BigDecimal  convertToReferenceUdm(UnitOfMesures unitOfMesures, BigDecimal value) {
		return value.multiply(unitOfMesures.getRatio());
	}

}
