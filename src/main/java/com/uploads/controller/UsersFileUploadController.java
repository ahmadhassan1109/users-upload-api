package com.uploads.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;
import com.uploads.service.UserUploadService;

@Controller
@RequestMapping("/api/users/uploads")
public class UsersFileUploadController {

	@Autowired
	private UserUploadService userUploadService;

	@PostMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> uploadFile(@NotNull @RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
		}

		try {
			userUploadService.processCSVFile(file);
			return new ResponseEntity<>("CSV file uploaded and users saved successfully", HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>("Error uploading CSV file", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}