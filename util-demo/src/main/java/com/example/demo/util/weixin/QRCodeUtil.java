package com.example.demo.util.weixin;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Description:二维码工具类
 * @Auther: zhangjie
 * @Date: 2018/5/29 13:39
 */
public class QRCodeUtil {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    public static final String FORMAT = "png";
    public static final String CHARTSET = "utf-8";

    /**
     * 创建二维码
     * @param url 二维码链接url
     * @return
     * @throws WriterException
     */
    public static BitMatrix createQRCode(String url) throws WriterException {

        HashMap<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, CHARTSET);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
        return bitMatrix;
    }

    /**
     * 解析二维码
     * @param file 二维码路径
     * @return
     * @throws IOException
     * @throws NotFoundException
     */
    public static Result analysisQRCode(String file) throws IOException, NotFoundException {
        MultiFormatReader reader=new MultiFormatReader();
        File f=new File(file);
        BufferedImage image= ImageIO.read(f);
        BinaryBitmap bb=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        HashMap map =new HashMap();
        map.put(EncodeHintType.CHARACTER_SET, CHARTSET);
        Result result = reader.decode(bb,map);
        return result;
    }

}
