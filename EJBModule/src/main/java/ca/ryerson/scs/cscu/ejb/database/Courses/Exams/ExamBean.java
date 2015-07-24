package ca.ryerson.scs.cscu.ejb.database.Courses.Exams;

import ca.ryerson.scs.cscu.entities.Exam;

import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-23.
 */
public interface ExamBean {
    void removeExam();
    List<Exam> getAllExams();
    Exam getExamById();
    void addExam();
}
