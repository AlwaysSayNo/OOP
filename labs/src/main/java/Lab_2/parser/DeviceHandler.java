package Lab_2.parser;

import Lab_2.model.Device;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class DeviceHandler extends DefaultHandler {
    private String valueOfElement;
    private List<Device> deviceList = new ArrayList<>();

    @Override
    public void startElement(String url, String name, String attributeName, Attributes attributes) {
        switch (attributeName) {
            case Constants.DEVICE -> {
                main.java.Lab_2.model.Device device = new main.java.Lab_2.model.Device();
                deviceList.add(device);
            }
            case Constants.TYPES -> {
                Types types = new Types();
                getLastDevice().setTypes(types);
            }
        }
    }

    @Override
    public void endElement(String uri, String name, String attributeName) {
        switch (attributeName) {
            case Constants.ID -> getLastDevice().setId(Integer.parseInt(valueOfElement));
            case Constants.NAME -> getLastDevice().setName(valueOfElement);
            case Constants.ORIGIN -> getLastDevice().setOrigin(valueOfElement);
            case Constants.PRICE -> getLastDevice().setPrice(Integer.parseInt(valueOfElement));
            case Constants.CRITICAL -> getLastDevice().setCritical(Boolean.parseBoolean(valueOfElement));
            case Constants.PERIPHERAL -> getLastDevice().getTypes().setPeripheral(Boolean.parseBoolean(valueOfElement));
            case Constants.ENERGY_CONSUMPTION ->
                    getLastDevice().getTypes().setEnergyConsumption(Short.parseShort(valueOfElement));
            case Constants.COOLER -> getLastDevice().getTypes().setCooler(Boolean.parseBoolean(valueOfElement));
            case Constants.TYPE -> getLastDevice().getTypes().setType(valueOfElement);
            case Constants.PORT -> getLastDevice().getTypes().setPort(valueOfElement);
        }
    }

    public void setField(String attributeName, String str, Map<String, String> attributes) {
        switch (attributeName) {
            case Constants.DEVICE -> {
                main.java.Lab_2.model.Device device = new main.java.Lab_2.model.Device();
                deviceList.add(device);
            }
            case Constants.NAME -> getLastDevice().setName(str);
            case Constants.ID -> getLastDevice().setId(Integer.parseInt(str));
            case Constants.ORIGIN -> getLastDevice().setOrigin(str);
            case Constants.PRICE -> getLastDevice().setPrice(Integer.parseInt(str));
            case Constants.CRITICAL -> getLastDevice().setCritical(Boolean.parseBoolean(str));
            case Constants.PERIPHERAL -> getLastDevice().getTypes().setPeripheral(Boolean.parseBoolean(str));
            case Constants.ENERGY_CONSUMPTION -> getLastDevice().getTypes().setEnergyConsumption(Short.parseShort(str));
            case Constants.COOLER -> getLastDevice().getTypes().setCooler(Boolean.parseBoolean(str));
            case Constants.PORT -> getLastDevice().getTypes().setPort(str);
            case Constants.TYPE -> getLastDevice().getTypes().setType(str);
            case Constants.TYPES -> {
                Types types = new Types();
                getLastDevice().setTypes(types);
            }

        }
    }

    public List<main.java.Lab_2.model.Device> getDeviceList() {
        return deviceList;
    }

    @Override
    public void startDocument() throws SAXException {
        deviceList = new ArrayList<>();
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        valueOfElement = new String(ch, start, length);
    }

    private main.java.Lab_2.model.Device getLastDevice() {
        return deviceList.get(deviceList.size() - 1);
    }

    public String getName() {
        return Constants.DEVICE;
    }



    public main.java.Lab_2.model.Device createDevice(Element element) {
        int id = Integer.parseInt(element.getAttribute(ID));
        String name = getByTag(element, NAME);
        String origin = getByTag(element, ORIGIN);
        int price = Integer.parseInt(getByTag(element, PRICE));
        boolean critical = Boolean.parseBoolean(getByTag(element, CRITICAL));

        var typesElement = (Element) element.getElementsByTagName(TYPES).item(0);

        return new main.java.Lab_2.model.Device(id, name, origin, price, critical, createTypes(typesElement));
    }

    private Types createTypes(Element element) {
        boolean peripheral = Boolean.parseBoolean(getByTag(element, PERIPHERAL));
        int energyConsumption = Integer.parseInt(getByTag(element, ENERGY_CONSUMPTION));
        boolean cooler = Boolean.parseBoolean(getByTag(element, COOLER));
        String type = getByTag(element, TYPE);
        String port = getByTag(element, PORT);

        return new Types(peripheral, energyConsumption, cooler, type, port);
    }

    private String getByTag(Element element, String tag) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

}