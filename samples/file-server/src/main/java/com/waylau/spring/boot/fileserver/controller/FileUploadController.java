package com.waylau.spring.boot.fileserver.controller;

import com.mongodb.gridfs.GridFSDBFile;
import com.waylau.spring.boot.fileserver.storage.FileStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;


@Controller
public class FileUploadController {

    @Autowired
    private FileStorage fileStorage;

    public FileUploadController(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }


    public FileStorage getFileStorage() {
        return fileStorage;
    }

    public void setFileStorage(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    @RequestMapping(value = "/")
    public String index(Model model) {

        HashMap<String, String> allFiles = new HashMap<>();

        fileStorage.getAll().forEach(file -> allFiles.put(file.getId().toString(), file.getFilename()));

        model.addAttribute("files", allFiles);
        return "index";
    }


    @RequestMapping(value = "/{fileId}")
    @ResponseBody
    public ResponseEntity serveFile(@PathVariable String fileId) {

        Optional<GridFSDBFile> file = fileStorage.read(fileId);

        if (file.isPresent()) {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.get().getFilename() + "\"")
                    .body(Optional.of(file.get().getInputStream()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }

    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        try {
            fileStorage.save(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file) {

        try {
            fileStorage.save(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("File was upload");
    }
}
