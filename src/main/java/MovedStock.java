import lombok.Data;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Data
public class MovedStock {

    private List<String> types = Arrays.asList("201", "202", "261", "262", "311", "312", "323", "324", "325", "326");
    private static int idStatic = 0;

    private Integer id;
    private String batch;
    private String moveType;
    private BigDecimal movTypeSumResult;

    public MovedStock() {
        this.batch = "";
        int index = (int) (Math.random() * types.size());

        this.id = ++idStatic;
        this.moveType = types.get(index);
        this.movTypeSumResult = BigDecimal.valueOf(Math.random() * 1000);
    }
}