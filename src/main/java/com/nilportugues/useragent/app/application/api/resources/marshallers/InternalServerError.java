package com.nilportugues.useragent.app.application.api.resources.marshallers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nilportugues.useragent.app.application.api.presenters.vnderror.VndError;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalServerError extends VndError {

    public InternalServerError() {
        final ArrayList<Error> errors = new ArrayList<>();
        errors.add(new Error("User Agent resource is not reachable."));

        this.setTotal(1);
        this.setEmbedded(new VndError.VndErrors(errors));
    }
}
