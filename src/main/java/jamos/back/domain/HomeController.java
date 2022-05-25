package jamos.back.domain;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HomeController {

//    @GetMapping("/")
    public String home(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null == session) {
            return "noSession";
        }

        return "hasSession";
    }


    @GetMapping("/")
    public Content jamosTest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (null == session) {
            return new Content(new String[]{"1", "2"});
        }

        return new Content(new String[]{"3", "4"});
    }

    @Data
    static class Content {
        private final String[] content;
    }
}
