public class Week7_Abstraction_Interface_Category_B_Assignment_P3_BackyardToolshedRoutine {

    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        System.out.println(c.use());

        Pruner p = new Pruner();
        System.out.println(p.use());
    }
}

abstract class GardenTool {
    public abstract String use();
}

class CuttingTool extends GardenTool {
    public CuttingTool() {
    }

    @Override
    public String use() {
        return "Using the tool in the garden, blade sharpened first";
    }
}

class Pruner extends CuttingTool {
    public Pruner() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}
