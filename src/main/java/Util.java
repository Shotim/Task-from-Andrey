import java.math.BigDecimal;
import java.util.List;

public class Util {

    public static List<MaterialStock> countRealStocks(List<MaterialStock> basicMaterialStocks, List<MovedStock> movedMaterialStocks) {
        movedMaterialStocks.stream().parallel()
                .forEach(movedStock -> modifyMaterialStockByMovedStock(movedStock, basicMaterialStocks));
        return basicMaterialStocks;
    }

    public static void modifyMaterialStockByMovedStock(MovedStock movedStock, List<MaterialStock> basicMaterialStock) {

        basicMaterialStock.stream().forEach(element -> {
            if (element.getId().equals(movedStock.getId())) {
                modifyMaterialStock(element, movedStock);
            }
        });
    }

    private static void modifyMaterialStock(MaterialStock materialStock, MovedStock movedStock) {

        BigDecimal resultSum;

        switch (movedStock.getMoveType()) {
            case "201":
            case "202":
            case "261":
            case "262":
            case "311":
            case "312":
                resultSum = materialStock.getUnrestrictedStock().add(movedStock.getMovTypeSumResult());
                materialStock.setUnrestrictedStock(resultSum);
                break;
            case "323":
            case "324":
                resultSum = materialStock.getQualityInspectionStock().add(movedStock.getMovTypeSumResult());
                materialStock.setQualityInspectionStock(resultSum);
                break;
            case "325":
            case "326":
                resultSum = materialStock.getBlockedStock().add(movedStock.getMovTypeSumResult());
                materialStock.setBlockedStock(resultSum);
                break;
            default:
                break;
        }

    }
}
