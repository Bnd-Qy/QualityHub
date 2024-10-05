package com.quality.controller;

import com.quality.model.Response;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(tags = "文件管理")
@RequestMapping("/file")
@RestController
public class FileController {
    @PostMapping("/folder/create")
    public Response<?> createFolder() {
        return null;
    }

    @PostMapping("/folder/rename")
    public Response<?> renameFolder() {
        return null;
    }


    @DeleteMapping("/folder/delete/{folderId}")
    public Response<?> deleteFolder(@PathVariable String folderId) {
        return null;
    }

    @PostMapping("/upload")
    public Response<?> updateFile() {
        return null;
    }

    @GetMapping("/download")
    public Response<?> downloadFile() {
        return null;
    }
}
