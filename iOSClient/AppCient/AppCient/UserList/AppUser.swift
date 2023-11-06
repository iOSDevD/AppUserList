//
//  AppUser.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/5/23.
//

import Foundation


struct AppUser: Codable, Identifiable {
    var userFName: String
    var userLName: String
    var id = UUID()
    
    private enum CodingKeys: String, CodingKey {
        case userFName, userLName
    }
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        userFName = try container.decode(String.self, forKey: .userFName)
        userLName = try container.decode(String.self, forKey: .userLName)
    }
    
    
    func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(userFName, forKey: .userFName)
        try container.encode(userLName, forKey: .userLName)
    }
}
