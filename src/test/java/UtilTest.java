import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UtilTest {

    Util util = new Util();

    private StockId createId(String plant, String storageLocation, String material) {
        StockId stockId = new StockId();
        stockId.setMaterial(material);
        stockId.setPlant(plant);
        stockId.setStorageLocation(storageLocation);
        return stockId;
    }

    private MaterialStock createMaterialStock(String plant, String storageLocation, String material, BigDecimal blockedStock, BigDecimal qualityStock, BigDecimal unrestrictedStock) {
        MaterialStock materialStock = new MaterialStock();
        materialStock.setId(createId(plant, storageLocation, material));
        materialStock.setBlockedStock(blockedStock);
        materialStock.setQualityInspectionStock(qualityStock);
        materialStock.setUnrestrictedStock(unrestrictedStock);

        return materialStock;
    }

    private MovedStock createMovedStock(String plant, String storageLocation, String material, String moveType, BigDecimal movTypeSumResult) {
        MovedStock movedStock = new MovedStock();
        movedStock.setId(createId(plant, storageLocation, material));
        movedStock.setMoveType(moveType);
        movedStock.setMovTypeSumResult(movTypeSumResult);

        return movedStock;
    }

    @Test
    public void countRealStocks_unrestrictedStock() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        MaterialStock materialStock = createMaterialStock("1", "1", "1", BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678));
        materialStocks.add(materialStock);

        List<MovedStock> movedStocks = new ArrayList<>();

        MovedStock movedStock = createMovedStock("1", "1", "1", "201", BigDecimal.valueOf(87.654321));
        movedStocks.add(movedStock);

        List<MaterialStock> expected = new ArrayList<>();

        MaterialStock materialExpected = createMaterialStock("1", "1", "1", BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(211.111101));
        expected.add(materialExpected);

        List<MaterialStock> actual = util.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_qualityInspectionStock() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        MaterialStock materialStock = createMaterialStock("1", "1", "1", BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0));
        materialStocks.add(materialStock);

        List<MovedStock> movedStocks = new ArrayList<>();

        MovedStock movedStock = createMovedStock("1", "1", "1", "323", BigDecimal.valueOf(87.654321));
        movedStocks.add(movedStock);

        List<MaterialStock> expected = new ArrayList<>();

        MaterialStock materialExpected = createMaterialStock("1", "1", "1", BigDecimal.valueOf(0), BigDecimal.valueOf(211.111101), BigDecimal.valueOf(0));
        expected.add(materialExpected);

        List<MaterialStock> actual = util.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_blockedStock() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        MaterialStock materialStock = createMaterialStock("1", "1", "1", BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        materialStocks.add(materialStock);

        List<MovedStock> movedStocks = new ArrayList<>();

        MovedStock movedStock = createMovedStock("1", "1", "1", "325", BigDecimal.valueOf(87.654321));
        movedStocks.add(movedStock);

        List<MaterialStock> expected = new ArrayList<>();

        MaterialStock materialExpected = createMaterialStock("1", "1", "1", BigDecimal.valueOf(211.111101), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        expected.add(materialExpected);

        List<MaterialStock> actual = util.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_unsortedLists() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        MaterialStock materialStockOne = createMaterialStock("1", "1", "1", BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        MaterialStock materialStockTwo = createMaterialStock("2", "2", "2", BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0));
        MaterialStock materialStockThree = createMaterialStock("3", "3", "3", BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678));

        materialStocks.add(materialStockThree);
        materialStocks.add(materialStockOne);
        materialStocks.add(materialStockTwo);

        List<MovedStock> movedStocks = new ArrayList<>();

        MovedStock movedStockOne = createMovedStock("1", "1", "1", "326", BigDecimal.valueOf(87.654321));
        MovedStock movedStockTwo = createMovedStock("2", "2", "2", "324", BigDecimal.valueOf(87.654321));
        MovedStock movedStockThree = createMovedStock("3", "3", "3", "262", BigDecimal.valueOf(87.654321));

        movedStocks.add(movedStockTwo);
        movedStocks.add(movedStockOne);
        movedStocks.add(movedStockThree);

        List<MaterialStock> expected = new ArrayList<>();

        MaterialStock materialExpectedOne = createMaterialStock("1", "1", "1", BigDecimal.valueOf(211.111101), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        MaterialStock materialExpectedTwo = createMaterialStock("2", "2", "2", BigDecimal.valueOf(0), BigDecimal.valueOf(211.111101), BigDecimal.valueOf(0));
        MaterialStock materialExpectedThree = createMaterialStock("3", "3", "3", BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(211.111101));

        expected.add(materialExpectedThree);
        expected.add(materialExpectedOne);
        expected.add(materialExpectedTwo);

        List<MaterialStock> actual = util.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }
}