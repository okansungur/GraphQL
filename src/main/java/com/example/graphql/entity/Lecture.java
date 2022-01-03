package com.example.graphql.entity;

public class Lecture {
    private int lectureId;
    private int studentId;
    private String lectureName;

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public Lecture(int lectureId, int studentId, String lectureName) {
        this.lectureId = lectureId;
        this.studentId = studentId;
        this.lectureName = lectureName;
    }

}
