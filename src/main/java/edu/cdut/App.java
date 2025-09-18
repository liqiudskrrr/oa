package edu.cdut;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.out.println("启动");
        SpringApplication.run(App.class);
    }
}
