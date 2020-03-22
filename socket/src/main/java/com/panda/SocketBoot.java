package com.panda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SocketBoot {

	public static void main(String[] args) {
		SpringApplication.run(SocketBoot.class, args);
		log.info("\n             ヾ(◍°∇°◍)ﾉﾞ       SocketBoot       ヾ(◍°∇°◍)ﾉﾞ\n"
				+ "  ____          _              ______                    _   ______            \n"
				+ " / ___'_ __ _ _(_)_ __   __ _  |_   _ \\                  / |_|_   _ `.          \n"
				+ " \\___ | '_ | '_| | '_ \\/ _`  |  | |_) |   .--   SocketBoot   .    .--. `| |-' | | `. \\  .--.   \n"
				+ "  ___)| |_)| | | | | ||  (_| |  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n"
				+ " |____| .__|_| |_|_| |_\\__,  |_ | |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n"
				+ "  ====|_|===============|___/  |_______/ '.__.'  '.__.' \\__/|______.'  '.__.'  ");
	}

}
