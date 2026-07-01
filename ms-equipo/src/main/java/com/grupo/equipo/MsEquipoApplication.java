package com.grupo.equipo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication @EnableFeignClients
public class MsEquipoApplication {
	public static void main(String[] args) { SpringApplication.run(MsEquipoApplication.class, args); }
}