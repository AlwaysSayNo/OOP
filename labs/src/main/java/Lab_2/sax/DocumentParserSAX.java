package Lab_2.sax;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class DocumentParserSAX {

    public main.java.Lab_2.model.Computer parseXml(String xmlPath) throws SAXException, IOException, ParserConfigurationException {
        var xml = new File(xmlPath);

        var saxParserFactory = SAXParserFactory.newInstance();
        var saxParser = saxParserFactory.newSAXParser();

        var deviceHandler = new main.java.Lab_2.parser.DeviceHandler();
        saxParser.parse(xml, deviceHandler);

        return new main.java.Lab_2.model.Computer(deviceHandler.getDeviceList());
    }
}