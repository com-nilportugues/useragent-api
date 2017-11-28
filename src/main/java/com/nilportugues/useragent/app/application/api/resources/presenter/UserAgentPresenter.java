package com.nilportugues.useragent.app.application.api.resources.presenter;

import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetectionResult;

public interface UserAgentPresenter {
    String success(final UserAgentDetectionResult result);

    String internalServerError(Throwable t);

    String internalServerError();
}
