package fr.tungnguyen.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Class
 *
 */
@Entity
public class Clazz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "clazz")
    private Set<Student> students;

    /**
     * Constructeur
     */
    public Clazz() {
        students = new HashSet<>();
    }

    /**
     * Constructeur
     * @param clazzName class's name
     */
    public Clazz(final String clazzName) {
        this();
        name = clazzName;
    }

    /**
     * Getter pour id
     * @return la valeur du champ id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter pour id
     * @param id La nouvelle valeur du champ id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Getter pour name
     * @return la valeur du champ name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pour name
     * @param name La nouvelle valeur du champ name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter pour students
     * @return la valeur du champ students
     */
    public Set<Student> getStudents() {
        return students;
    }

    /**
     * Setter pour students
     * @param students La nouvelle valeur du champ students
     */
    public void setStudents(final Set<Student> students) {
        this.students = students;
    }

    /**
     * Add a student to class
     * @param student student to be added
     */
    public void addStudent(final Student student) {
        students.add(student);
        student.setClazz(this);
    }
}
