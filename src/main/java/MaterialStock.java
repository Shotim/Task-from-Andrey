import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialStock {

    private static Integer staticId = 0;
    private Integer id;
    private BigDecimal unrestrictedStock;
    private BigDecimal qualityInspectionStock;
    private BigDecimal blockedStock;

    //    private String baseUom;
    public MaterialStock() {
        this.id = ++staticId;
        this.unrestrictedStock = BigDecimal.valueOf(Math.random() * 1000);
        this.qualityInspectionStock = BigDecimal.valueOf(Math.random() * 1000);
        this.blockedStock = BigDecimal.valueOf(Math.random() * 1000);
    }

}
