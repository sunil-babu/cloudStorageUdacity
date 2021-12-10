package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
@RequestMapping("/files")
public class  FilesController {

    private FileServiceImpl fileService;

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file, Authentication authentication) {

        if(fileService.findFileByName(file.getOriginalFilename())==null)
        {
            fileService.storeFile(file,authentication);
            return "redirect:/result?success";
        }

        return "redirect:/result?error";

    }

    @RequestMapping("/{fileId}")
    public String delete(@PathVariable("fileId") int fileId)
    {
        fileService.deleteFile(fileId);
        return "redirect:/result?success";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        File file = fileService.findFileById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getFileData());
    }
}
