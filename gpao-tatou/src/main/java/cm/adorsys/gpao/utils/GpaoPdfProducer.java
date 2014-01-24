package cm.adorsys.gpao.utils;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import cm.adorsys.gpao.security.SecurityUtil;

/**
 * @author clovisgakam
 *
 */
@Service
public class GpaoPdfProducer {

	@Autowired
	ApplicationContext context;
	DataSource dataSource = null;

	public void buildPdfDocument(Map parameters, HttpServletResponse response, String jasperFile)throws Exception {
		if(jasperFile == null || jasperFile.isEmpty() || response == null)throw new RuntimeException("jasperFile or response may not be null ! ");
		Connection connection = DataSourceUtils.doGetConnection(getDataSource());
		parameters.put("PrintBy", SecurityUtil.getUserName());
		parameters.put("user", SecurityUtil.getUserName());
		net.sf.jasperreports.engine.JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
		net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		response.setContentLength(baos.size());
		ServletOutputStream out1 = response.getOutputStream();
		baos.writeTo(out1);
		out1.flush();
		connection.close();
	}

	public DataSource getDataSource()
	{
		if(dataSource==null){
			dataSource = (DataSource) context.getBean("dataSource");
		}
		return dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

}
