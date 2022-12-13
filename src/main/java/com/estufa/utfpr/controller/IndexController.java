package com.estufa.utfpr.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estufa.utfpr.model.NivesLeituras;
import com.estufa.utfpr.repository.NiveisLeiturasRepository;
import com.estufa.utfpr.services.ServiceRelatorio;

@CrossOrigin
@RestController
@RequestMapping(value = "/niveis")
public class IndexController {
	
	@Autowired
	private NiveisLeiturasRepository niveisLeiturasRepository;
	
	@Autowired
	private ServiceRelatorio serviceRelatorio;
	
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<NivesLeituras>> leituras(){
		List<NivesLeituras> todasLeituras = (List<NivesLeituras>)niveisLeiturasRepository.findAll();	
		
		return new ResponseEntity<List<NivesLeituras>>(todasLeituras, HttpStatus.OK);		
		
	}
	
	@GetMapping(value = "/{data}", produces = "application/json")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public ResponseEntity<List<NivesLeituras>>consultapordata(@PathVariable(value = "data") @DateTimeFormat(pattern ="yyyy-MM-dd" ) Date data){
		List<NivesLeituras> nLeituras = (List<NivesLeituras>)niveisLeiturasRepository.findByDate(data);
		System.out.print(data);
		return new ResponseEntity<List<NivesLeituras>>(nLeituras, HttpStatus.OK);		
	}
	@GetMapping(value = "/{data1}/{data2}", produces = "application/json")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public ResponseEntity<List<NivesLeituras>>consultaEntreDatas(@PathVariable(value = "data1") @DateTimeFormat(pattern ="yyyy-MM-dd" ) Date data1, @PathVariable(value = "data2") @DateTimeFormat(pattern ="yyyy-MM-dd" ) Date data2 ){
		List<NivesLeituras> nLeituras = (List<NivesLeituras>)niveisLeiturasRepository.findByDatesBetween(data1, data2);
		return new ResponseEntity<List<NivesLeituras>>(nLeituras, HttpStatus.OK);		
	}
	

	
	@PostMapping(value = "/")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public ResponseEntity<NivesLeituras> addNiveis(@RequestBody NivesLeituras niveis){	
		NivesLeituras niveisLeituras = niveisLeiturasRepository.save(niveis);
		return new ResponseEntity<NivesLeituras>(niveisLeituras, HttpStatus.OK);	
	
	}
	
	@GetMapping(value="/relatorio", produces = MediaType.APPLICATION_PDF_VALUE)	
	public ResponseEntity<byte[]> downloadRelatorio(HttpServletRequest request) throws Exception{
		
		byte[] pdf = serviceRelatorio.gerarRelatorio("relatorio", new HashMap<String, Object>(),request.getServletContext());		
			
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=relstufa.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdf);

	}
	
	
	@GetMapping(value = "/data", produces = "application/json")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public ResponseEntity<List<NivesLeituras>>consultapordataQ(){	
		
		Date data = new Date();		
		List<NivesLeituras> nLeituras = (List<NivesLeituras>)niveisLeiturasRepository.findByDate(data);
		System.out.print(data);
		return new ResponseEntity<List<NivesLeituras>>(nLeituras, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping(value="/relatorio2", produces ="application/text")
	public ResponseEntity<String> downloadRelatorio2(HttpServletRequest request) throws Exception{
		
		byte[] pdf = serviceRelatorio.gerarRelatorio("relatorio", new HashMap<String, Object>(),request.getServletContext());		
		
		String base64Pdf = "data:application/pdf;charset=utf-8;base64," + Base64.encodeBase64String(pdf);
		return new ResponseEntity<String>(base64Pdf, HttpStatus.OK);


	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
