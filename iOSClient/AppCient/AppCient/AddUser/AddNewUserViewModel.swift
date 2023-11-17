//
//  AddNewUserViewModel.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/15/23.
//

import Foundation


class AddNewUserViewModel: ObservableObject {
    
    @Published var firstName: String = ""
    
    @Published var lastName: String = ""
    
    @Published var showErroEmptyInput: Bool = false
    @Published var userAddSuccessfully: Bool = false
    
    var saveDetails: SaveDetails?
    
    private let client = NetWorkClient()
    
    func addNewUser() async {
        let fName = firstName.trimmingCharacters(in: .whitespacesAndNewlines)
        let lName = lastName.trimmingCharacters(in: .whitespacesAndNewlines)
        guard !fName.isEmpty, !lName.isEmpty else {
            DispatchQueue.main.async {
                self.saveDetails = SaveDetails(error: "Please enter both first name and last name.")
                self.showErroEmptyInput = true
            }
            return
        }
        
        let addNewUserRequest = AddNewUserRequest(userFName: fName, userLName: lName)
        
        let request = Requests.addNewUser.makeRequest(input: addNewUserRequest)
        
        let response: [String: String]? = await client.connect(request: request)
        
        DispatchQueue.main.async { [response, unowned self] in
            if let response = response, let status = response["status"], status == "success" {
                userAddSuccessfully = true
            }
        }
    }
}


struct SaveDetails: Identifiable {
    let error: String
    let id = UUID()
}
