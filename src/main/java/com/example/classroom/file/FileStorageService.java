package com.example.classroom.file;



import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileStorageService {

    @Autowired
    private FileRepository fileDBRepository;

    public Long store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File myfile = new File(fileName, file.getContentType(), file.getBytes());
        fileDBRepository.save(myfile);
        return myfile.getId();
    }

    public File getFile(Long id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}