package com.userlist.home;

import javax.crypto.spec.SecretKeySpec;

public class BadCryptoExample {
	void bad() {
        SecretKeySpec secretKeySpec = new SecretKeySpec("my secret here".getBytes(), "AES");
        
    }
}