package ca.ryerson.scs.cscu.ejb.database.Courses.Exams;

import ca.ryerson.scs.cscu.entities.Course;
import ca.ryerson.scs.cscu.entities.Exam;

import java.util.List;

/**
 * Created by mitchellmohorovich on 15-07-23.
 */
public interface ExamBean {
    void removeExam();
    List<Exam> getAllExams();
    Exam getExamById(int id);
    Exam addExam(Exam e);
    void persistExam(int id);
    Exam setExamType(int id, Exam.Type type);
    Exam setExamYear(int id, short year);
    Exam setExamSemester(int id, Exam.Semester semester);
    Exam setExamOwnerCourse(int id, Course course);
    void deleteExam(int id);
}
