import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class Util {

    private int movedStockListCounter;

    public List<MaterialStock> countRealStocks(List<MaterialStock> basicMaterialStocks, List<MovedStock> movedMaterialStocks) {
        basicMaterialStocks.sort(Comparator.comparing(element -> {
            StockId stockId = element.getId();
            return stockId.getPlant() + " " + stockId.getStorageLocation() + " " + stockId.getMaterial();
        }));

        movedMaterialStocks.sort(Comparator.comparing(element -> {
            StockId stockId = element.getId();
            return stockId.getPlant() + " " + stockId.getStorageLocation() + " " + stockId.getMaterial();
        }));

        basicMaterialStocks.stream()
                .forEach(materialStock -> modifyMaterialStockByMovedStock(materialStock, movedMaterialStocks));

        this.movedStockListCounter = 0;

        return basicMaterialStocks;
    }

    private void modifyMaterialStockByMovedStock(MaterialStock materialStock, List<MovedStock> movedStocks) {
        while (movedStockListCounter < movedStocks.size() && movedStocks.get(movedStockListCounter).getId().equals(materialStock.getId())) {
            modifyMaterialStock(materialStock, movedStocks.get(movedStockListCounter));
            movedStockListCounter++;
        }
    }

    private void modifyMaterialStock(MaterialStock materialStock, MovedStock movedStock) {

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
