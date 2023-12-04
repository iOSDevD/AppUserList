//
//  UserListViewModel.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/5/23.
//

import Foundation
import Auth0

class UserListViewModel: ObservableObject {
    
    private let client = NetWorkClient()
    
    @Published var logoutStatus: Bool = false
    
    @Published var appUsers: [AppUser] = []
    
    @Published var shouldShowAddNewUser: Bool = false
    

    
    func logout() async {
        let emptyBody: [String: String] = [:]
        let request = Requests.logout.makeRequest(input: emptyBody)
        
        try? await Auth0.webAuth().clearSession()
        
        let response: [String: String]? = await client.connect(request: request)
        
        DispatchQueue.main.async { [response, unowned self] in
            if let response = response, let status = response["status"], status == "success" {
                logoutStatus.toggle()
            } 
        }
    }
    
    func fetchList() {
        Task {
            let emptyDictionary: [String: String] = [:]
            let request = Requests.fetchAllUsers.makeRequest(input: emptyDictionary)
            let response: [AppUser]? = await client.connect(request: request)
            
            
            DispatchQueue.main.async { [response, unowned self] in
                if let response = response {
                    self.appUsers = response
                }
            }
        }
    }
    
    func showAddNewUser() {
        self.shouldShowAddNewUser = true
    }
}


struct LogoutRequest: Codable {
    var username: String
}


