package arabian;

import java.util.TreeMap;

public class Converter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();

    public Converter() {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);
        romanKeyMap.put('D', 500);
        romanKeyMap.put('M', 1000);

        arabianKeyMap.put(1000, "M");
        arabianKeyMap.put(900, "CM");
        arabianKeyMap.put(500, "D");
        arabianKeyMap.put(400, "CD");
        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");

    }

    public boolean isRoman(String number) {
        if (number == null || number.isEmpty()) {
            return false;
        }

        // Регулярное выражение для стандартного и нестандартного формата римских чисел
        String romanPattern = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        return number.matches(romanPattern);
    }

    public String intToRoman(int number) {


        StringBuilder roman = new StringBuilder();

        while (number > 0) {
            Integer key = arabianKeyMap.floorKey(number);
            String romanNumeral = arabianKeyMap.get(key);
            roman.append(romanNumeral);
            number -= key;
        }

        return roman.toString();
    }

    public int romanToInt(String s) {
        if (!isRoman(s)) {
            throw new IllegalArgumentException("Неверное римское число");
        }

        int result = 0;
        int i = 0;

        while (i < s.length()) {
            if (i + 1 < s.length() && romanKeyMap.get(s.charAt(i)) < romanKeyMap.get(s.charAt(i + 1))) {
                result += romanKeyMap.get(s.charAt(i + 1)) - romanKeyMap.get(s.charAt(i));
                i += 2;
            } else {
                result += romanKeyMap.get(s.charAt(i));
                i++;
            }
        }

        return result;
    }
}