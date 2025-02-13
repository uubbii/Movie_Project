package com.kmove.app.comm;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ���� �޽����� ������ �̵����� ó���ϴ� Ŭ�����Դϴ�.
 * �������� Request�� ���� ��쿡�� ������ּ���.
 */
public class Err {

    // ���� �������� ��� (redirect�� �̵��� ������)
    private static final String ERROR_PAGE = "/user/error";  

    /**
     * HttpServletRequest�� ���� ���ǿ� ���� �޽����� �����ϰ� ���� �������� �����̷�Ʈ�մϴ�.
     * @param request Ŭ���̾�Ʈ ��û ��ü
     * @param msg ���� �޽���
     * @return ���� ������ ��� (�����̷�Ʈ ����)
     */
    public static String ErrPage(HttpServletRequest request, String msg) {
        request.getSession().setAttribute("msg", msg);
        return "redirect:" + ERROR_PAGE;
    }

    /**
     * Model ��ü�� ���� �޽����� �߰��ϰ� ���� �������� �̵��մϴ�.
     * @param model Spring Model ��ü
     * @param msg ���� �޽���
     * @return ���� ������ ���
     */
    public static String ErrPage(Model model, String msg) {
        model.addAttribute("msg", msg);
        return "redirect:" + ERROR_PAGE;
    }

    /**
     * RedirectAttributes�� ����Ͽ� ���� �޽����� �����ϰ� ���� �������� �����̷�Ʈ�մϴ�.
     * @param rattr �����̷�Ʈ �Ӽ��� ������ ��ü
     * @param msg ���� �޽���
     * @return ���� ������ ��� (�����̷�Ʈ ����)
     */
    public static String ErrPage(RedirectAttributes rattr, String msg) {
        rattr.addFlashAttribute("msg", msg);
        return "redirect:" + ERROR_PAGE;
    }

    /**
     * HttpServletRequest�� ���ǿ� ���� �޽����� �����մϴ�.
     * @param request Ŭ���̾�Ʈ ��û ��ü
     * @param msg ���� �޽���
     */
    public static void ErrMsg(HttpServletRequest request, String msg) {
        request.getSession().setAttribute("msg", msg);
    }

    /**
     * Model ��ü�� ���� �޽����� �߰��մϴ�.
     * @param model Spring Model ��ü
     * @param msg ���� �޽���
     */
    public static void ErrMsg(Model model, String msg) {
        model.addAttribute("msg", msg);
    }

    
}
