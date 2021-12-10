package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class CredentialsServiceImpl implements CredentialsService {

    private final CredentialMapper credentialMapper;
    private final HashService hashService;
    private final  EncryptionService encryptionService;
    private final  UserServiceImpl userService;

    @Override
    public List<Credential> findAllCredentials(Integer userId) {
        List<Credential> credentialList =  credentialMapper.findByUserId(userService.findUserByUserId(userId).getUserId());
        for (Credential credential: credentialList){
            credential.setDecryptedPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        }
        return credentialList;
    }

    @Override
    public void deleteCredential(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }

    @Override
    public void updateCredential(Credential credential) {
        String hashedPassword = hashService.getHashedValue(credential.getPassword(), createSalt());
        credential.setKey(hashedPassword);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), hashedPassword));
        credentialMapper.update(credential);
    }

    @Override
    public void insertCredential(Credential credential) {
        String hashedPassword = hashService.getHashedValue(credential.getPassword(), createSalt());
        credential.setKey(hashedPassword);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), hashedPassword));
        credentialMapper.insert(credential);
    }

    private String createSalt(){
        byte[] salt = new byte[16];
        return Base64.getEncoder().encodeToString(salt);
    }

 
}
