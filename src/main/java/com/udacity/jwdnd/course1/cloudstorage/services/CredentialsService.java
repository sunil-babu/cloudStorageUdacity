package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

import java.util.List;

public interface CredentialsService {

     List<Credential> findAllCredentials(Integer UserId);
     void deleteCredential(Integer credentialId);
     void updateCredential(Credential credential);
     void insertCredential(Credential credential);


}
