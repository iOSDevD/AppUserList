//
//  UserListView.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/5/23.
//

import SwiftUI

struct UserListView: View {
    
    @StateObject var model = UserListViewModel()
    var userName: String
    
    @Environment(\.dismiss) private var dismiss
    
    @EnvironmentObject var userConfig: UserConfigDetails
    
    var body: some View {
        
        List(model.appUsers) { element in
            HStack {
                Text(element.userFName)
                Text(element.userLName)
            }
        }
        .navigationBarTitleDisplayMode(.inline)
        .navigationBarBackButtonHidden(true)
        .navigationTitle("App User List")
        .navigationDestination(isPresented: $model.shouldShowAddNewUser, destination: {
            AddNewUserView()
        })
        .toolbar {
            ToolbarItemGroup(placement: .topBarLeading) {
                Button {
                    Task {
                        await model.logout()
                    }
                } label: {
                    Text("Logout")
                }

            }
            ToolbarItemGroup(placement: .topBarTrailing) {
                if userConfig.role == .admin {
                    Button {
                        model.showAddNewUser()
                    } label: {
                        Text("Add New")
                    }
                }
            }
        }
        .onAppear {
            model.setLoggedInUser(userName: self.userName)
            model.fetchList()
        }.onChange(of: model.logoutStatus) { _ in
            dismiss()
        }
    }
}

#Preview {
    UserListView(userName: "")
}
