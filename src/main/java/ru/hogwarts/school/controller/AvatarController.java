package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.AvatarDTO;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/student/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id,
                                               @RequestParam MultipartFile avatarDTO) throws IOException {
        if(!avatarService.getExtension(avatarDTO.getOriginalFilename()).equals("png")) {
            return ResponseEntity.badRequest().body("File isn't \".png\" format!");
        }
        avatarService.uploadAvatar(id, avatarDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{id}/preview")
    public ResponseEntity<byte[]> downloadAvatar (@PathVariable Long id) {
        AvatarDTO avatarDTO = AvatarDTO.fromAvatar(avatarService.getAvatar(id));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatarDTO.getMediaType()));
        headers.setContentLength(avatarDTO.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatarDTO.getData());
    }

    @GetMapping("/student/{id}")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        AvatarDTO avatarDTO = AvatarDTO.fromAvatar(avatarService.getAvatar(id));

        Path path = Path.of(avatarDTO.getFilePath());

        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream()
        ){
            response.setStatus(200);
            response.setContentType(avatarDTO.getMediaType());
            response.setContentLength((int) avatarDTO.getFileSize());
            is.transferTo(os);
        }
    }

}
