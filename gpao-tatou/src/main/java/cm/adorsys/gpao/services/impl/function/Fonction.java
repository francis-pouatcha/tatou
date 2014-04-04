package cm.adorsys.gpao.services.impl.function;
public interface Fonction<U,G> {
	G doFunction(U ... u );
}