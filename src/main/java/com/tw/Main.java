package com.tw;

import java.util.*;


public class Main {

    private Scanner scanner = new Scanner(System.in);
    private Set<String> subjects = new HashSet<>();
    private List<Student> students = new ArrayList<>();


    public static void main(String[] args) {
        new Main().mainMenu();
    }


    private void mainMenu() {
        System.out.println("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：");


        Integer action = Integer.parseInt(scanner.nextLine().trim());

        switch (action) {
            case 1:
                addStudent();
                mainMenu();
                break;
            case 2:
                scoreList();
                mainMenu();
                break;
            case 3:
                break;
        }
    }

    private void addStudent() {
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
        outer:
        while (true) {

            try {

                String line = scanner.nextLine();

                String[] args = line.split(",");

                if (args.length < 3) {
                    System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
                    continue;
                }

                Student student = new Student();

                for (int i = 0; i < args.length; i++) {
                    if (i == 0) {
                        student.setName(args[0]);
                    } else if (i == 1) {
                        student.setNumber(args[1]);
                    } else {
                        String[] scores = args[i].split(":");

                        if (scores.length != 2) {
                            break;
                        }

                        student.addScore(scores[0], Integer.parseInt(scores[1]));

                        subjects.add(scores[0]);
                    }
                }

                students.add(student);
                System.out.println(String.format("学生%s的成绩被添加", student.getName()));
                break outer;

            } catch (Exception e) {
                System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
            }


        }


    }


    private void scoreList() {


        System.out.println("成绩单");
        System.out.println(getTitleString());
        System.out.println("========================");
        Double total = 0D;
        List<Integer> scores = new ArrayList<>();

        for (Student student : students) {
            total += student.getAver();
            scores.add(student.getTotal());

            System.out.println(getStudentString(student));
        }
        System.out.println("========================");
        System.out.println("全班总分平均数：" + total / students.size());

        Collections.sort(scores);
        Double middle;
        if (scores.size() % 2 == 0) {
            middle = (scores.get((scores.size()-1) / 2) + scores.get(scores.size()/21)) * 1.0D / 2;
        } else {
            middle = Double.valueOf(scores.get(scores.size() / 2));
        }


        System.out.println("全班总分中位数：" + middle);
    }


    private String getTitleString() {
        StringBuffer sb = new StringBuffer();
        sb.append("姓名|学号");

        for (String subject : subjects) {
            sb.append("|").append(subject);
        }
        sb.append("|平均分|总分");

        return sb.toString();
    }

    private String getStudentString(Student student) {


        StringBuffer sb = new StringBuffer();
        sb.append(student.getName()).append("|").append(student.getNumber());

        for (String subject : subjects) {
            Integer score = student.getScores().get(subject);
            if (score == null) {
                sb.append("|  ");
            } else {
                sb.append("|").append(score);
            }
        }


        sb.append("|").append(student.getAver())
                .append("|").append(student.getTotal());


        return sb.toString();
    }


}
