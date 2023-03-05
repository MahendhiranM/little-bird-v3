package com.transport.buspass.controller;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PdfController {

	@PostMapping("/form/pdf")
	public ResponseEntity<InputStreamResource> createPdf(
			@RequestParam("pdfData")  String pdfData
			) {
		
		byte[] pdf = Base64.getDecoder().decode(pdfData);
		
		ByteArrayInputStream pdfFile = new ByteArrayInputStream(pdf);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition", "inline;file=asda.pdf");
		
		return ResponseEntity.ok()
				.headers(httpHeaders)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdfFile));
	}
}
