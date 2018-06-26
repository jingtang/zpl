package org.tang.gof;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * <p>二维码图片生成工具</p>
 * <p>1、生成二维码图片。2、生成带logo的二维码图片。3、二维码图片转ZPL。4、二维码图片转Base64</p>
 * @author tang
 *
 */
public class QrCodeUtil  {
	
	private static final String FORMAT = "png";
	
	private static QrCodeUtil qrCodeUtil;
	private QrCodeUtil() {}
	
	private MatrixToImageWriter mw  = new MatrixToImageWriter();
	
	/**
	 * 将二维码图片写入输出流
	 * @param contents
	 * @param width
	 * @param height
	 * @param stream
	 * @throws WriterException
	 * @throws IOException 
	 */
	public  void writeQrCodePicToOutStream(String contents,int width,int height,String logoPath,OutputStream stream) throws WriterException, IOException {

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();

		// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）

		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

		// 内容所使用字符集编码

		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

		// hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值

		// hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值

		hints.put(EncodeHintType.MARGIN, 0);// 设置二维码边的空白（0表示无空白），非负数

		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, // 要编码的内容

				// 编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,

				// Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,

				// MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E
				// 1D,UPC/EAN extension,UPC_EAN_EXTENSION

				BarcodeFormat.QR_CODE,

				width, // 条形码的宽度

				height, // 条形码的高度

				hints);// 生成条形码时的一些配置,此项可选
		
		mw.writeToStream(bitMatrix, FORMAT, logoPath,stream);//写入输出流
	}
	
	/**
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param logoPath
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public String qrCodePicToZPL(String contents,int width,int height,String logoPath) throws WriterException, IOException {

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();

		// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）

		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

		// 内容所使用字符集编码

		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

		// hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值

		// hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值

		hints.put(EncodeHintType.MARGIN, 0);// 设置二维码边的空白（0表示无空白），非负数

		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, // 要编码的内容

				// 编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,

				// Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,

				// MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E
				// 1D,UPC/EAN extension,UPC_EAN_EXTENSION

				BarcodeFormat.QR_CODE,

				width, // 条形码的宽度

				height, // 条形码的高度

				hints);// 生成条形码时的一些配置,此项可选
		
		return mw.convertToZpl(bitMatrix, logoPath);
	}
	
	
	/**
	 * 获取实例
	 * @return
	 */
	public static synchronized  QrCodeUtil getInstance() {
		if(qrCodeUtil == null) {
			return new QrCodeUtil();
		}else {
			return qrCodeUtil;
		}
	}
}
