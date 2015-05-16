package fr.tungnguyen.hibernate;

import static java.util.Calendar.NOVEMBER;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import fr.tungnguyen.hibernate.model.Student;
import fr.tungnguyen.hibernate.util.HibernateUtil;

/**
 * test basic hiberante oprations
 * @author tungnguyen
 *
 */
public class BasicOperationTest {

    private static final Date BIRTHDAY = new GregorianCalendar(1990, NOVEMBER, 27).getTime();
    private static final String LAST_NAME = "TRAN";
    private static final String FIRST_NAME = "Phuong Linh";

    /**
     * Test save operation
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        executeSaveOperation(session);

        @SuppressWarnings("unchecked")
        List<Student> allStudent = session.createQuery("from Student").list();
        assertEquals(1, allStudent.size());

        Student student = allStudent.get(0);
        assertEquals(FIRST_NAME, student.getFirstName());
        assertEquals(LAST_NAME, student.getLastName());
        assertEquals(BIRTHDAY, student.getBirthDay());

        HibernateUtil.shutdown();
    }

    private void executeSaveOperation(final Session session) {
        session.beginTransaction();

        Student student = new Student();
        student.setFirstName(FIRST_NAME);
        student.setLastName(LAST_NAME);
        student.setBirthDay(BIRTHDAY);

        session.save(student);

        session.getTransaction().commit();
    }
}
