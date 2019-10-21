import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovedStock {

    private Integer id;
    private String batch;
    private String moveType;
    private BigDecimal movTypeSumResult;

}