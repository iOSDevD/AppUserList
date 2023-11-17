//
//  String+Crypto.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/15/23.
//

import Foundation
import CryptoKit

extension String {
    var md5: String {
       Insecure.MD5.hash(data: self.data(using: .utf8)!).map { String(format: "%02hhx", $0) }.joined()
    }
}
