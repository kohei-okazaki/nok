package jp.co.nok.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.web.view.AppView;

/**
 * アプリエラーハンドラー
 *
 * @version 1.0.0
 */
@Component
public class AppErrorHandler implements HandlerExceptionResolver {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(AppErrorHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception e) {

        ModelAndView modelView = new ModelAndView();
        // error画面を設定
        modelView.setViewName(AppView.APP_ERROR_VIEW.getValue());

        LOG.error("エラーが発生しました", e);
        return modelView;
    }

}
