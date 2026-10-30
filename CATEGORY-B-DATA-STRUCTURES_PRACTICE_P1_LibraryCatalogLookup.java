// Problem 1: Library Catalog Lookup
// Finds a book's title by ISBN in a catalog pre-sorted by ISBN, using
// binary search to leverage the sorted order.
class LibraryCatalogLookup {

    // Time: O(log n), additional space: O(1).
    static String findBook(String[][] catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String isbn = catalog[mid][0];
            int cmp = isbn.compareTo(targetIsbn);

            if (cmp == 0) {
                return catalog[mid][1];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        String[][] catalog = {
            { "0001112223", "Introduction to Algebra" },
            { "0002223334", "Beginning Python" },
            { "0003334445", "Classic Mythology" },
            { "0004445556", "Data and Society" },
            { "0005556667", "European History" }
        };

        System.out.println(findBook(catalog, "0003334445") + " | Expected: Classic Mythology");
        System.out.println(findBook(catalog, "0009998887") + " | Expected: Not Found");
    }
}
