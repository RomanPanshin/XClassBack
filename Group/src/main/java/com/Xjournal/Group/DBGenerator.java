package com.Xjournal.Group;

import com.Xjournal.Group.Entity.*;
import com.Xjournal.Group.Entity.GroupDate;
import com.Xjournal.Group.Repo.*;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
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
    @Autowired
    private VideoLessonRepository videoLessonRepository;


    private ArrayList<String> teachers = new ArrayList<>();
    private ArrayList<ClassInfo> classes = new ArrayList<>();
    private static final int  classCount= 11;
    private static final int  teacherCount= 22;
    private static final String[] classNames= {"А", "Б"};

    private HashMap<String,Boolean > teachersOccupy = new HashMap<String, Boolean>();

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
        int k = 0;
        for(String ch : classNames){
            for(int i = 1; i != 12; i++){
                for(String ln : lessonNames){
                    additionalLessonRepository.sendALessonToDB(new AdditionalLesson(ln, i  + ch));
                    k++;
                }
                System.out.println(k + " " + i  + ch);
                k = 0;
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
            " Юрий Петрович",
            " Хож-Ахмед Ахмадович",
            " Леонид Давидович",
            " Платон Сергеевич",
            " Филипп Денисович",
            " Евгений Николаевич",
            " Владимир Осипович",
            " Роман Сергеевич",
            " Владимир Генрихович",
            " Ахмет Хамиевич",
            " Владимир Алексеевич",
            " Андрей Олегович",
            " Игорь Михайлович",
            " Александр Викторович",
            " Олег Николаеви",
            " Линор",
            " Владимир Иванович",
            " Андрей Николаевич",
            " Алексей Андреевич",
            " Дмитрий Степанович",
            " Дмитрий Анатольевич",
            " Леонид Григорьевич",
            " Игорь Борисович",
            " Василий Фёдорович",
            " Евгений Валерьевич",
            " Александр Николаевич",
            " Андрей Вадимович",
            " Дмитрий Сергеевич",
            " Валерий Дмитриевич",
            " Алексей Андреевич",
            " Дмитрий Степанович",
            " Дмитрий Анатольевич",
            " Леонид Григорьевич",
            " Игорь Борисович",
            " Василий Фёдорович",
            " Евгений Валерьевич",
            " Александр Николаевич",
            " Андрей Вадимович",
            " Дмитрий Сергеевич",
            " Валерий Дмитриевич"
    };


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

    private void generateScheduleForClass2(String classId) throws ExecutionException, InterruptedException {
        Random r = new Random(100);
        for (GroupDate.DayOfWeek d: GroupDate.DayOfWeek.values()) {
            int count = 6;
            for (int i = 0; i < count; i++) {
                String teacherIndex = "";
                String teacher = "";
                do {
                    teacher = teachers.get(r.nextInt(teacherCount));
                    teacherIndex = teacher+"-"+d+"-"+i+1;
                } while (teachersOccupy.getOrDefault(teacherIndex,false));
                teachersOccupy.put(teacherIndex,true);

                String lessonName = getLessonForTeacher(teacher);
                Lesson lesson =  new Lesson(lessonName,teacher,classId,new GroupDate(d,i+1));
                System.out.println("lesson "+lesson.getIdclass() +" "+ lessonName + " " +(i+1));
                lessonRepository.addLessonDetails(lesson);
            }
        }
    }






    public void Generate()  {
        try {
            userRepository.DeleteUsers();
            generateTeachers();
            generateClasses();
            generateStudents();
            generateAdditionalLessons();
            for (ClassInfo c: classes) {
                generateScheduleForClass2(c.getId());
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }
    public void createUU(){
        String id = userRepository.createUser("admins"+"@school.ru","roma123","Роман Паньшин",UserRepository.ADMIN,null);
    }

    public void uploadVideoLesson(){
        HashMap<String, Boolean> presentStudents = new HashMap<>();
        presentStudents.put("1UsH8az6tpb2jnkBhLrlpscS0k33", true);
        presentStudents.put("brsoxtbmr3cneJp3iQFVQzETrmc2", false);
        VideoLesson result = new VideoLesson(UUID.randomUUID().toString(), "38eb3cbc-ec5e-4cbc-92db-4733fa16a525", presentStudents, "18.02.2021", "0AczFiV5UdQhf3EYJBM6R2b5V3h2");
        videoLessonRepository.sendFileToDB(result);
    }

    public void Clear(){


    }
}
