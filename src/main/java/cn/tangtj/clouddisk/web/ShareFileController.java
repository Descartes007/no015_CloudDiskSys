package cn.tangtj.clouddisk.web;

import cn.tangtj.clouddisk.entity.UploadFile;
import cn.tangtj.clouddisk.entity.User;
import cn.tangtj.clouddisk.entity.vo.Guest;
import cn.tangtj.clouddisk.entity.vo.ShareFile;
import cn.tangtj.clouddisk.service.FileService;
import cn.tangtj.clouddisk.service.UserService;
import cn.tangtj.clouddisk.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.File;

@Controller
@RequestMapping("/shareFile")
@SessionAttributes({"guest"})
public class ShareFileController {

    private final String fileSavePath;

    private final static String fileSaveDir = "upload";

    private final FileService fileService;

    private final UserService userService;

    @Autowired
    public ShareFileController(FileService fileService, UserService userService,ServletContext servletContext) {
        this.fileService = fileService;
        this.userService = userService;
        fileSavePath = servletContext.getRealPath("") + fileSaveDir + File.separator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String shareUi(Model model) {
//        Guest guest = new Guest();
//        guest.setDownloadTime(0);
//        model.addAttribute("guest", guest);
        return "shareFileIndex";
    }

    @RequestMapping(value = "/{shareCode}", method = RequestMethod.POST)
    @ResponseBody
    public ShareFile shareFile(@SessionAttribute Guest guest, @PathVariable("shareCode") String shareCode) {
        if (guest == null) {
            return null;
        }
        UploadFile fileInfo = fileService.findByShareCode(shareCode);
        User user = userService.findById(fileInfo.getUserId());
        ShareFile shareFileInfo = new ShareFile();
        shareFileInfo.setFileName(fileInfo.getFileName());
        shareFileInfo.setOwnName(user.getUsername());
        shareFileInfo.setShareCode(shareCode);
        return shareFileInfo;
    }

    @RequestMapping(value = "/{shareCode}/download")
    public ResponseEntity<byte[]> shareDownload(@SessionAttribute Guest guest, @PathVariable("shareCode") String shareCode) {
//        if (guest == null) {
//            return null;
//        }
//        if (guest.getDownloadTime() >= 10) {
//            return null;
//        }
//        guest.setDownloadTime(guest.getDownloadTime() + 1);
        UploadFile fileInfo = fileService.findByShareCode(shareCode);
        return FileUtil.createResponseEntityByFileInfo(fileInfo, fileSavePath);
    }
}
