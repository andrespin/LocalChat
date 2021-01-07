public class MassiveProcess {

    public static Integer [] takeAfterFour(Integer mas[]){
        return createNewMassive(mas, countNotNullElems(deleteBeforeFour(mas)));
    }

    public static void printMassive(Integer [] mas){
        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");
        }
    }

    private static Integer [] deleteBeforeFour(Integer mas[]){
        boolean On = false;
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] == 4){
                On = true;
                mas[i] = null;
            }
            if (!On){
                mas[i] = null;
            }
        }
        if(!On) throw new RuntimeException();
        return mas;
    }

    private static Integer countNotNullElems(Integer mas[]){
        int count = 0;
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] != null){
                count++;
            }
        }
        return count;
    }

    private static Integer [] createNewMassive(Integer mas[], Integer count){
        Integer[] mas1 = new Integer[count];
        int j = 0;
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] != null){
                mas1[j] = mas[i];
                j++;
            }
        }
        return mas1;
    }

    public static boolean checkIfContainsOneAndFour(Integer [] mas){
        boolean hasFour = false;
        boolean hasOne = false;
        for (Integer ma : mas) {
            if (ma == 1) {
                hasOne = true;
            }
            if (ma == 4) {
                hasFour = true;
            }
        }
        return (hasFour && hasOne);
    }
}
