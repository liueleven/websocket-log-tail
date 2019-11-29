package cn.eleven.log;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 页面访问
 * @date: 2019-11-29 10:33
 * @author: 十一
 */

@Controller
@RequestMapping("/wallet")
public class PageController {

    /**
     * 页面请求
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/log")
    public ModelAndView home(Model model, HttpServletRequest request) throws Exception {
        int serverPort = request.getServerPort();
        ModelAndView mv = new ModelAndView("log-view");
        mv.addObject("port",serverPort);
        return mv;
    }
}