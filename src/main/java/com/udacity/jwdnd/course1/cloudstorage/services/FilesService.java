package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesService {

     List<File> findAllFiles(int userId);

     void deleteFile(int id);

     File findFileByName(String fileName);

     void storeFile(MultipartFile file, Authentication auth);

     File findFileById(Integer fileId);
}