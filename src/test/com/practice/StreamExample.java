package com.practice;

/**
 * Created by pbhattacharya on 10/13/16.
 */
public class StreamExample {

    static class Person {
        Integer id;
        String name;

        Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


//    public static void main(String[] args) {
//        List<com.practice.Person> persons = new ArrayList<>(Arrays.asList(new com.practice.Person(1,"Ram"), new com.practice.Person(2, "Rahim"), new com.practice.Person(3,"Isma")));
//        List<String> list = persons.stream().map(com.practice.Person ::getName).collect(Collectors.toList());
//        List<String> anotherList = persons.stream().map(com.practice.Person ::getName).collect(Collectors.partitioningBy(s -> {
//            return s.toString().startsWith("R");
//        }));
//        list.forEach(System.out ::println);
//    }
}
