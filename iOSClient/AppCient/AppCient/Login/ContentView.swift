//
//  ContentView.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/5/23.
//

import SwiftUI
import Auth0

struct ContentView: View {
    
    @StateObject var viewModel = LoginViewModel()
    
    var body: some View {
        NavigationStack
        {
            VStack {
                

                NavigationLink(destination:
                                UserListView().environmentObject(viewModel.loginUserConfig),
                               isActive: $viewModel.loginSuccess) {
                     EmptyView()
                }.hidden()
                
                Button("Login") {
                    Task {
                        Auth0
                            .webAuth(clientId: AuthConfig.clientId, domain: AuthConfig.domain)
                            .start { result in
                                switch result {
                                case .success(let credentials):
                                    print("Obtained credentials: \(credentials)")
                                    Task {
                                        await viewModel.login(with: credentials)
                                    }
                                case .failure(let error):
                                    print("Failed with: \(error)")
                                }
                            }
                    }
                }.buttonStyle(BorderedButtonStyle())
            }
            .padding()
        }
    }
}

#Preview {
    ContentView()
}
