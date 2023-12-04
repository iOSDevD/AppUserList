//
//  LoginViewModel.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/5/23.
//

import Foundation
import Combine
import Auth0

class LoginViewModel: ObservableObject {
    @Published var loginSuccess: Bool = false
    
    private let client = NetWorkClient()
    
    var loginUserConfig = UserConfigDetails()
    
    func login(with credential: Credentials) async {
        let requestBody: [String: String] = [:]
        let request = Requests.login.makeRequest(input: requestBody)
        let response: LoginResponse? = await client.connect(request: request)
       
        do {
            AppSessionDetails.shared.save(accessToken: credential.idToken)
        } catch {
            print("All user Profile Items \(error)")
        }
        
        DispatchQueue.main.async { [response, unowned self] in
            if let response = response, response.isSuccess {
                loginUserConfig.role = response.userRole
                loginSuccess = true
            } else {
                loginSuccess = false
            }
        }
        
    }
    

    func getUserProfile(from credentials: Credentials) async throws -> Auth0.UserInfo?  {
        await withCheckedContinuation { obj in
            Auth0
                .authentication()
                .userInfo(withAccessToken: credentials.accessToken)
                .start { result in
                    switch result {
                    case .success(let profile):
                        obj.resume(returning: profile)
                    case .failure(let error):
                        obj.resume(returning: nil)
                    }
                }
        }
        
    }

    
}

struct LoginResponse: Codable {
    var status: String
    var role: String
    
    var isSuccess: Bool {
        status == "success"
    }
    
    var userRole: Role {
        switch role {
        case "1":
            return .admin
        default:
            return .staff
        }
    }
}

class UserConfigDetails: ObservableObject {
    @Published var role: Role = .staff
}

enum Role  {
    case admin, staff
}
