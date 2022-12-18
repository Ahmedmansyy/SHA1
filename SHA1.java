// String to Array of char
// char[] catx = str.toCharArray();
// for (int i = 0; i < catx.length; i++) {
// System.out.println(catx[i]);
// }

// String arr[] = new String[catx.length];

// // String to binary
// for (int i = 0; i < catx.length; i++) {
// arr[i] = 0 + Integer.toBinaryString(str.charAt(i));
// }

// for (int i = 0; i < str.length(); i++) {
//     System.out.print(plantext(str)[i]);
// }

// String to Hex
// for (int i = 0; i < catx.length; i++) {
// System.out.println(str.charAt(i) + " " +
// Integer.toHexString(str.charAt(i)));
// }

package Assignment_5;

import java.util.Scanner;

public class SHA1 {
    static Scanner cin = new Scanner(System.in);

    public static String[] arr, arr2, arrdig;
    public static char[] catx, devidinginto, harmd, harmd2;
    public static String str, t, binarycalc, appendit, temp;
    public static int xx, calc, first = 0, second = 0, third = 0, fourth = 0,
            y = 1, mod = 1, Extend = 0, more = 0, temp2 = 0;;

    // public static String h0 = "01100111010001010010001100000001";
    // public static String h1 = "11101111110011011010101110001001";
    // public static String h2 = "10011000101110101101110011111110";
    // public static String h3 = "00010000001100100101010001110110";
    // public static String h4 = "11000011110100101110000111110000";

    // public static String A = h1;
    // public static String B = h2;
    // public static String C = h3;
    // public static String D = h4;

    public static int h1 = 0x67452301; // 01100111010001010010001100000001
    public static int h2 = 0xEFCDAB89; // 11101111110011011010101110001001
    public static int h3 = 0x98BADCFE; // 10011000101110101101110011111110
    public static int h4 = 0x10325476; // 00010000001100100101010001110110
    public static int h5 = 0xC3D2E1F0; // 11000011110100101110000111110000
    public static int k1 = 0x5A827999;
    public static int k2 = 0x6ED9EBA1;
    public static int k3 = 0x8F1BBCDC;
    public static int k4 = 0xCA62C1D6;

    public static int A = h1;
    public static int B = h2;
    public static int C = h3;
    public static int D = h4;
    public static int E = h5;

    public static int power(int base, int exponent) {
        int result = 0;

        if (exponent == 0) {
            result = 1;
        } else if (exponent == 1) {
            result = base;
        } else if (exponent > 1) {
            // result = 2147483647;
            result = base * power(base, exponent - 1);
        }
        return result;
    }

    public static int calcmod(int base, int power, int primee) {
        double pow = power / 5; // 103 /5 = 20.6 ...100/5 = 20.00
        int divi = power % 5; // 3 ... 0

        if (divi == 0) {
            for (int i = 0; i < (int) pow; i++) {
                int x = power(base, 5) % primee;
                y = (y * x) % primee;
            }
        } else {
            for (int i = 0; i < (int) pow; i++) {
                int x = power(base, 5) % primee;
                y = (y * x) % primee;
            }
            y = y * power(base, divi) % primee;
        }
        y = y % primee;
        return y;
    }

    public static String[] plantext(String str) {
        catx = str.toCharArray();
        arr = new String[catx.length];
        for (int i = 0; i < catx.length; i++) {
            arr[i] = 0 + Integer.toBinaryString(str.charAt(i));
        }
        return arr;
    }

