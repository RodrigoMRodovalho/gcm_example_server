package br.com.rrodovalho.gcm_example_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GcmExampleServerApplication {

	public final static String SENDER_API_KEY="AIzaSyDszwo1XOan77b6HQ4cHCoPq0l-BEGIeSs";
	public final static String GCM_EXAMPLE_B2C_PACKAGE="br.com.rrodovalho.gcm_exampleb2c";

	public static void main(String[] args) {
		SpringApplication.run(GcmExampleServerApplication.class, args);
	}
}
