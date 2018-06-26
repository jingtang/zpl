package org.tang.gof;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;

/**
 * 
 * 二维码的生成需要借助MatrixToImageWriter类，该类是由Google提供的，可以将该类直接拷贝到源码中使用，当然你也可以自己写个
 * 
 * 生产条形码的基类
 * 
 */

public class MatrixToImageWriter {

	private static final int BLACK = 0xFF000000;// 用于设置图案的颜色

	private static final int WHITE = 0xFFFFFFFF; // 用于背景色
	
	private  ZPLConveter zp;
	

	public MatrixToImageWriter() {
		this.zp = new ZPLConveter();
	}

	/**
	 * 位图转BufferedImage
	 * @param matrix
	 * @return
	 */
	public  BufferedImage toBufferedImage(BitMatrix matrix) {

		int width = matrix.getWidth();

		int height = matrix.getHeight();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {

			for (int y = 0; y < height; y++) {

				image.setRGB(x, y, (matrix.get(x, y) ? BLACK : WHITE));

				// image.setRGB(x, y, (matrix.get(x, y) ? Color.YELLOW.getRGB() :
				// Color.CYAN.getRGB()));

			}

		}

		return image;

	}
	
	/**
	 * 写入到文件
	 * @param matrix 
	 * @param format 图片格式
	 * @param logoPath  logo图片路径,为空时不生成带logo的二维码图
	 * @param file 文件
	 * @throws IOException
	 */
	public  void writeToFile(BitMatrix matrix, String format,String logoPath, File file) throws IOException {

		BufferedImage image = toBufferedImage(matrix);

		// 设置logo图标

		LogoConfig logoConfig = new LogoConfig();

		image = logoConfig.LogoMatrix(image,logoPath);

		if (!ImageIO.write(image, format, file)) {

			throw new IOException("Could not write an image of format " + format + " to " + file);

		} else {

			System.out.println("图片生成成功！");

		}

	}

	/**
	 * 写入输出流
	 * @param matrix
	 * @param format
	 * @param logoPath  logo图片路径,为空时不生成带logo的二维码图
	 * @param stream
	 * @throws IOException
	 */
	public  void writeToStream(BitMatrix matrix, String format, String logoPath,OutputStream stream) throws IOException {

		BufferedImage image = generateQrCode(matrix,logoPath);

		if (!ImageIO.write(image, format, stream)) {

			throw new IOException("Could not write an image of format " + format);

		}

	}
	
	/**
	 * 
	 * @param matrix
	 * @param logoPath logo图片路径,为空时不生成带logo的二维码图
	 * @return
	 * @throws IOException
	 */
	public  BufferedImage generateQrCode(BitMatrix matrix,String logoPath) throws IOException {

		BufferedImage image = toBufferedImage(matrix);

		
		if(logoPath == null || logoPath.length() ==0 ) {
			return image;
		}else {
			// 设置logo图标
			LogoConfig logoConfig = new LogoConfig();

			return logoConfig.LogoMatrix(image,logoPath);
		}
		

	}
	
	/**
	 * 转换成ZPL
	 * @param matrix 二维码位图
	 * @param logoPath logo图片路径,为空时不生成带logo的二维码图
	 * @return
	 * @throws IOException
	 */
	public  String convertToZpl(BitMatrix matrix ,String logoPath) throws IOException {

		BufferedImage image = generateQrCode(matrix,logoPath);
		File outputFile = new File("d:" + File.separator + "new-1.png");// 指定输出路径
		ImageIO.write(image, "png", outputFile);
		zp.setCompressHex(true);
		zp.setBlacknessLimitPercentage(50);
		return zp.convertfromImg(image);
		
	}
	

}
