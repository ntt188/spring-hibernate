package fr.tungnguyen.hibernate.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Class HibernateUtil
 * @author tungnguyen
 *
 */
public class HibernateUtil {

    private static final Logger LOGGER = LogManager.getLogger(HibernateUtil.class);

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Private constructeur
     */
    private HibernateUtil() {
    }

    private static final SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build());
        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory creation failed", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Return the sessionFactory
     * @return the sessionFactory
     */
    public static final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Shutdown the sessionFactory
     */
    public static final void shutdown() {
        sessionFactory.close();
    }
}
