package app.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.School;
import app.core.entities.Student;
import app.core.exceptions.SchoolException;
import app.core.repos.SchoolRepo;
import app.core.repos.StudentRepo;

@Service
@Transactional
public class SchoolService {

	@Autowired
	private SchoolRepo schoolRepo;
	@Autowired
	private StudentRepo studentRepo;

	public boolean login(String email, String password) {
		return email.equals("aaa@mail") && password.equals("aaaPass");
	}

	public int addSchool(School school) {
		if (schoolRepo.existsById(school.getId())) {
			throw new SchoolException("addSchool failed - already exists");
		}
		school = schoolRepo.save(school);
		return school.getId();
	}

	public int addStudent(Student student) {
		if (studentRepo.existsById(student.getId())) {
			throw new SchoolException("addStudent failed - already exists");
		}
		student = studentRepo.save(student);
		return student.getId();
	}

}
