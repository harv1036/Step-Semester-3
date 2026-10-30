public class Week7_Abstraction_Interface_Category_B_Practice_P2_WarehouseLabelPrinter {

    public static void main(String[] args) {
        PackageBox p = new PackageBox("TRK-88");
        System.out.println(p.printLabel());

        Invoice i = new Invoice("INV-42");
        System.out.println(i.printLabel());

        printAll(new Printable[]{ p, i });
    }

    static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }
}

interface Printable {
    String printLabel();
}

class PackageBox implements Printable {
    private final String trackingId;

    public PackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

class Invoice implements Printable {
    private final String invoiceNumber;

    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}
