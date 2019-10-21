import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    public List<MaterialStock> countRealStocks(List<MaterialStock> basicMaterialStocks, List<MovedStock> movedMaterialStocks) {

        Map<StockId, MaterialStock> materialStockMap = basicMaterialStocks.stream()
                .collect(Collectors.toMap(MaterialStock::getId, materialStock -> materialStock));
        movedMaterialStocks
                .forEach(element -> modifyMaterialStock(element, materialStockMap.get(element.getId())));
        return basicMaterialStocks;
    }

    private void modifyMaterialStock(MovedStock movedStock, MaterialStock materialStock) {

        switch (movedStock.getMoveType()) {
            case "201":
            case "202":
            case "261":
            case "262":
            case "311":
            case "312":
                BigDecimal unrestrictedSum = materialStock.getUnrestrictedStock().add(movedStock.getMovTypeSumResult());
                materialStock.setUnrestrictedStock(unrestrictedSum);
                break;
            case "323":
            case "324":
                BigDecimal inspectionSum = materialStock.getQualityInspectionStock().add(movedStock.getMovTypeSumResult());
                materialStock.setQualityInspectionStock(inspectionSum);
                break;
            case "325":
            case "326":
                BigDecimal blockedSum = materialStock.getBlockedStock().add(movedStock.getMovTypeSumResult());
                materialStock.setBlockedStock(blockedSum);
                break;
            default:
                break;
        }
    }

    private BigDecimal toBigDecimal(String str) {
        return new BigDecimal(Double.parseDouble(str));
    }
}
