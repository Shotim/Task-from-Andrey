import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Util {

    public static List<MaterialStock> countRealStocks(List<MaterialStock> basicMaterialStocks, List<MovedStock> movedMaterialStocks) {
        //todo
        movedMaterialStocks.stream().parallel()
                .forEach(movedStock -> method(movedStock, basicMaterialStocks));
        return basicMaterialStocks;
    }

    public static void method(MovedStock movedStock, List<MaterialStock> basicMaterialStock) {

        BigDecimal resultSum;
        int elemIndex = Collections.binarySearch(basicMaterialStock, movedStock, (first, second) ->
                ((MaterialStock) first).getId().compareTo(((MovedStock) second).getId()));

        MaterialStock elem = basicMaterialStock.get(elemIndex);
        switch (movedStock.getMoveType()) {
            case "201":
            case "202":
            case "261":
            case "262":
            case "311":
            case "312":
                resultSum = elem.getUnrestrictedStock().add(movedStock.getMovTypeSumResult());
                elem.setUnrestrictedStock(resultSum);
                break;
            case "323":
            case "324":
                resultSum = elem.getQualityInspectionStock().add(movedStock.getMovTypeSumResult());
                elem.setQualityInspectionStock(resultSum);
                break;
            case "325":
            case "326":
                resultSum = elem.getBlockedStock().add(movedStock.getMovTypeSumResult());
                elem.setBlockedStock(resultSum);
                break;
            default:
                break;
        }
    }
}
