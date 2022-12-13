package test.java.Lab_2;

import Lab_2.parser.XsdValidityChecker;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XsdValidityCheckerTest {

    @Test
    public void xmlShouldBeValidToXsdTest() {
        boolean valid = XsdValidityChecker.validateXmlSchemaToXsd(
                "src/main/resources/Lab_2/computer.xml", "src/main/resources/Lab_2/computer.xsd");

        assertThat(valid).isTrue();
    }
}
