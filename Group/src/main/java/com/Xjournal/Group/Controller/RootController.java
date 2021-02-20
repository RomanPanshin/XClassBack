package com.Xjournal.Group.Controller;

import com.Xjournal.Group.DBGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/REgenerate")
public class RootController {
    @Autowired
    DBGenerator dbGenerator;
    @RequestMapping(method = RequestMethod.GET)
    public void swaggerUi() {
        dbGenerator.uploadVideoLesson();
        return;
    }
}
