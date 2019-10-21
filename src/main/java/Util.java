import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    public List<MaterialStock> countRealStocks(List<MaterialStock> basicMaterialStocks, List<MovedStock> movedMaterialStocks) {

        Map<StockId, MaterialStock> materialStockMap = basicMaterialStocks.stream()
                .collect(Collectors.toMap(MaterialStock::getId, materialStock -> materialStock));
        movedMaterialStocks.stream()
                .forEach(element -> modifyMaterialStock(element, materialStockMap.get(element.getId())));
        return basicMaterialStocks;
    }

    private void modifyMaterialStock(MovedStock movedStock, MaterialStock materialStock) {

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

    private BigDecimal toBigDecimal(String str) {
        return new BigDecimal(Double.parseDouble(str));
    }
}
