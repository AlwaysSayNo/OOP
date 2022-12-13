package Lab_2.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Types {
    private boolean peripheral;
    private int energyConsumption;
    private boolean cooler;
    private String type;
    private String port;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Types types = (Types) o;
        return peripheral == types.peripheral &&
                energyConsumption == types.energyConsumption &&
                cooler == types.cooler &&
                Objects.equals(type, types.type) &&
                Objects.equals(port, types.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peripheral, energyConsumption, cooler, type, port);
    }
}
