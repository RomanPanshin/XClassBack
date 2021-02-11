package com.Xjournal.Group;

import com.Xjournal.Group.Entity.*;
import com.Xjournal.Group.Entity.GroupDate;
import com.Xjournal.Group.Repo.AdditionalLessonRepository;
import com.Xjournal.Group.Repo.ClassInfoRepository;
import com.Xjournal.Group.Repo.LessonRepository;
import com.Xjournal.Group.Repo.UserRepository;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@Component
public class DBGenerator {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ClassInfoRepository classInfoRepository;
    @Autowired
    private AdditionalLessonRepository additionalLessonRepository;


    private ArrayList<String> teachers = new ArrayList<>();
    private ArrayList<ClassInfo> classes = new ArrayList<>();
    private static final int  classCount= 11;
    private static final int  teacherCount= 10;
    private static final String[] classNames= {"А", "Б"};
    private String[] lessonNames = {
            "Русский язык",
            "Иностранный язык",
            "Литература",
            "Информатика",
            "История древнего мира",
            "Математика",
            "Обществознание",
            "Музыка",
            "ОДНКНР",
            "Труд",
            "Природоведение",
            "Основы безопасности жизнедеятельности",
            "География",
            "ИЗО (рисование)",
            "Физическая культура"
    };

    public void generateAdditionalLessons(){
        for(String ln : lessonNames){
            for(String ch : classNames){
                for(int i = 1; i != 12; i++){
                    additionalLessonRepository.sendALessonToDB(new AdditionalLesson(ln, i  + ch));
                }
            }
        }
    }

    private String[] names = {
            " Михаил Александрович",
            " Сергей Сергеевич",
            " Ахед Гаджимурадович",
            " Джорджо",
            " Сабетказы",
            " Лариса Анатольевна",
            " Виктор Петрович",
            " Нина Георгиевна",
            " Рафаэль",
            " Сен Суренович",
            " Асеф Мехманович",
            " Муслим Бахтиярович",
            " Ильдар Анвярович",
            " Василий Олегович",
            " Ирина Анатольевна",
            " Николай Викторович",
            " Валерий Абрамович",
            " Юрий Петрович"};

    private String[] studentsSurNames = {
            "Смирнов" ,
            "Иванов" ,
            "Кузнецов" ,
            "Соколов" ,
            "Попов" ,
            "Лебедев" ,
            "Козлов" ,
            "Новиков" ,
            "Морозов" ,
            "Петров" ,
            "Волков" ,
            "Соловьёв" ,
            "Васильев" ,
            "Зайцев" ,
            "Павлов" ,
            "Семёнов" ,
            "Голубев" ,
            "Виноградов" ,
            "Богданов" ,
            "Воробьёв" ,
            "Фёдоров" ,
            "Михайлов" ,
            "Беляев" ,
            "Тарасов" ,
            "Белов" ,
            "Комаров" ,
            "Орлов" ,
            "Киселёв" ,
            "Макаров"
    };

    String[] Names = {"Михаил"," Максим"," Артем"," Лев"," Марк"," Дмитрий"," Иван"," Матвей","Даниил"};

    private void generateStudents() {
        int i = 0;
        Random r= new Random();
        for (ClassInfo c : classes) {
            String name = names[r.nextInt(names.length)] + " " + studentsSurNames[r.nextInt(studentsSurNames.length)];
            String id = userRepository.createUser("students"+i+"@school.ru","roma123",name,UserRepository.STUDENT,classes.get(i).getId());
            i++;
        }
    }

    private void generateTeachers() {
        Random r= new Random();
        for (int i = 0; i < teacherCount; i++) {
            String name = names[r.nextInt(names.length)] + " " + studentsSurNames[r.nextInt(studentsSurNames.length)];
            String id = userRepository.createUser("teachers"+i+"@school.ru","roma123",name,UserRepository.TEACHER,null);
            teachers.add(id);
        }
    }

    private void generateClasses() throws ExecutionException, InterruptedException {
        for (int i = 0; i < classCount; i++) {
            for (int j = 0; j < classNames.length; j++) {
                ClassInfo classInfo = new ClassInfo(Integer.toString(i + 1),classNames[j]);
                classInfoRepository.addClassInfo(classInfo);
                classes.add(classInfo);
            }
        }
    }

    private String getLessonForTeacher(String id) {
        int hash = Math.abs(id.hashCode());
        int num = hash % lessonNames.length;
        return lessonNames[num];
    }

    private void generateScheduleForClass(String classId) throws ExecutionException, InterruptedException {
        Random r = new Random(100);
        for (GroupDate.DayOfWeek d: GroupDate.DayOfWeek.values()) {
            int count = r.nextInt(4)+4;
            for (int i = 0; i < count; i++) {
                String teacher = teachers.get(r.nextInt(teacherCount));
                String lessonName = getLessonForTeacher(teacher);
                Lesson lesson =  new Lesson(lessonName,teacher,classId,new GroupDate(d,i+1));
                lessonRepository.addLessonDetails(lesson);
            }
        }
    }



    public void Generate()  {
        try {
//            userRepository.DeleteUsers();
            generateTeachers();
            generateClasses();
            generateStudents();
            generateAdditionalLessons();
            for (ClassInfo c: classes) {
                generateScheduleForClass(c.getId());
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void Clear(){


    }
}
