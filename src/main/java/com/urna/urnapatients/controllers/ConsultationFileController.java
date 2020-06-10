package com.urna.urnapatients.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.urna.urnapatients.models.ConsultationFile;
import com.urna.urnapatients.services.ConsultationFileService;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/consultationfile")
public class ConsultationFileController {

	ConsultationFileService consultationFileService;

	public ConsultationFileService getConsultationFileService() {
		return consultationFileService;
	}
	@Autowired
	public void setConsultationFileService(ConsultationFileService consultationFileService) {
		this.consultationFileService = consultationFileService;
	}

	@PostMapping("/file/{id}")
	public @ResponseBody ConsultationFile saveConsultationFile(@RequestParam("file") MultipartFile file,
			@PathVariable String id) throws IOException {
		ConsultationFile consultationFile = new ConsultationFile();
		consultationFile.setConsultationId(Long.parseLong(id));
		consultationFile.setFile(file.getBytes());
		ConsultationFile savedObj = consultationFileService.save(consultationFile, file);
		savedObj.setFile(null);
		return savedObj;
	}

	@DeleteMapping("/file/{id}")
	public void deleteConsultationFile(@PathVariable String id) {
		ConsultationFile consultationFile = new ConsultationFile();
		consultationFile.setId(Long.parseLong(id));
		consultationFileService.delete(consultationFile);
	}

	@GetMapping("/file/{id}")
	public @ResponseBody ResponseEntity<ByteArrayResource> getConsultationFile(@PathVariable String id)
			throws IOException {
		ConsultationFile consultationFile = new ConsultationFile();
		consultationFile.setId(Long.parseLong(id));
		Optional<ConsultationFile> consultationFileo = consultationFileService.findById(consultationFile);
		if (consultationFileo.isPresent()) {
			ConsultationFile consultationFile2 = consultationFileo.get();
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(consultationFile2.getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + consultationFile2.getFileName() + "\"")
					.body(new ByteArrayResource(consultationFile2.getFile()));
		}
		return (ResponseEntity<ByteArrayResource>) ResponseEntity.notFound();
	}
}