    // public static int size = str.length() + 1;
    public static String[] add1(String[] arr, String str) {
        arr2 = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (i == str.length() - 1) {
                arr2[i] = arr[i] + 1;
            } else {
                arr2[i] = arr[i];
            }
        }
        return arr2;
    }

    public static void getlenghtstr(String str) {
        int S = str.length();
        calc = S * 8;
        xx = calc + 1;
        System.out.println(xx);
    }

    public static void how_many_ZeroAdd(int xx, String[] arr2) {
        if (xx < 448) {
            int e = 448 - xx;
            t = "";

            for (int i = 0; i < arr2.length; i++) {
                t += arr2[i];
            }
            for (int i = 0; i < e; i++) {
                t = t + "0";
            }

            System.out.println(t);

        } else {
            mod = xx % 512;
            how_many_ZeroAdd(mod, arr2);

        }
    }

    static String leftrotate(String str, int d) {
        String ans = str.substring(d) + str.substring(0, d);
        return ans;
    }

    public static void calcxor(int i, String[] arrdig) { // 16 Done 18 Done
        // w[i] = w[i-3] xor w[i-8] xor w[i-14] xor w[i-16]
        // w[16] = w[13] xor w[8] xor w[2] xor w[0]

        // 00000000000000000000000000000000
        // 00000000000000000000000000000000
        // 01100001011100000110100001111001
        // 01000011011100100111100101110000

        first = i - 3;
        second = i - 8;
        third = i - 14;
        fourth = i - 16;

        int firstdes = Integer.parseInt(arrdig[first], 2);
        int seconddes = Integer.parseInt(arrdig[second], 2);
        int thierddes = Integer.parseInt(arrdig[third], 2);
        int fourthdes = Integer.parseInt(arrdig[fourth], 2);

        // String ff = arrdig[first] ^ arrdig[second] ^ arrdig[third] ^ arrdig[fourth]
        // System.out.println(firstdes);

        int begwen = firstdes ^ seconddes;
        int ben2 = begwen ^ thierddes;
        int ben3 = ben2 ^ fourthdes;

        String nateeg = Integer.toBinaryString(ben3);
        if (nateeg.length() < 32) {
            // String rString = leftrotate(nateeg, 1);

            // System.out.println(rString);

            int e = 32 - nateeg.length();
            t = ""; // 2
            for (int w = 0; w < e - 1; w++) {
                t = t + "0";
            }

            // String tt = t + nateeg;

            System.out.print(i + ": " + t);
            System.out.println(nateeg);

        } else if (nateeg.length() > 32) {
            System.out.println("i am more than 32");
            harmd = nateeg.toCharArray();
            int e = nateeg.length() - 32;
            t = "";
            for (int w = e; w < nateeg.length(); w++) {
                t = t + harmd[w];
            }
            String opdin = t;
            System.out.println(opdin);

        } else {
            System.out.println("hi i am 32 bit ");
            // harmd = nateeg.toCharArray();
            // char trt = harmd[0];
            // // harmd2 = trt.toCharArray();
            // // if (harmd2[0] == '1') {
            // for (int j = 1; j < harmd.length; j++) {
            // System.out.print(harmd[j]);
            // }
            // System.out.println(1);

            // } else {
            // for (int j = 1; j < harmd.length; j++) {
            // System.out.print(harmd[j]);
            // }
            // System.out.println(0);
        }
        first = 0;
        second = 0;
        third = 0;
        fourth = 0;

        firstdes = 0;
        seconddes = 0;
        thierddes = 0;
        fourthdes = 0;

        begwen = 0;
        ben2 = 0;
        ben3 = 0;

    }

    public static String E5tesar(String appendit, int Extend, int more) {
        devidinginto = appendit.toCharArray();

        temp = "";
        for (int j = Extend; j < more; j++) {
            temp += devidinginto[j];
        }

        return temp;
    }

    public static void Devby32bit(String appendit) {
        devidinginto = appendit.toCharArray();
        arrdig = new String[16];

        Extend = 0;
        more = 32;
        for (int j = 0; j < 16; j++) {
            arrdig[j] = E5tesar(appendit, Extend, more);
            Extend += 32;
            more += 32;
        }

        for (int z = 0; z < 16; z++) {
            System.out.println(z + ": " + arrdig[z]);
        }

        // for (int i = 16; i < 80; i++) {
        // calcxor(i, arrdig);
        // }
        calcxor(16, arrdig);
        calcxor(18, arrdig);
        calcxor(17, arrdig);
    }

    public static void how_many_ZeroAddLift(int calc) {
        String bin = Integer.toBinaryString(calc);
        System.out.println(calc);
        binarycalc = "";

        if (bin.length() < 64) {
            int e = 64 - bin.length();
            for (int i = 0; i < e; i++) {
                binarycalc += "0";
            }
            binarycalc += bin;
        }
        System.out.print(binarycalc);
    }

    public static void append(String t, String binarycalc) {
        appendit = t;
        appendit = appendit + binarycalc;
        System.out.print(appendit);
    }

    private static int leftrotatex(int x, int shift) {

        return ((x << shift) | (x >>> (32 - shift)));

    }

    private static String hash(int[] msg) {
        int msglen = msg.length;
        int[] intArray = new int[80];
        int j = 0;
        // complete the rest of 80 groups
        for (int i = 0; i < msglen; i += 16) {
            for (j = 0; j <= 15; j++)
                intArray[j] = msg[j + i];
            for (j = 16; j <= 79; j++) {
                intArray[j] = leftrotatex(intArray[j - 3] ^ intArray[j - 8] ^ intArray[j - 14] ^ intArray[j - 16], 1);
            }

            for (int x = 0; x <= 19; x++) {
                temp2 = leftrotatex(A, 5) + ((B & C) | ((~B) & D)) + E + intArray[x] + k1;
                E = D;
                D = C;
                C = leftrotatex(B, 30);
                B = A;
                A = temp2;
            }
            for (int b = 20; b <= 39; b++) {
                temp2 = leftrotatex(A, 5) + (B ^ C ^ D) + E + intArray[b] + k2;
                E = D;
                D = C;
                C = leftrotatex(B, 30);
                B = A;
                A = temp2;
            }
            for (int c = 40; c <= 59; c++) {
                temp2 = leftrotatex(A, 5) + ((B & C) | (B & D) | (C & D)) + E + intArray[c] + k3;
                E = D;
                D = C;
                C = leftrotatex(B, 30);
                B = A;
                A = temp2;
            }
            for (int d = 60; d <= 79; d++) {
                temp2 = leftrotatex(A, 5) + (B ^ C ^ D) + E + intArray[d] + k4;
                E = D;
                D = C;
                C = leftrotatex(B, 30);
                B = A;
                A = temp2;
            }
            h1 += A;
            h2 += B;
            h3 += C;
            h4 += D;
            h5 += E;
        }

        String h1Length = Integer.toHexString(h1);
        String h2Length = Integer.toHexString(h2);
        String h3Length = Integer.toHexString(h3);
        String h4Length = Integer.toHexString(h4);
        String h5Length = Integer.toHexString(h5);
        String hh = h1Length + h2Length + h3Length + h4Length + h5Length;
        System.out.println("Result: " + hh);

        return null;
    }

    public static void main(String[] args) {
        System.out.println("input the string ");
        str = cin.nextLine();

        for (int i = 0; i < str.length(); i++) {
            System.out.print(plantext(str)[i]);
        }
        System.out.println(" ");

        for (int i = 0; i < str.length(); i++) {
            System.out.print(add1(arr, str)[i]);
        }

        System.out.println(" ");

        getlenghtstr(str);

        how_many_ZeroAdd(xx, arr2);
        System.out.println(" ");
        how_many_ZeroAddLift(calc);
        System.out.println(" ");
        append(t, binarycalc);
        System.out.println(" ");
        System.out.println(" ");
        Devby32bit(appendit);

    }
}
