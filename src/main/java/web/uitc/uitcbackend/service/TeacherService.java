package web.uitc.uitcbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.uitc.uitcbackend.entity.Teacher;
import web.uitc.uitcbackend.payload.ApiResponse;
import web.uitc.uitcbackend.payload.TeacherDto;
import web.uitc.uitcbackend.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public ApiResponse add(TeacherDto teacherDto) {
        if (teacherRepository.existsByFirstNameAndLastNameAndStack(
                teacherDto.getFirstName(),
                teacherDto.getLastName(),
                teacherDto.getStack()))
            return new ApiResponse("Such teacher already exists !", false);
        teacherRepository.save(new Teacher(teacherDto.getFirstName(), teacherDto.getLastName(), teacherDto.getStack()));
        return new ApiResponse("Teacher successfully added !", true);
    }

//    public ApiResponse getAll() {
//        List<Teacher> teacherList = teacherRepository.findAll();
//        if (teacherList.isEmpty()) return new ApiResponse("no teachers", false, "No teachers");
//        return new ApiResponse("teachers", true, teacherList);
//    }

    public ApiResponse editTeacher(TeacherDto teacherDto, Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (!optionalTeacher.isPresent()) return new ApiResponse("Teacher not found !", false);
        Teacher teacher = optionalTeacher.get();
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setStack(teacherDto.getStack());
        teacherRepository.save(teacher);
        return new ApiResponse("Teacher successfully edited !", true);
    }

    public ApiResponse deleteTeacher(Long id) {
        try {
            teacherRepository.deleteById(id);
            return new ApiResponse("Teacher successfully deleted !", true);
        } catch (Exception e) {
            return new ApiResponse("Error while deleting !", false);
        }
    }
}
