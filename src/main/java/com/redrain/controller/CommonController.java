package com.redrain.controller;

import com.redrain.common.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/9 17:44
 */
@Controller
public class CommonController {

    @RequestMapping("/getActionEntry")
    @ResponseBody
    public String getActionEntry(HttpServletRequest request) {
        String action = request.getParameter("action");
        String rootPath = request.getSession().getServletContext().getRealPath("/uploadFiles");

        String result = new ActionEnter(request, rootPath).exec();
        //在下面判断如果是列出文件或图片的，替换物理路径的“/”
        if (action != null && (action.equals("listfile") || action.equals("listimage"))) {
            rootPath = rootPath.replace("\\", "/");
            result = result.replaceAll(rootPath, "/");
        }
        return result;
    }

//    @RequestMapping(value = "/upload/uploadFile")
//    @ResponseBody
//    public Map<String, Object> updloadFile(@PathVariable(value = "files") MultipartFile[] files, HttpServletRequest request) {
//        ArrayList<String> urlPathList = new ArrayList<>();
//        //判断file数组不能为空并且长度大于0
//        if (files != null && files.length > 0) {
//            //循环获取file数组中得文件
//            for (int i = 0; i < files.length; i++) {
//                MultipartFile file = files[i];
//                //保存文件
//                String s = saveFile(request, file);
//                if (!TextUtils.isEmpty(s)) {
//                    urlPathList.add(s);
//                }
//            }
//        }
//
//        HashMap<String, Object> resultMap = new HashMap<>();
//        resultMap.put("urlPathList", urlPathList);
//        if (urlPathList.size() == 0) {
//            return ReturnData.fail("上传失败");
//        }
//        return ReturnData.success(resultMap, "上传成功");
//    }
//
//    private String saveFile(HttpServletRequest request, MultipartFile file) {
//        String urlPath = "";
//        String str = file.getOriginalFilename();
//        String name = UploadUtils.getUUIDName(str);
//        String dir = UploadUtils.getDir(name);
//        String path_dir = request.getSession().getServletContext().getRealPath("/uploadFiles") + dir + "/";
//        String path_full = request.getSession().getServletContext().getRealPath("/uploadFiles") + dir + "/" + name;
//        try {
//            File newFile_dir = new File(path_dir);
//            if (!newFile_dir.exists()) {
//                boolean b = newFile_dir.mkdirs();
//            }
//            file.transferTo(new File(path_full));
//            urlPath = "uploadFiles" + dir + "/" + name;
//        } catch (IllegalStateException | IOException e) {
//            e.printStackTrace();
//        }
//        return urlPath;
//    }
}
