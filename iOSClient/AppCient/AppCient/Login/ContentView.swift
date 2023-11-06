//
//  ContentView.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/5/23.
//

import SwiftUI

struct ContentView: View {
    
    @StateObject var viewModel = LoginViewModel()
    
    var body: some View {
        NavigationView {
            VStack {
                NavigationLink(destination:
                                UserListView(userName: viewModel.userName),
                               isActive: $viewModel.loginSuccess) {
                     EmptyView()
                }.hidden()
                TextField("User Name", text: $viewModel.userName)
                    .textFieldStyle(.roundedBorder)
                SecureField("Password", text: $viewModel.password)
                    .textFieldStyle(.roundedBorder)
                
                Button("Login") {
                    Task {
                        await viewModel.login()
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
