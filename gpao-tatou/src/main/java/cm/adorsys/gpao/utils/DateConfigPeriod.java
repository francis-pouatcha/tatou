package cm.adorsys.gpao.utils ;

import java.util.Date;

/**
 * @author clovisgakam
 *
 */
public class DateConfigPeriod {
	private final Date begin;
	private final Date end;

	public DateConfigPeriod(Date begin, Date end) {
		super();
		this.begin = begin;
		this.end = end;
	}
	
	public Date getBegin() {
		return begin;
	}
	
	public Date getEnd() {
		return end;
	}
	
}
