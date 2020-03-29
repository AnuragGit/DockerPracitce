
package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DockerStudentServiceImpl implements IDokerStudentService {

	private IDockerStudent userRepo;

	@Autowired
	public void setUserRepo(IDockerStudent userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public Iterable<DockerStudent> listAllStudents() {
		return userRepo.findAll();
	}

	@Override
	public DockerStudent getStudentById(long id) {
		return userRepo.findOne(id);
	}

	@Override
	public DockerStudent saveStudent(DockerStudent user) {
		return userRepo.save(user);
	}

	@Override
	public void deleteStudent(long id) {

		userRepo.delete(id);

	}

}
