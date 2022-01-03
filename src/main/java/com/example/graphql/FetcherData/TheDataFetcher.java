package com.example.graphql.FetcherData;
import graphql.com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class TheDataFetcher  {


    private static List<Map<String, String>> students = Arrays.asList(
            ImmutableMap.of("studentId", "1",
                    "studentName", "Rita",
                    "studentAddress", "Texas",
                    "age", "22",
                    "lectureId", "1"
            ),
            ImmutableMap.of("studentId", "2",
                    "studentName", "Susanne",
                    "studentAddress", "Rio",
                    "age", "32",
                    "lectureId", "2"
            )
    );

    public DataFetcher getStudentDataFetcher() {
        return dataFetchingEnvironment -> {
            return students
                    .stream();
        };
    }

    public DataFetcher getStudentByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String studentid = dataFetchingEnvironment.getArgument("id");
            return students
                    .stream()
                    .filter(stu -> stu.get("studentId").equals(studentid))
                    .findFirst()
                    .orElse(null);
        };
    }



    public DataFetcher getLectureDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> student = dataFetchingEnvironment.getSource();

            String lectureid = student.get("lectureId");


            return lectures
                    .stream()
                    .filter(stu ->  stu.get("lectureId").equals(lectureid))
                    .findFirst()
                    .orElse(null);
        };
    }


    private static List<Map<String, String>> lectures = Arrays.asList(
            ImmutableMap.of(
                    "lectureId", "1",
                    "lectureName", "Maths"),
            ImmutableMap.of(
                    "lectureId", "2",
                    "lectureName", "Science")

    );







}