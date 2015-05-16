package fr.tungnguyen.hibernate;

import static java.util.Calendar.NOVEMBER;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.HibernateException;
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
    public void testSaveError() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            executeSaveOperation(session);
            fail("HibernateException have to be throwed");
        } catch (HibernateException e) {
            // OK
        }
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
