//
//  RSA.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 12/3/23.
//

import Foundation
import Security

func encryptRSA(data: Data, publicKey: SecKey) throws -> Data {
    var error: Unmanaged<CFError>?

    guard let encryptedData = SecKeyCreateEncryptedData(publicKey, .rsaEncryptionPKCS1, data as CFData, &error) as Data? else {
        throw error!.takeRetainedValue() as Error
    }

    return encryptedData
}

func loadPublicKey() throws -> SecKey {
    guard let publicKeyPath = Bundle.main.path(forResource: "public_key", ofType: "der") else {
        throw NSError(domain: "YourAppDomain", code: 404, userInfo: nil)
    }

    let publicKeyData = try Data(contentsOf: URL(fileURLWithPath: publicKeyPath))
    var error: Unmanaged<CFError>?

    guard let publicKey = SecKeyCreateWithData(publicKeyData as CFData, [
        kSecAttrKeyType: kSecAttrKeyTypeRSA,
        kSecAttrKeyClass: kSecAttrKeyClassPublic,
        kSecAttrKeySizeInBits: 2048
    ] as CFDictionary, &error) else {
        throw error!.takeRetainedValue() as Error
    }

    return publicKey
}

// Example usage

func encrypt(inputText: String = "Baeldung secret message") {
    do {
        let publicKey = try loadPublicKey()
        let plaintext = inputText.data(using: .utf8)!
        let encryptedData = try encryptRSA(data: plaintext, publicKey: publicKey)
        
        print("Encrypted data: \(encryptedData.base64EncodedString())")
    } catch {
        print("Error: \(error)")
    }
}

