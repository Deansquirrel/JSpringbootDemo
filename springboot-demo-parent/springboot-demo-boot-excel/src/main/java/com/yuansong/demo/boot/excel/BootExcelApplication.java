package com.yuansong.demo.boot.excel;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuansong.demo.boot.excel.service.DataCalculate;

@SpringBootApplication(scanBasePackages="com.yuansong.demo")
public class BootExcelApplication implements  CommandLineRunner{
	
	@Autowired
	private DataCalculate dataCalculate;
	
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

	@Override
	public void run(String... args) throws Exception {
		long beginTime = System.currentTimeMillis();
		this.dataCalculate.subRun();
		long endTime = System.currentTimeMillis();
		System.out.println("\n" +
                " 运行耗时: " + (endTime - beginTime) + "毫秒 \n");
		
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
        scanner.next();
		
	}

}
