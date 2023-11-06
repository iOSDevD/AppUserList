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
        .toolbar {
            ToolbarItemGroup {
                Button {
                    Task {
                        await model.logout()
                    }
                } label: {
                    Text("Logout")
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
