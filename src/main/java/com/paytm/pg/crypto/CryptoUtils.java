package com.paytm.pg.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class CryptoUtils {
   public static String getHashFromSHA(String value) throws Exception {
      String hashValue = "";
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      hashValue = byteArray2Hex(messageDigest.digest(value.getBytes()));
      return hashValue;
   }

   private static String byteArray2Hex(byte[] hash) {
      Formatter formatter = new Formatter();
      byte[] arr$ = hash;
      int len$ = hash.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         byte b = arr$[i$];
         formatter.format("%02x", b);
      }

      return formatter.toString();
   }

   public static String generateRandomString(int length) {
      String ALPHA_NUM = "9876543210ZYXWVUTSRQPONMLKJIHGFEDCBAabcdefghijklmnopqrstuvwxyz!@#$&_";
      StringBuffer sb = new StringBuffer(length);

      for(int i = 0; i < length; ++i) {
         int ndx = (int)(Math.random() * (double)ALPHA_NUM.length());
         sb.append(ALPHA_NUM.charAt(ndx));
      }

      return sb.toString();
   }

   public static String getSHA256(String value) throws SecurityException {
      String hashValue = "";

      try {
         MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
         hashValue = byteArray2Hex(messageDigest.digest(value.getBytes()));
         return hashValue;
      } catch (NoSuchAlgorithmException var3) {
         throw new SecurityException(var3.getMessage(), var3);
      }
   }

   public static String getLastNChars(String inputString, int subStringLength) {
      if (null != inputString && inputString.length() > 0) {
         int length = inputString.length();
         if (length <= subStringLength) {
            return inputString;
         } else {
            int startIndex = length - subStringLength;
            return inputString.substring(startIndex);
         }
      } else {
         return "";
      }
   }
}
