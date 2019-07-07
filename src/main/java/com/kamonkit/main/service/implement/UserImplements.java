package com.kamonkit.main.service.implement;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
@Service
public class UserImplements {

	@Transactional
	public  String createUser(String email) {
		 String password = "12345678";
		//create account fire
		CreateRequest request = new CreateRequest()
			    .setEmail(email)
			    .setEmailVerified(false)
			    .setPassword(password)
//			    .setPhoneNumber("+66853334462")
//			    .setDisplayName("Tae500")
//			    .setPhotoUrl("http://www.example.com/12345678/photo.png")
			    .setDisabled(false);

			UserRecord userRecord = null;
			try {
				userRecord = FirebaseAuth.getInstance().createUser(request);
			} catch (FirebaseAuthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userRecord.getUid();
	}
}
