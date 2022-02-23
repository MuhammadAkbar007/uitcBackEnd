package web.uitc.uitcbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import web.uitc.uitcbackend.entity.Attachment;
import web.uitc.uitcbackend.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Autowired
    AttachmentRepository attachmentRepository;

    private static final String uploadDir = "yuklanganlar";

    @PreAuthorize("hasAuthority('UPLOAD_ATTACHMENT')")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFileToFs(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames(); // requestdan fayllarni ol
        MultipartFile file = request.getFile(fileNames.next()); // requestdan faylni ol
        if (file != null) {
            String originalFilename = file.getOriginalFilename();

            Attachment attachment = new Attachment();
            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());

            assert originalFilename != null;
            String[] split = originalFilename.split("\\."); // . gacha bo'laklash
            String name = UUID.randomUUID().toString() + "." + split[split.length - 1]; // random name ga type ni qo'sh
            attachment.setName(name);
            attachmentRepository.save(attachment);
            Path path = Paths.get(uploadDir + "/" + name); // tushadigan papkaga joyla
            Files.copy(file.getInputStream(), path);
            return ResponseEntity.ok(attachment.getId() + "-id lik fayl saqlandi !");
        }
        return ResponseEntity.status(409).body("Saqlanmadi");
    }

    @PreAuthorize("hasAnyAuthority('DOWNLOAD_ATTACHMENT', 'VIEW_ATTACHMENTS')")
    @GetMapping("/download/{id}")
    public void getFileFromFs(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            FileInputStream inputStream = new FileInputStream(uploadDir + "/" + attachment.getName());
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }
}
