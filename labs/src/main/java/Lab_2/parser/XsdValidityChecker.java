package Lab_2.parser;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;

public class XsdValidityChecker {

    public static boolean validateXmlSchemaToXsd(String xmlPath, String xsdPath) {
        try {
            validateXMLSchema(xsdPath, xmlPath);
        } catch (IOException | SAXException e) {
            throw new IllegalStateException("XML file is not valid", e);
        }
        return true;
    }

    private static void validateXMLSchema(String xsdPath, String xmlPath) throws SAXException, IOException {
        var factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        var schema = factory.newSchema(new File(xsdPath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlPath)));
    }
}
