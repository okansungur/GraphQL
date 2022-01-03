package com.example.graphql.controller;

import com.example.graphql.service.GQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

   private GQLService GQLService;

    @Autowired
    public StudentController(GQLService GQLService) {
        this.GQLService = GQLService;
    }

    @GetMapping("/getStudents")
    public ResponseEntity<Object> getStudents() {
        ExecutionResult execute = GQLService.getGraphQL().execute("query { allStudents { studentName  } }");
        return new ResponseEntity<>(execute, HttpStatus.OK);

    }

    @GetMapping("/getByStudentID")
    public ResponseEntity<Object> getStudentsbyId(@RequestParam(required = false) String id) {
        String query = "query { student(id:1) { studentName  } }";
        ExecutionResult execute = GQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }


    @GetMapping("/getStudentLecture")
    public ResponseEntity<Object> getsBy() {
        String query = "query { student(id:2) { studentName  lecture {lectureName} } }";
        ExecutionResult execute = GQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }


}