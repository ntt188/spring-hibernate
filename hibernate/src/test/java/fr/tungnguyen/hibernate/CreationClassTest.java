package fr.tungnguyen.hibernate;

import static java.util.Calendar.AUGUST;
import static java.util.Calendar.NOVEMBER;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import fr.tungnguyen.hibernate.model.Clazz;
import fr.tungnguyen.hibernate.model.Student;
import fr.tungnguyen.hibernate.util.HibernateUtil;

/**
 * Test creation of a class
 * @author tungnguyen
 *
 */
public class CreationClassTest {

    /**
     * Test creation of a clazz
     *
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        createClazz(session);

        @SuppressWarnings("unchecked")
        List<Clazz> allClazz = session.createQuery("from Clazz").list();
        assertEquals(1, allClazz.size());
        assertEquals(2, allClazz.get(0).getStudents().size());
    }

    private void createClazz(final Session session) {
        session.beginTransaction();

        Clazz clazz = new Clazz("superhero");

        clazz.addStudent(createStudent("Phuong Linh", "Tran", new GregorianCalendar(1990, NOVEMBER, 27).getTime()));
        clazz.addStudent(createStudent("The Tung", "Nguyen", new GregorianCalendar(1983, AUGUST, 18).getTime()));

        session.save(clazz);

        session.getTransaction().commit();
    }

    private Student createStudent(final String firstName, final String lastName, final Date birthDay) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setBirthDay(birthDay);

        return student;
    }

}
