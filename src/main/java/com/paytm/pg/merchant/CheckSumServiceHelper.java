package com.paytm.pg.merchant;

import com.paytm.pg.crypto.CryptoUtils;
import com.paytm.pg.crypto.Encryption;
import com.paytm.pg.crypto.EncryptionFactory;
import com.paytm.pg.crypto.EncryptionGAE;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class CheckSumServiceHelper {
   private static final CheckSumServiceHelper checkSumServiceHelper = new CheckSumServiceHelper();

   private CheckSumServiceHelper() {
   }

   public static String getVersion() {
      return "1.0";
   }

   public static CheckSumServiceHelper getCheckSumServiceHelper() {
      return checkSumServiceHelper;
   }

   public String genrateCheckSum(String Key, TreeMap<String, String> paramap) throws Exception {
      StringBuilder response = checkSumServiceHelper.getCheckSumString(paramap);
      String checkSumValue = null;

      try {
         Encryption encryption = EncryptionFactory.getEncryptionInstance("AES");
         String randomNo = CryptoUtils.generateRandomString(4);
         response.append(randomNo);
         String checkSumHash = CryptoUtils.getSHA256(response.toString());
         checkSumHash = checkSumHash.concat(randomNo);
         System.out.println("-----------------------------");
         System.out.println(checkSumHash);
         System.out.println("-----------------------------");
         checkSumValue = encryption.encrypt(checkSumHash, Key);
         if (checkSumValue != null) {
            checkSumValue = checkSumValue.replaceAll("\r\n", "");
            checkSumValue = checkSumValue.replaceAll("\r", "");
            checkSumValue = checkSumValue.replaceAll("\n", "");
         }
      } catch (SecurityException var8) {
         var8.printStackTrace();
      }

      return checkSumValue;
   }

   public String genrateCheckSumGAE(String Key, TreeMap<String, String> paramap) throws Exception {
      StringBuilder response = checkSumServiceHelper.getCheckSumString(paramap);
      String checkSumValue = null;

      try {
         EncryptionGAE encryption = EncryptionFactory.getEncryptionInstanceGAE("AES");
         String randomNo = CryptoUtils.generateRandomString(4);
         response.append(randomNo);
         String checkSumHash = CryptoUtils.getSHA256(response.toString());
         checkSumHash = checkSumHash.concat(randomNo);
         System.out.println("-----------------------------");
         System.out.println(checkSumHash);
         System.out.println("-----------------------------");
         checkSumValue = encryption.encryptGAE(checkSumHash, Key);
         if (checkSumValue != null) {
            checkSumValue = checkSumValue.replaceAll("\r\n", "");
            checkSumValue = checkSumValue.replaceAll("\r", "");
            checkSumValue = checkSumValue.replaceAll("\n", "");
         }
      } catch (SecurityException var8) {
         var8.printStackTrace();
      }

      return checkSumValue;
   }

   public StringBuilder getCheckSumString(TreeMap<String, String> paramMap) throws Exception {
      Set<String> keys = paramMap.keySet();
      StringBuilder checkSumStringBuffer = new StringBuilder("");
      TreeSet<String> parameterSet = new TreeSet();
      Iterator i$ = keys.iterator();

      String paramName;
      while(i$.hasNext()) {
         paramName = (String)i$.next();
         if (!"CHECKSUMHASH".equalsIgnoreCase(paramName)) {
            parameterSet.add(paramName);
         }
      }

      String value;
      for(i$ = parameterSet.iterator(); i$.hasNext(); checkSumStringBuffer.append(value).append("|")) {
         paramName = (String)i$.next();
         value = (String)paramMap.get(paramName);
         if (value == null || value.trim().equalsIgnoreCase("NULL")) {
            value = "";
         }
      }

      return checkSumStringBuffer;
   }

   public TreeMap<String, String> getParamsMapFromEncParam(String masterKey, String encParam) {
      try {
         if (null != masterKey && null != encParam) {
            Encryption encryption = EncryptionFactory.getEncryptionInstance("AES");
            String paramsString = encryption.decrypt(encParam, masterKey);
            if (null != paramsString) {
               return this.getMapForRawData(paramsString);
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return null;
   }

   public String getDecryptedValue(String masterKey, String decryptTo) {
      try {
         Encryption encryption = EncryptionFactory.getEncryptionInstance("AES");
         return encryption.decrypt(decryptTo, masterKey);
      } catch (Exception var4) {
         var4.printStackTrace();
         return null;
      }
   }

   private TreeMap<String, String> getMapForRawData(String rawdata) {
      if (null != rawdata) {
         TreeMap<String, String> resp = new TreeMap();
         String[] params = rawdata.split("\\|");
         if (null != params && params.length > 0) {
            String[] arr$ = params;
            int len$ = params.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               String param = arr$[i$];
               String[] keyValue = param.split("=");
               if (null != keyValue) {
                  if (keyValue.length == 2) {
                     resp.put(keyValue[0], keyValue[1]);
                  } else if (keyValue.length == 1) {
                     resp.put(keyValue[0], "");
                  }
               }
            }

            return resp;
         }
      }

      return null;
   }

   public String getEncryptedParam(String masterKey, TreeMap<String, String> paramMap) {
      StringBuilder params = new StringBuilder();

      try {
         if (paramMap != null && paramMap.size() > 0) {
            Iterator i$ = paramMap.entrySet().iterator();

            while(i$.hasNext()) {
               Entry<String, String> entry = (Entry)i$.next();
               params.append(((String)entry.getKey()).trim()).append("=").append(((String)entry.getValue()).trim()).append("|");
            }
         }

         Encryption encryption = EncryptionFactory.getEncryptionInstance("AES");
         return encryption.encrypt(params.toString(), masterKey);
      } catch (Exception var6) {
         var6.printStackTrace();
         return null;
      }
   }

   public boolean verifycheckSum(String masterKey, TreeMap<String, String> paramap, String responseCheckSumString) throws Exception {
      boolean isValidChecksum = false;
      StringBuilder response = checkSumServiceHelper.getCheckSumString(paramap);
      Encryption encryption = EncryptionFactory.getEncryptionInstance("AES");
      String responseCheckSumHash = encryption.decrypt(responseCheckSumString, masterKey);
      String randomStr = getLastNChars(responseCheckSumHash, 4);
      String payTmCheckSumHash = this.calculateRequestCheckSum(randomStr, response.toString());
      if (null != responseCheckSumHash && null != payTmCheckSumHash && responseCheckSumHash.equals(payTmCheckSumHash)) {
         isValidChecksum = true;
      }

      return isValidChecksum;
   }

   public boolean verifycheckSumGAE(String masterKey, TreeMap<String, String> paramap, String responseCheckSumString) throws Exception {
      boolean isValidChecksum = false;
      StringBuilder response = checkSumServiceHelper.getCheckSumString(paramap);
      EncryptionGAE encryption = EncryptionFactory.getEncryptionInstanceGAE("AES");
      String responseCheckSumHash = encryption.decryptGAE(responseCheckSumString, masterKey);
      String randomStr = getLastNChars(responseCheckSumHash, 4);
      String payTmCheckSumHash = this.calculateRequestCheckSum(randomStr, response.toString());
      if (null != responseCheckSumHash && null != payTmCheckSumHash && responseCheckSumHash.equals(payTmCheckSumHash)) {
         isValidChecksum = true;
      }

      return isValidChecksum;
   }

   private String calculateRequestCheckSum(String randomStr, String checkSumString) throws Exception {
      String checkSumHash = CryptoUtils.getSHA256(checkSumString.concat(randomStr));
      checkSumHash = checkSumHash.concat(randomStr);
      return checkSumHash;
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

   public static void main(String[] a) {
      String key = "C#txuHrwuO0ghDfv";
      String str = "esNqRAyDNMeqcYaCMfOTxjfeKT04vUnPxr6Et8j5NXT0PBTWaeM2WII7IQH++E3J4UOQiyBsgEcgcBTVzYuQ2fmF3Aj1w2jxm2RJSQAHIt8=";
      Encryption encryption = EncryptionFactory.getEncryptionInstance("AES");

      try {
         System.out.println(encryption.decrypt(str, key));
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }
}
