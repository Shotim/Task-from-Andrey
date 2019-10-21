import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialStock {

    private Integer id;
    private BigDecimal unrestrictedStock;
    private BigDecimal qualityInspectionStock;
    private BigDecimal blockedStock;

}
