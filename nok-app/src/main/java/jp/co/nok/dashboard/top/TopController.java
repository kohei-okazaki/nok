package jp.co.nok.dashboard.top;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.nok.common.component.SessionComponent;
import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.web.view.AppView;

/**
 * TOP画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
public class TopController {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(TopController.class);
    @Autowired
    private HttpSession session;

    /**
     * ログイン後の画面を表示するためのTOP画面
     *
     * @return TOP
     */
    @RequestMapping(path = "/top", method = { RequestMethod.GET, RequestMethod.POST })
    public String top() {
        SessionComponent sessionComponent = (SessionComponent) session
                .getAttribute(SessionComponent.KEY);
        LOG.debugBean(sessionComponent);
        LOG.debugBean(sessionComponent.getLoginAuthDto());
        return AppView.TOP_VIEW.getValue();
    }
}
