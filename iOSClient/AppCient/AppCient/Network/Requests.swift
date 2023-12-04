//
//  Requests.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/5/23.
//

import Foundation

enum Constants {
    static let hostURL = "http://localhost:8080/appUserList-1.0.0-BUILD-SNAPSHOT/"
}

enum Requests {
    case login, logout, fetchAllUsers, addNewUser
}

extension Requests {
    var path: String {
        switch self {
        case .login:
            "login"
        case .logout:
            "logout"
        case .fetchAllUsers:
            "fetchAppUsers"
        case .addNewUser:
            "addNewUser"
        }
    }
    
    func makeRequest<T:Codable>(input: T) -> URLRequest {
    
        let url = URL(string: "\(Constants.hostURL)\(self.path)")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
        
        request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Accept")
        
        request.setValue("Bearer: \(AppSessionDetails.shared.acessToken)", forHTTPHeaderField: "Authorization") // Set Access Token for each request.
        
        request.httpBody = try! JSONEncoder().encode(input)
        return request
    }
}
