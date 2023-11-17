//
//  AddNewUserView.swift
//  AppCient
//
//  Created by Nikunj Upadhyay on 11/15/23.
//

import SwiftUI

struct AddNewUserView: View {

    @StateObject var model = AddNewUserViewModel()
    
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        VStack {
            HStack {
                TextField("First Name", text: $model.firstName)
            }
            HStack {
                TextField("Last Name", text: $model.lastName)
            }
            Button {
                Task {
                    await model.addNewUser()
                }
            } label: {
                Text("Add")
            }.buttonStyle(BorderedButtonStyle())

            Spacer()
        }
        .padding()
        .navigationTitle("New User")
        .alert("Save Failed", isPresented: $model.showErroEmptyInput, presenting: model.saveDetails, actions: { details in
            Button(role: .cancel) {
                
            } label: {
                Text("Cancel")
            }
        }, message: { details in
            Button(role: .cancel) {
                
            } label: {
                Text(details.error)
            }
        })
        .onChange(of: model.userAddSuccessfully, initial: false, { oldValue, newValue in
            dismiss()
        })
    }
}

#Preview {
    AddNewUserView()
}
