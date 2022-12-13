package Lab_2.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private int id;
    private String name;
    private String origin;
    private int price;
    private boolean critical;
    private Types types;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return price == device.price &&
                critical == device.critical &&
                Objects.equals(name, device.name) &&
                Objects.equals(origin, device.origin) &&
                Objects.equals(types, device.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, origin, price, critical, types);
    }
}
