//
//  AuthConfig.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 12/2/23.
//

import Foundation
import Auth0

class AuthConfig {
    static let clientId = "XXX";
    static let domain = "XX";
}

class AppSessionDetails {
    static let shared = AppSessionDetails()
    
    // Save Access Token in local memory
    private(set) var acessToken: String = ""
    
    private init() { }
    
    func save(accessToken: String){
        self.acessToken = accessToken
    }
}
