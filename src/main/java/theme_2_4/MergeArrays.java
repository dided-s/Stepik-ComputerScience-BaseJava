package theme_2_4;

public class MergeArrays {

    /**
     * Merges two given sorted arrays into one
     *
     * @param a1 first sorted array
     * @param a2 second sorted array
     * @return new array containing all elements from a1 and a2, sorted
     */
    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] sortedArray = new int[a1.length + a2.length];
        int i1 = 0, i2 = 0, sortedIndex = 0;

        // Start1: Сортируем до конца одного из массивов
        while (i1 != a1.length && i2 != a2.length) {
            if (a1[i1] <= a2[i2]) {
                sortedArray[sortedIndex] = a1[i1];
                i1++;
            } else {
                sortedArray[sortedIndex] = a2[i2];
                i2++;
            }
            sortedIndex++;
        }
        // End1

        // В одном из массивов у нас остались элементы. Их нужно добавить в конец
        // Start2: Если в первом массиве остались элементы, то добавляем их в конец
        while (i1 < a1.length) {
            sortedArray[sortedIndex] = a1[i1];
            i1++;
            sortedIndex++;
        }
        // End2

        // Start3: Если во втором массиве остались элементы, то добавляем их в конец
        while (i2 < a2.length) {
            sortedArray[sortedIndex] = a2[i2];
            i2++;
            sortedIndex++;
        }
        // End3

        return sortedArray;
    }
}