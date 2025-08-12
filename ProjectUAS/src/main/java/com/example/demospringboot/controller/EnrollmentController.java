package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demospringboot.entity.Question;
import com.example.demospringboot.entity.User;
import com.example.demospringboot.service.QuestionService;
import com.example.demospringboot.service.JadwalService;
import com.example.demospringboot.service.EnrollmentService;
import com.example.demospringboot.service.UserService;
import com.example.demospringboot.entity.Enrollment;
import com.example.demospringboot.entity.Jadwal;
import org.springframework.ui.Model;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Map;

@Controller
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private JadwalService jadwalService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;


    @GetMapping("/enrollment")
    public String showEnrollment(Model model){
        model.addAttribute("enrollmentList", enrollmentService.getAllActiveEnrollments());
        return "enrollment/enrollment-list.html";
    }

    // menampilkan detail enrollment

    @GetMapping("/enrollment/{id}")
    public String showEnrollmentDetail(Model model,@PathVariable("id") int id){
        //model.addAttribute("enrollmentList", enrollmentService.getAllActiveEnrollments());
        return "enrollment/enrollment-list.html";
    }

    // menampilkan semua enrollment yang instructor_id sesuai parameter

    @GetMapping("/enrollment/instructor/{instructor_id}")
    public String showEnrollmentInstructor(Model model, @PathVariable("instructor_id") int instructor_id){
        
        List<Enrollment> filteredEnrollments = enrollmentService.getFilteredEnrollmentsByInstructor(instructor_id);
        model.addAttribute("enrollmentList", filteredEnrollments);

        return "enrollment/enrollment-list-filtered.html";
    }

    @GetMapping("/enrollment/grade/{grade}")
    public String showEnrollmentGrade(Model model, @PathVariable("grade") String grade){
        
        List<Enrollment> filteredEnrollments = enrollmentService.getFilteredEnrollmentsByGrade(grade);
        model.addAttribute("enrollmentList", filteredEnrollments);
        
        return "enrollment/enrollment-list-filtered.html";
    }

    @PostMapping("/result")
    public String showResult(Model model,@RequestParam Map<String, String> answers) {

        List<Question> questions = questionService.getQuestions();
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            String questionKey = "question" + (i + 1);
            String userAnswer = answers.get(questionKey);
            if (userAnswer != null && userAnswer.equalsIgnoreCase(questions.get(i).getCorrectAnswer())) {
                score++;
            }
        }

        Double d_score = (Double.parseDouble(score+"")/questions.size());
        // Membulatkan hingga 2 angka di belakang koma
        double finalscore = (Math.round(d_score * 100.0) / 100.0)*100;
        System.out.println("final score:"+finalscore);

        String str_grade;
        if ((int) finalscore == 0) {
            str_grade = "Pemula";
        } else if ((int)finalscore < 100) { // Tidak perlu memeriksa `score > 0` lagi karena sudah ditangani oleh kondisi sebelumnya
            str_grade = "Menengah";
        } else {
            str_grade = "Lanjutan";
        }

        //load jadwal berdasar grade
        List<Jadwal> jadwals = jadwalService.findJadwalByGrade(str_grade);

        //Pilih Jadwal setelah assesssment
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("score",(int) finalscore);
        model.addAttribute("grade",str_grade);
        model.addAttribute("jadwals",jadwals);
        model.addAttribute("user", userService.getUserByUsername(authentication.getName()));
        model.addAttribute("enrollmentInfo", new Enrollment());
        return "assessment/result";
    }

    @GetMapping("/selectjadwal")
    public String showSelectJadwal(Model model)
    {
        //Pilih jadwal dari dasboard
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        
        //get last grade from enrollment
        Enrollment lastenrollment = enrollmentService.getLastEnrollmentByUser(user.getId());
        String str_last_grade = lastenrollment.getJadwal().getGrade();

        List<Jadwal> jadwals = jadwalService.findJadwalByGrade(str_last_grade);

        model.addAttribute("enrollmentInfo", new Enrollment());
        model.addAttribute("jadwals",jadwals);
        model.addAttribute("user", user);
        return "enrollment/enrollment-selectjadwal";
    }

    // Proses add enrollment
    @PostMapping("/enrollment/save")
    public String saveEnrollment(Model model
    ,Enrollment enrollmentInfo
    ,@RequestParam("user_id") int user_id
    ,@RequestParam("jadwal_id") int jadwal_id) {
        enrollmentInfo.setActive_status(true);
        enrollmentInfo.setTanggal(LocalDate.now());
        enrollmentInfo.setJadwal(jadwalService.getJadById(jadwal_id));
        enrollmentInfo.setUser(userService.getUserById(user_id));

        Jadwal jadwal = jadwalService.getJadById(jadwal_id);
        userService.updateUserGrade(user_id, jadwal.getGrade());

        //menonaktifkan enrollment sebelumnya
        enrollmentService.updateInactiveByUser(user_id);
        //mendaftarkan enrollment baru
        enrollmentService.saveEnrollment(enrollmentInfo);

        return "enrollment/enrollment-success";
    }

     // Proses stop enrollment
     @PostMapping("/enrollment/cancel")
     public String cancelEnrollment(Model model
     ,@RequestParam("user_id") int user_id) {
        // menonaktifkan enrollment saat ini dan sebelumnya
        enrollmentService.updateInactiveByUser(user_id);
          return "enrollment/enrollment-cancel";
     }
    
}
