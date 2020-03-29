
package com.app;

public interface IDokerStudentService {

	public Iterable<DockerStudent> listAllStudents();

	public DockerStudent getStudentById(long id);

	public DockerStudent saveStudent(DockerStudent user);

	public void deleteStudent(long id);

}
