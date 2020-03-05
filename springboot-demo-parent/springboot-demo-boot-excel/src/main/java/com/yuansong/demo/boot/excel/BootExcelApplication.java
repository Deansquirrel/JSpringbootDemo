package com.yuansong.demo.boot.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.yuansong.demo")
public class BootExcelApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(BootExcelApplication.class, args);
		
//		System.out.println("\n" + 
//				"-----------------------------------------------------------------------\n" +
//                "-----------------------------------------------------------------------\n" +
//                "                               _     _\n" +
//                "                              ( \\---/ )\n" +
//                "                               ) . . (\n" +
//                "-------------------------,--._(___Y___)_,--.---------------------------\n" +
//                "                         `--'           `--'\n" +
//                "                          启动成功                         \n" +
//                "\n" +
//                " 启动耗时: " + (endTime - beginTime) + "毫秒 \n" +
//                "-----------------------------------------------------------------------\n" +
//                "-----------------------------------------------------------------------\n");
	}

}
