package week05;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class School implements ISchool {

    // Resource
    @Autowired(required = true) //primary
    Klass class1;

    @Resource(name = "studentA")
    Student student;

    @Override
    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student);
    }

}
