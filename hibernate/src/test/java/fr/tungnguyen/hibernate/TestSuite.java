package fr.tungnguyen.hibernate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Global TestSuite
 * @author tungnguyen
 */
@RunWith(Suite.class)
@SuiteClasses({ BasicOperationTest.class, CreationClassTest.class })
public class TestSuite {

}
