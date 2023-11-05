package com.userlist.home;

import javax.crypto.spec.SecretKeySpec;

/**
 * @author Nikunj.Upadhyay
 * 
 * CS-763 Project - AppUserList
 * 
 * Example: Security Style Category, for PMD GitHub action Java.
 * This gets reported as below.
 * 
 * HardCodedCryptoKey (Priority: 3, Ruleset: Security)
 * https://pmd.github.io/pmd-6.40.0/pmd_rules_java_security.html#hardcodedcryptokey
 * 
 */
public class BadCryptoExample {
	void bad() {
        SecretKeySpec secretKeySpec = new SecretKeySpec("my secret here".getBytes(), "AES");
        
    }
}