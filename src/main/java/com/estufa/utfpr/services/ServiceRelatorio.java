package com.estufa.utfpr.services;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class ServiceRelatorio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public byte[] gerarRelatorio(String nomeRelatorio,Map<String,Object> params, ServletContext servletContext) throws Exception {
		
		//obtem conexao com o banco de dados
		Connection connection = jdbcTemplate.getDataSource().getConnection();		
		
		// carregar caminho do arquivo Jasper
		
		String caminhoJasper = servletContext.getRealPath("relatorios") + File.separator +nomeRelatorio + ".jasper"; 
		
		//Gerar o relatorio com os dados  e conexao
		
		JasperPrint print = JasperFillManager.fillReport(caminhoJasper, params, connection);
		
		//exportar para byte o pdf
		byte[] retorno = JasperExportManager.exportReportToPdf(print); 
		
		connection.close();
		
		return retorno;
	}
	
}