package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.School;
import app.core.entities.Student;
import app.core.services.SchoolService;

@RestController
@RequestMapping("/api")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	@PostMapping("/add/school")
	public int addSchool(@RequestBody School school, @RequestHeader String token) {
		try {
			return this.schoolService.addSchool(school);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
	}

	@PostMapping("/add/student")
	public int addStudent(@RequestBody Student student, @RequestHeader String token) {
		try {
			return this.schoolService.addStudent(student);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
	}
}
