package com.uploads.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uploads.model.User;
import com.uploads.repo.UserRepository;

@Service
public class UserUploadService {

	@Autowired
	private UserRepository userRepository;

	public void processCSVFile(MultipartFile file) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			List<User> users = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length >= 2) {
					User user = new User();
					user.setName(values[0]);
					user.setEmail(values[1]);
					user.setAddress(values[2]);
					users.add(user);
				}
			}
			userRepository.saveAll(users);
		}
	}

}
