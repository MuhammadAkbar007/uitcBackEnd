package web.uitc.uitcbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.uitc.uitcbackend.entity.Attachment;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
