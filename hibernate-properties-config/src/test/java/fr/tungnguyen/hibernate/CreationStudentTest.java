package fr.tungnguyen.hibernate;

import static java.util.Calendar.AUGUST;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import fr.tungnguyen.hibernate.model.Student;
import fr.tungnguyen.hibernate.util.HibernateUtil;

/**
 * Test creation of a class
 * @author tungnguyen
 *
 */
public class CreationStudentTest {

    private static final Date BIRTHDAY = new GregorianCalendar(1990, AUGUST, 15).getTime();
    private static final String LAST_NAME = "Potter";
    private static final String FIRST_NAME = "Harry";

    /**
     * Test creation of a clazz
     *
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Long idStudent = createStudent(session);

        @SuppressWarnings("unchecked")
        List<Student> allStudent = session.createQuery("from Student").list();
        assertEquals(1, allStudent.size());

        Student student = allStudent.get(0);
        assertEquals(idStudent.longValue(), student.getId().longValue());
        assertEquals(FIRST_NAME, student.getFirstName());
        assertEquals(LAST_NAME, student.getLastName());
        assertEquals(BIRTHDAY, student.getBirthDay());
    }

    private Long createStudent(final Session session) {
        session.beginTransaction();

        Student student = new Student();
        student.setFirstName(FIRST_NAME);
        student.setLastName(LAST_NAME);
        student.setBirthDay(BIRTHDAY);

        Long idStudent = (Long)session.save(student);

        session.getTransaction().commit();

        return idStudent;
    }
}
