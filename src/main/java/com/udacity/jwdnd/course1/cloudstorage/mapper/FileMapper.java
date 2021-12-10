package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File findFileById(Integer fileId);

    @Select("SELECT * FROM FILES WHERE fileName = #{fileName}")
    File findFileByName(String fileName);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> findAllFiles(int userId);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) "
            +"VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    Integer delete(Integer fileId);
}
