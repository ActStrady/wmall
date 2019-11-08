package com.actstrady.wmall.web.controller;

import com.actstrady.wmall.iatws.WebIATWS;
import com.actstrady.wmall.utils.voice.FileUtils;
import com.actstrady.wmall.utils.voice.ResponseVo;
import com.actstrady.wmall.utils.voice.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * 语音搜索
 *
 * @author : ActStrady@tom.com
 * @date : 2019/11/6 17:24
 * @fileName : AutoVoiceController.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@RestController
@SessionAttributes("iat_message")
@Slf4j
public class AutoVoiceController {
    private static final String FFMPEG_PATH = "D:\\Program Files\\ffmpeg\\bin\\";

    /**
     * 语音转文字
     * 整体思路，前端传文件，后端使用MultipartFile来接收，然后转成一个.wav文件
     * 使用ffmpeg将wav转化为pcm格式，经过讯飞接口转化为文字
     * 后传到前端
     *
     * @param multfile 接收前台传的文件
     * @param mode mode
     * @return 语音识别结果
     * @throws IOException IO异常
     * @throws InterruptedException InterruptedException
     */
    @PostMapping("/transform")
    public String upload(@RequestParam(value = "wavData", required = false) MultipartFile multfile, Model mode) throws IOException, InterruptedException {
        // 获取文件名
        String fileName = multfile.getOriginalFilename();
        // 获取文件后缀
        String suffix = ".wav";
        // 用uuid作为文件名，防止生成的临时文件重复
        final File iatFile = File.createTempFile(UuidUtils.getUuid32(), suffix);
        multfile.transferTo(iatFile);
        String relativelyPath = System.getProperty("user.dir");
        String temp = relativelyPath + File.separator + "temp";
        log.info(temp);
        File tempFile = new File(temp);
        String destTemp = temp + File.separator + iatFile.getName().substring(0, iatFile.getName().lastIndexOf(".")) + ".pcm";
        // 调用cmd程序，转成pcm格式文件
        String command = FFMPEG_PATH + "ffmpeg -y -i " + iatFile + " -acodec pcm_s16le -f s16le -ac 1 -ar 16000 " + destTemp;
        Process process = Runtime.getRuntime().exec(command);
        int status = process.waitFor();
        String message = "";
        if (status == 0) {
            // 调用讯飞接口，返回转换后的结果
            WebIATWS ws = new WebIATWS();
            try {
                ResponseVo response = ws.getMessage(destTemp);
                if (response.getMessage() != null) {
                    message = response.getMessage();
                }
                mode.addAttribute("iat_message", response);
            } catch (Exception e) {
                e.printStackTrace();
                message = "请重新说";
            }

        } else {
            log.error("音频文件转写失败");
            message = "音频文件转写失败";
        }

        //程序结束时，删除临时文件
        FileUtils.deleteFile(iatFile);
        log.info("message:{}", message);
        return message;
    }

    @RequestMapping("/iat")
    public ResponseVo iat(HttpSession session) {
        return (ResponseVo) session.getAttribute("iat_message");
    }
}
