package com.example.demo.test;

import com.example.demo.util.weixin.QRCodeUtil;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/5/2914:29
 */
@RestController
@RequestMapping("/qr")
public class QRCodeTestController {

    @RequestMapping("/create")
    public void createQRCode(HttpServletRequest request, HttpServletResponse response){
        //String url="www.baidu.com";
        String url="http://192.168.1.83:8091/test/index";
        try {
            BitMatrix bitMatrix= QRCodeUtil.createQRCode(url);
            MatrixToImageWriter.writeToStream(bitMatrix, QRCodeUtil.FORMAT, response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/analysis")
    public String analysisQRCode(HttpServletRequest request, HttpServletResponse response){
        String file="G:/MyDownloads/img/create.png";
        try {
            Result result = QRCodeUtil.analysisQRCode(file);
            System.out.println(result.getText());
            System.out.println(result.getBarcodeFormat());
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }

}
