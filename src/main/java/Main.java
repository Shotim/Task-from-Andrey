import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Util util = new Util();
        List<MaterialStock> materialStocks = new ArrayList<>();
        List<MovedStock> movedStocks = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            materialStocks.add(new MaterialStock());
            movedStocks.add(new MovedStock());
        }

        long start = System.currentTimeMillis();
        util.countRealStocks(materialStocks, movedStocks);
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }
}
