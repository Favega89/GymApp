package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class QrGenerator {
	
	
	
	public ByteArrayOutputStream mapQRDetToQR(String details) throws IOException {
		ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
		File f = new File("C:\\Users\\idra\\eclipse-workspace\\GymApp\\qrs");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(f);
			fos.write(out.toByteArray());
			fos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return out;
	}
	
}
