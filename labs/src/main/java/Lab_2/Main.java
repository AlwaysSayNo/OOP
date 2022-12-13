package Lab_2;

import Lab_2.dom.DocumentParserDOM;

public class Main {
    public static void main(String[] args) throws Exception {
        // DOM
        var domParser = new DocumentParserDOM();
        var computer = domParser.parseXml("src/main/resources/Lab_2/computer.xml", "src/main/resources/Lab_2/computer.xsd");
        System.out.println(computer);

        // StAX
        var staxParser = new DocumentParserStAX();
        var computer2 = staxParser.parseXml("src/main/resources/Lab_2/computer.xml");
        System.out.println(computer2);

        // SAX
        var saxParser = new DocumentParserStAX();
        var computer3 = saxParser.parseXml("src/main/resources/Lab_2/computer.xml");
        System.out.println(computer3);
    }
}
