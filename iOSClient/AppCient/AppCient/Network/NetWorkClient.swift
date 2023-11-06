//
//  NetWorkClient.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/5/23.
//

import Foundation
import Combine

class NetWorkClient {
    
    
    var disposeBag: Set<AnyCancellable> = []
    
    var session: URLSession
    
    init() {
        session = URLSession.shared
    }
    
    func connect<T: Codable>(request: URLRequest) async -> T? {
        do {
            let (data, response) = try await URLSession.shared.data(for: request)
            
            guard let httpResponse = response as? HTTPURLResponse,
                httpResponse.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
            let decoder = JSONDecoder()
            return try decoder.decode(T.self, from: data)
            
        } catch {
            return nil
        }
    }
}
