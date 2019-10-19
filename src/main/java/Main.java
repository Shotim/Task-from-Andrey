import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<MaterialStock> materialStocks = new ArrayList<>();
        List<MovedStock> movedStocks = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            materialStocks.add(new MaterialStock());
            movedStocks.add(new MovedStock());
        }

        Util.countRealStocks(materialStocks, movedStocks);
    }
}
