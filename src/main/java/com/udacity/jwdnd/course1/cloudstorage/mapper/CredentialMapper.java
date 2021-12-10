package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CredentialMapper {

    @Select("SELECT * FROM credentials")
    List<Credential> findAll();

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void delete(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password}, key= #{key} WHERE credentialId = #{credentialId}")
    void update(Credential credential);

    @Select("SELECT * FROM credentials WHERE userId = #{userId}")
    List<Credential> findByUserId(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (credentialId, url, username, key, password, userId) "
            +"VALUES(#{credentialId}, #{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer insert(Credential credential);
}
