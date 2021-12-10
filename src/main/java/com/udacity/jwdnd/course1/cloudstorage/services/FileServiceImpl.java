package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FilesService {

    private final FileMapper fileMapper;
    private final UserMapper userMapper;


    @Override
    public List<File> findAllFiles(int userId){
        return fileMapper.findAllFiles(userId);
    }

    @Override
    public void deleteFile(int id) {
        fileMapper.delete(id);
    }

    @Override
    public void storeFile(MultipartFile file, Authentication auth)
    {
        File fileModel = new File();
        User user = userMapper.findByUserName(auth.getName());
        fileModel.setUserId(user.getUserId());
        fileModel.setContentType(file.getContentType());
        fileModel.setFileName(file.getOriginalFilename());
        try {
            fileModel.setFileData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileModel.setFileSize(file.getSize());
        fileMapper.insert(fileModel);

    }

    @Override
    public File findFileByName(String fileName) {
        return fileMapper.findFileByName(fileName);
    }

    @Override
    public File findFileById(Integer fileId) {
        return fileMapper.findFileById(fileId);
    }

}
