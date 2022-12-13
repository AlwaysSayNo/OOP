package test.java.Lab_2;

import Lab_2.model.Device;
import Lab_2.model.Types;
import Lab_2.dom.DocumentParserDOM;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DocumentParserDOMTest {

    @Test
    @SneakyThrows
    public void parseDocumentTest() {
        DocumentParserDOM documentParser = new DocumentParserDOM();
        var computer = documentParser.parseXml(
                "src/main/resources/Lab_2/computer.xml",
                "src/main/resources/Lab_2/computer.xsd");

        List<Device> devices = computer.getDevices();

        assertThat(devices.size()).isEqualTo(3);

        Device d1 = Device.builder()
                .id(1)
                .name("Intel Core i7-10700K")
                .origin("USA")
                .price(850)
                .critical(true)
                .types(Types.builder()
                        .peripheral(false)
                        .energyConsumption(220)
                        .cooler(true)
                        .type("CPU")
                        .port("PCI-E")
                        .build())
                .build();

        Device d2 = Device.builder()
                .id(2)
                .name("Nvidia GeForce 1080-TI")
                .origin("USA")
                .price(900)
                .critical(false)
                .types(Types.builder()
                        .peripheral(false)
                        .energyConsumption(250)
                        .cooler(true)
                        .type("GPU")
                        .port("PCI-E")
                        .build())
                .build();

        Device d3 = Device.builder()
                .id(3)
                .name("Asus Mouse")
                .origin("Taiwan")
                .price(50)
                .critical(false)
                .types(Types.builder()
                        .peripheral(true)
                        .energyConsumption(5)
                        .cooler(false)
                        .type("IO")
                        .port("USB")
                        .build())
                .build();

        assertThat(devices.get(0)).isEqualTo(d1);
        assertThat(devices.get(1)).isEqualTo(d2);
        assertThat(devices.get(2)).isEqualTo(d3);
    }
}
