package com.paytm.pg.crypto;

public interface Encryption {
   String encrypt(String var1, String var2) throws Exception;

   String decrypt(String var1, String var2) throws Exception;
}
