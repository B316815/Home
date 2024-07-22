package com.home.Controller;
import com.home.Service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("s3bucket")
@CrossOrigin("*")
public class BucketController {
    @Autowired
    BucketService service;
    @PostMapping(path = "/upload/file/{b316815m}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestParam MultipartFile file,
            @PathVariable String b316815m) {
        return new
                ResponseEntity<>(service.uploadFile(file,b316815m),HttpStatus.OK);
    }

    //@DeleteMapping(path="/delete/file/{bucketName}/{fileName}")
//public ResponseEntity<String> deleteFile(@PathVariable String bucketName,@PathVariable String fileName)
//{
// return new ResponseEntity<>(service.deleteFile(bucketName,fileName),HttpStatus.OK);
}


