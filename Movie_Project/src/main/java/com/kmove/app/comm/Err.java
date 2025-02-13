package com.kmove.app.comm;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 오류 메시지와 페이지 이동만을 처리하는 클래스입니다.
 * 에러관련 Request를 보낼 경우에만 사용해주세요.
 */
public class Err {

    // 오류 페이지의 경로 (redirect로 이동할 페이지)
    private static final String ERROR_PAGE = "/user/error";  

    /**
     * HttpServletRequest를 통해 세션에 오류 메시지를 저장하고 오류 페이지로 리다이렉트합니다.
     * @param request 클라이언트 요청 객체
     * @param msg 오류 메시지
     * @return 오류 페이지 경로 (리다이렉트 포함)
     */
    public static String ErrPage(HttpServletRequest request, String msg) {
        request.getSession().setAttribute("msg", msg);
        return "redirect:" + ERROR_PAGE;
    }

    /**
     * Model 객체에 오류 메시지를 추가하고 오류 페이지로 이동합니다.
     * @param model Spring Model 객체
     * @param msg 오류 메시지
     * @return 오류 페이지 경로
     */
    public static String ErrPage(Model model, String msg) {
        model.addAttribute("msg", msg);
        return "redirect:" + ERROR_PAGE;
    }

    /**
     * RedirectAttributes를 사용하여 오류 메시지를 전달하고 오류 페이지로 리다이렉트합니다.
     * @param rattr 리다이렉트 속성을 설정할 객체
     * @param msg 오류 메시지
     * @return 오류 페이지 경로 (리다이렉트 포함)
     */
    public static String ErrPage(RedirectAttributes rattr, String msg) {
        rattr.addFlashAttribute("msg", msg);
        return "redirect:" + ERROR_PAGE;
    }

    /**
     * HttpServletRequest의 세션에 오류 메시지를 설정합니다.
     * @param request 클라이언트 요청 객체
     * @param msg 오류 메시지
     */
    public static void ErrMsg(HttpServletRequest request, String msg) {
        request.getSession().setAttribute("msg", msg);
    }

    /**
     * Model 객체에 오류 메시지를 추가합니다.
     * @param model Spring Model 객체
     * @param msg 오류 메시지
     */
    public static void ErrMsg(Model model, String msg) {
        model.addAttribute("msg", msg);
    }

    
}
