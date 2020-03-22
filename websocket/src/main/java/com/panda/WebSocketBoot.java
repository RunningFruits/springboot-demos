package com.panda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WebSocketBoot {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketBoot.class, args);
		log.info("\n             ヾ(◍°∇°◍)ﾉﾞ    WebSocketBoot       ヾ(◍°∇°◍)ﾉﾞ\n"
				+ "  ____          _              ______                    _   ______            \n"
				+ " / ___'_ __ _ _(_)_ __   __ _  |_   _ \\                  / |_|_   _ `.          \n"
				+ " \\___ | '_ | '_| | '_ \\/ _`  |  | |_) |   .--WebSocketBoot   .    .--. `| |-' | | `. \\  .--.   \n"
				+ "  ___)| |_)| | | | | ||  (_| |  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n"
				+ " |____| .__|_| |_|_| |_\\__,  |_ | |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n"
				+ "  ====|_|===============|___/  |_______/ '.__.'  '.__.' \\__/|______.'  '.__.'  ");
	}

}
