//
//  LoginViewModel.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/5/23.
//

import Foundation
import Combine

class LoginViewModel: ObservableObject {
    @Published var userName: String = ""
    @Published var password: String = ""
    @Published var loginSuccess: Bool = false
    
    private let client = NetWorkClient()
    
    func login() async {
        let requestBody = ["username" : self.userName, "password": self.password]
        let request = Requests.login.makeRequest(input: requestBody)
        let response: LoginResponse? = await client.connect(request: request)
        
        DispatchQueue.main.async { [response, unowned self] in
            if let response = response, response.isSuccess {
                loginSuccess.toggle()
                userName = ""
                password = ""
            } else {
                loginSuccess = false
            }
        }
        
    }
    
}

struct LoginResponse: Codable {
    var status: String
    
    var isSuccess: Bool {
        status == "success"
    }
}
