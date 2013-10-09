package cm.adorsys.gpao.utils;

import java.io.File;
import java.util.List;

/**
 * @author clovisgakam
 *
 */
public interface EntityMarshaller<T> {
	public File marshalle(List<T> entityToMarshalle);
	public List<T> unMarshalle(File file) ; 

}
