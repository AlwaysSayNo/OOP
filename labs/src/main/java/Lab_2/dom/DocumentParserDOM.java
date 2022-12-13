package Lab_2.dom;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DocumentParserDOM {

    public Computer parseXml(String xmlPath, String xsdPath) throws ParserConfigurationException, IOException, SAXException {
        var dbf = DocumentBuilderFactory.newInstance();
        var db = dbf.newDocumentBuilder();

        var doc = db.parse(new File(xmlPath));
        var deviceNodeList = doc.getElementsByTagName(DEVICE);

        var devices = new ArrayList<Device>();

        var deviceHandler = new DeviceHandler();

        for (int i = 0; i < deviceNodeList.getLength(); i++) {
            var node = deviceNodeList.item(i);

            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;

            var element = (Element) node;
            devices.add(deviceHandler.createDevice(element));
        }

        return new Computer(devices);
    }


}
