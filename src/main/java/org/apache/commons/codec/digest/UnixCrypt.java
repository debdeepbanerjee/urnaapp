package org.apache.commons.codec.digest;

import java.util.Random;
import org.apache.commons.codec.Charsets;

public class UnixCrypt {
   private static final int[] CON_SALT = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 0, 0, 0, 0, 0};
   private static final int[] COV2CHAR = new int[]{46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
   private static final char[] SALT_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./".toCharArray();
   private static final boolean[] SHIFT2 = new boolean[]{false, false, true, true, true, true, true, true, false, true, true, true, true, true, true, false};
   private static final int[][] SKB = new int[][]{{0, 16, 536870912, 536870928, 65536, 65552, 536936448, 536936464, 2048, 2064, 536872960, 536872976, 67584, 67600, 536938496, 536938512, 32, 48, 536870944, 536870960, 65568, 65584, 536936480, 536936496, 2080, 2096, 536872992, 536873008, 67616, 67632, 536938528, 536938544, 524288, 524304, 537395200, 537395216, 589824, 589840, 537460736, 537460752, 526336, 526352, 537397248, 537397264, 591872, 591888, 537462784, 537462800, 524320, 524336, 537395232, 537395248, 589856, 589872, 537460768, 537460784, 526368, 526384, 537397280, 537397296, 591904, 591920, 537462816, 537462832}, {0, 33554432, 8192, 33562624, 2097152, 35651584, 2105344, 35659776, 4, 33554436, 8196, 33562628, 2097156, 35651588, 2105348, 35659780, 1024, 33555456, 9216, 33563648, 2098176, 35652608, 2106368, 35660800, 1028, 33555460, 9220, 33563652, 2098180, 35652612, 2106372, 35660804, 268435456, 301989888, 268443648, 301998080, 270532608, 304087040, 270540800, 304095232, 268435460, 301989892, 268443652, 301998084, 270532612, 304087044, 270540804, 304095236, 268436480, 301990912, 268444672, 301999104, 270533632, 304088064, 270541824, 304096256, 268436484, 301990916, 268444676, 301999108, 270533636, 304088068, 270541828, 304096260}, {0, 1, 262144, 262145, 16777216, 16777217, 17039360, 17039361, 2, 3, 262146, 262147, 16777218, 16777219, 17039362, 17039363, 512, 513, 262656, 262657, 16777728, 16777729, 17039872, 17039873, 514, 515, 262658, 262659, 16777730, 16777731, 17039874, 17039875, 134217728, 134217729, 134479872, 134479873, 150994944, 150994945, 151257088, 151257089, 134217730, 134217731, 134479874, 134479875, 150994946, 150994947, 151257090, 151257091, 134218240, 134218241, 134480384, 134480385, 150995456, 150995457, 151257600, 151257601, 134218242, 134218243, 134480386, 134480387, 150995458, 150995459, 151257602, 151257603}, {0, 1048576, 256, 1048832, 8, 1048584, 264, 1048840, 4096, 1052672, 4352, 1052928, 4104, 1052680, 4360, 1052936, 67108864, 68157440, 67109120, 68157696, 67108872, 68157448, 67109128, 68157704, 67112960, 68161536, 67113216, 68161792, 67112968, 68161544, 67113224, 68161800, 131072, 1179648, 131328, 1179904, 131080, 1179656, 131336, 1179912, 135168, 1183744, 135424, 1184000, 135176, 1183752, 135432, 1184008, 67239936, 68288512, 67240192, 68288768, 67239944, 68288520, 67240200, 68288776, 67244032, 68292608, 67244288, 68292864, 67244040, 68292616, 67244296, 68292872}, {0, 268435456, 65536, 268500992, 4, 268435460, 65540, 268500996, 536870912, 805306368, 536936448, 805371904, 536870916, 805306372, 536936452, 805371908, 1048576, 269484032, 1114112, 269549568, 1048580, 269484036, 1114116, 269549572, 537919488, 806354944, 537985024, 806420480, 537919492, 806354948, 537985028, 806420484, 4096, 268439552, 69632, 268505088, 4100, 268439556, 69636, 268505092, 536875008, 805310464, 536940544, 805376000, 536875012, 805310468, 536940548, 805376004, 1052672, 269488128, 1118208, 269553664, 1052676, 269488132, 1118212, 269553668, 537923584, 806359040, 537989120, 806424576, 537923588, 806359044, 537989124, 806424580}, {0, 134217728, 8, 134217736, 1024, 134218752, 1032, 134218760, 131072, 134348800, 131080, 134348808, 132096, 134349824, 132104, 134349832, 1, 134217729, 9, 134217737, 1025, 134218753, 1033, 134218761, 131073, 134348801, 131081, 134348809, 132097, 134349825, 132105, 134349833, 33554432, 167772160, 33554440, 167772168, 33555456, 167773184, 33555464, 167773192, 33685504, 167903232, 33685512, 167903240, 33686528, 167904256, 33686536, 167904264, 33554433, 167772161, 33554441, 167772169, 33555457, 167773185, 33555465, 167773193, 33685505, 167903233, 33685513, 167903241, 33686529, 167904257, 33686537, 167904265}, {0, 256, 524288, 524544, 16777216, 16777472, 17301504, 17301760, 16, 272, 524304, 524560, 16777232, 16777488, 17301520, 17301776, 2097152, 2097408, 2621440, 2621696, 18874368, 18874624, 19398656, 19398912, 2097168, 2097424, 2621456, 2621712, 18874384, 18874640, 19398672, 19398928, 512, 768, 524800, 525056, 16777728, 16777984, 17302016, 17302272, 528, 784, 524816, 525072, 16777744, 16778000, 17302032, 17302288, 2097664, 2097920, 2621952, 2622208, 18874880, 18875136, 19399168, 19399424, 2097680, 2097936, 2621968, 2622224, 18874896, 18875152, 19399184, 19399440}, {0, 67108864, 262144, 67371008, 2, 67108866, 262146, 67371010, 8192, 67117056, 270336, 67379200, 8194, 67117058, 270338, 67379202, 32, 67108896, 262176, 67371040, 34, 67108898, 262178, 67371042, 8224, 67117088, 270368, 67379232, 8226, 67117090, 270370, 67379234, 2048, 67110912, 264192, 67373056, 2050, 67110914, 264194, 67373058, 10240, 67119104, 272384, 67381248, 10242, 67119106, 272386, 67381250, 2080, 67110944, 264224, 67373088, 2082, 67110946, 264226, 67373090, 10272, 67119136, 272416, 67381280, 10274, 67119138, 272418, 67381282}};
   private static final int[][] SPTRANS = new int[][]{{8520192, 131072, -2139095040, -2138963456, 8388608, -2147352064, -2147352576, -2139095040, -2147352064, 8520192, 8519680, -2147483136, -2139094528, 8388608, 0, -2147352576, 131072, Integer.MIN_VALUE, 8389120, 131584, -2138963456, 8519680, -2147483136, 8389120, Integer.MIN_VALUE, 512, 131584, -2138963968, 512, -2139094528, -2138963968, 0, 0, -2138963456, 8389120, -2147352576, 8520192, 131072, -2147483136, 8389120, -2138963968, 512, 131584, -2139095040, -2147352064, Integer.MIN_VALUE, -2139095040, 8519680, -2138963456, 131584, 8519680, -2139094528, 8388608, -2147483136, -2147352576, 0, 131072, 8388608, -2139094528, 8520192, Integer.MIN_VALUE, -2138963968, 512, -2147352064}, {268705796, 0, 270336, 268697600, 268435460, 8196, 268443648, 270336, 8192, 268697604, 4, 268443648, 262148, 268705792, 268697600, 4, 262144, 268443652, 268697604, 8192, 270340, 268435456, 0, 262148, 268443652, 270340, 268705792, 268435460, 268435456, 262144, 8196, 268705796, 262148, 268705792, 268443648, 270340, 268705796, 262148, 268435460, 0, 268435456, 8196, 262144, 268697604, 8192, 268435456, 270340, 268443652, 268705792, 8192, 0, 268435460, 4, 268705796, 270336, 268697600, 268697604, 262144, 8196, 268443648, 268443652, 4, 268697600, 270336}, {1090519040, 16842816, 64, 1090519104, 1073807360, 16777216, 1090519104, 65600, 16777280, 65536, 16842752, 1073741824, 1090584640, 1073741888, 1073741824, 1090584576, 0, 1073807360, 16842816, 64, 1073741888, 1090584640, 65536, 1090519040, 1090584576, 16777280, 1073807424, 16842752, 65600, 0, 16777216, 1073807424, 16842816, 64, 1073741824, 65536, 1073741888, 1073807360, 16842752, 1090519104, 0, 16842816, 65600, 1090584576, 1073807360, 16777216, 1090584640, 1073741824, 1073807424, 1090519040, 16777216, 1090584640, 65536, 16777280, 1090519104, 65600, 16777280, 0, 1090584576, 1073741888, 1090519040, 1073807424, 64, 16842752}, {1049602, 67109888, 2, 68158466, 0, 68157440, 67109890, 1048578, 68158464, 67108866, 67108864, 1026, 67108866, 1049602, 1048576, 67108864, 68157442, 1049600, 1024, 2, 1049600, 67109890, 68157440, 1024, 1026, 0, 1048578, 68158464, 67109888, 68157442, 68158466, 1048576, 68157442, 1026, 1048576, 67108866, 1049600, 67109888, 2, 68157440, 67109890, 0, 1024, 1048578, 0, 68157442, 68158464, 1024, 67108864, 68158466, 1049602, 1048576, 68158466, 2, 67109888, 1049602, 1048578, 1049600, 68157440, 67109890, 1026, 67108864, 67108866, 68158464}, {33554432, 16384, 256, 33571080, 33570824, 33554688, 16648, 33570816, 16384, 8, 33554440, 16640, 33554696, 33570824, 33571072, 0, 16640, 33554432, 16392, 264, 33554688, 16648, 0, 33554440, 8, 33554696, 33571080, 16392, 33570816, 256, 264, 33571072, 33571072, 33554696, 16392, 33570816, 16384, 8, 33554440, 33554688, 33554432, 16640, 33571080, 0, 16648, 33554432, 256, 16392, 33554696, 256, 0, 33571080, 33570824, 33571072, 264, 16384, 16640, 33570824, 33554688, 264, 8, 16648, 33570816, 33554440}, {536870928, 524304, 0, 537397248, 524304, 2048, 536872976, 524288, 2064, 537397264, 526336, 536870912, 536872960, 536870928, 537395200, 526352, 524288, 536872976, 537395216, 0, 2048, 16, 537397248, 537395216, 537397264, 537395200, 536870912, 2064, 16, 526336, 526352, 536872960, 2064, 536870912, 536872960, 526352, 537397248, 524304, 0, 536872960, 536870912, 2048, 537395216, 524288, 524304, 537397264, 526336, 16, 537397264, 526336, 524288, 536872976, 536870928, 537395200, 526352, 0, 2048, 536870928, 536872976, 537397248, 537395200, 2064, 16, 537395216}, {4096, 128, 4194432, 4194305, 4198529, 4097, 4224, 0, 4194304, 4194433, 129, 4198400, 1, 4198528, 4198400, 129, 4194433, 4096, 4097, 4198529, 0, 4194432, 4194305, 4224, 4198401, 4225, 4198528, 1, 4225, 4198401, 128, 4194304, 4225, 4198400, 4198401, 129, 4096, 128, 4194304, 4198401, 4194433, 4225, 4224, 0, 128, 4194305, 1, 4194432, 0, 4194433, 4194432, 4224, 129, 4096, 4198529, 4194304, 4198528, 1, 4097, 4198529, 4194305, 4198528, 4198400, 4097}, {136314912, 136347648, 32800, 0, 134250496, 2097184, 136314880, 136347680, 32, 134217728, 2129920, 32800, 2129952, 134250528, 134217760, 136314880, 32768, 2129952, 2097184, 134250496, 136347680, 134217760, 0, 2129920, 134217728, 2097152, 134250528, 136314912, 2097152, 32768, 136347648, 32, 2097152, 32768, 134217760, 136347680, 32800, 134217728, 0, 2129920, 136314912, 134250528, 134250496, 2097184, 136347648, 32, 2097184, 134250496, 136347680, 2097152, 136314880, 134217760, 2129920, 32800, 134250528, 136314880, 32, 136347648, 2129952, 0, 134217728, 136314912, 32768, 2129952}};

   public static String crypt(byte[] original) {
      return crypt((byte[])original, (String)null);
   }

   public static String crypt(byte[] original, String salt) {
      if (salt == null) {
         Random randomGenerator = new Random();
         int numSaltChars = SALT_CHARS.length;
         salt = "" + SALT_CHARS[randomGenerator.nextInt(numSaltChars)] + SALT_CHARS[randomGenerator.nextInt(numSaltChars)];
      } else if (!salt.matches("^[./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]{2,}$")) {
         throw new IllegalArgumentException("Invalid salt value: " + salt);
      }

      StringBuilder buffer = new StringBuilder("             ");
      char charZero = salt.charAt(0);
      char charOne = salt.charAt(1);
      buffer.setCharAt(0, charZero);
      buffer.setCharAt(1, charOne);
      int eSwap0 = CON_SALT[charZero];
      int eSwap1 = CON_SALT[charOne] << 4;
      byte[] key = new byte[8];

      int i;
      for(i = 0; i < key.length; ++i) {
         key[i] = 0;
      }

      for(i = 0; i < key.length && i < original.length; ++i) {
         int iChar = original[i];
         key[i] = (byte)(iChar << 1);
      }

      int[] schedule = desSetKey(key);
      int[] out = body(schedule, eSwap0, eSwap1);
      byte[] b = new byte[9];
      intToFourBytes(out[0], b, 0);
      intToFourBytes(out[1], b, 4);
      b[8] = 0;
      int i1 = 2;
      int y = 0;

      for(int u = 128; i1 < 13; ++i1) {
         int j = 0;

         for(int c = 0; j < 6; ++j) {
            c <<= 1;
            if ((b[y] & u) != 0) {
               c |= 1;
            }

            u >>>= 1;
            if (u == 0) {
               ++y;
               u = 128;
            }

            buffer.setCharAt(i1, (char)COV2CHAR[c]);
         }
      }

      return buffer.toString();
   }

   public static String crypt(String original) {
      return crypt(original.getBytes(Charsets.UTF_8));
   }

   public static String crypt(String original, String salt) {
      return crypt(original.getBytes(Charsets.UTF_8), salt);
   }

   private static int[] body(int[] schedule, int eSwap0, int eSwap1) {
      int left = 0;
      int right = 0;
      boolean t = false;

      int t1;
      for(int j = 0; j < 25; ++j) {
         for(int i = 0; i < 32; i += 4) {
            left = dEncrypt(left, right, i, eSwap0, eSwap1, schedule);
            right = dEncrypt(right, left, i + 2, eSwap0, eSwap1, schedule);
         }

         t1 = left;
         left = right;
         right = t1;
      }

      t1 = right;
      right = left >>> 1 | left << 31;
      left = t1 >>> 1 | t1 << 31;
      int[] results = new int[2];
      permOp(right, left, 1, 1431655765, results);
      right = results[0];
      left = results[1];
      permOp(left, right, 8, 16711935, results);
      left = results[0];
      right = results[1];
      permOp(right, left, 2, 858993459, results);
      right = results[0];
      left = results[1];
      permOp(left, right, 16, 65535, results);
      left = results[0];
      right = results[1];
      permOp(right, left, 4, 252645135, results);
      right = results[0];
      left = results[1];
      int[] out = new int[]{left, right};
      return out;
   }

   private static int byteToUnsigned(byte b) {
      return b < 0 ? b + 256 : b;
   }

   private static int dEncrypt(int el, int r, int s, int e0, int e1, int[] sArr) {
      int v = r ^ r >>> 16;
      int u = v & e0;
      v &= e1;
      u = u ^ u << 16 ^ r ^ sArr[s];
      int t = v ^ v << 16 ^ r ^ sArr[s + 1];
      t = t >>> 4 | t << 28;
      el ^= SPTRANS[1][t & 63] | SPTRANS[3][t >>> 8 & 63] | SPTRANS[5][t >>> 16 & 63] | SPTRANS[7][t >>> 24 & 63] | SPTRANS[0][u & 63] | SPTRANS[2][u >>> 8 & 63] | SPTRANS[4][u >>> 16 & 63] | SPTRANS[6][u >>> 24 & 63];
      return el;
   }

   private static int[] desSetKey(byte[] key) {
      int[] schedule = new int[32];
      int c = fourBytesToInt(key, 0);
      int d = fourBytesToInt(key, 4);
      int[] results = new int[2];
      permOp(d, c, 4, 252645135, results);
      d = results[0];
      c = results[1];
      c = hPermOp(c, -2, -859045888);
      d = hPermOp(d, -2, -859045888);
      permOp(d, c, 1, 1431655765, results);
      d = results[0];
      c = results[1];
      permOp(c, d, 8, 16711935, results);
      c = results[0];
      d = results[1];
      permOp(d, c, 1, 1431655765, results);
      d = results[0];
      c = results[1];
      d = (d & 255) << 16 | d & '\uff00' | (d & 16711680) >>> 16 | (c & -268435456) >>> 4;
      c &= 268435455;
      int j = 0;

      for(int i = 0; i < 16; ++i) {
         if (SHIFT2[i]) {
            c = c >>> 2 | c << 26;
            d = d >>> 2 | d << 26;
         } else {
            c = c >>> 1 | c << 27;
            d = d >>> 1 | d << 27;
         }

         c &= 268435455;
         d &= 268435455;
         int s = SKB[0][c & 63] | SKB[1][c >>> 6 & 3 | c >>> 7 & 60] | SKB[2][c >>> 13 & 15 | c >>> 14 & 48] | SKB[3][c >>> 20 & 1 | c >>> 21 & 6 | c >>> 22 & 56];
         int t = SKB[4][d & 63] | SKB[5][d >>> 7 & 3 | d >>> 8 & 60] | SKB[6][d >>> 15 & 63] | SKB[7][d >>> 21 & 15 | d >>> 22 & 48];
         schedule[j++] = t << 16 | s & '\uffff';
         s = s >>> 16 | t & -65536;
         s = s << 4 | s >>> 28;
         schedule[j++] = s;
      }

      return schedule;
   }

   private static int fourBytesToInt(byte[] b, int offset) {
      int value = byteToUnsigned(b[offset++]);
      value |= byteToUnsigned(b[offset++]) << 8;
      value |= byteToUnsigned(b[offset++]) << 16;
      value |= byteToUnsigned(b[offset++]) << 24;
      return value;
   }

   private static int hPermOp(int a, int n, int m) {
      int t = (a << 16 - n ^ a) & m;
      a = a ^ t ^ t >>> 16 - n;
      return a;
   }

   private static void intToFourBytes(int iValue, byte[] b, int offset) {
      b[offset++] = (byte)(iValue & 255);
      b[offset++] = (byte)(iValue >>> 8 & 255);
      b[offset++] = (byte)(iValue >>> 16 & 255);
      b[offset++] = (byte)(iValue >>> 24 & 255);
   }

   private static void permOp(int a, int b, int n, int m, int[] results) {
      int t = (a >>> n ^ b) & m;
      a ^= t << n;
      b ^= t;
      results[0] = a;
      results[1] = b;
   }
}
