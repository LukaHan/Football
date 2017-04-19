package com.football.football.http;

import com.football.football.entity.base.HttpResult;

import rx.Subscriber;

/**
 * Created by hanshaobo on 2016/12/22.
 */

public abstract class HttpResultSubscriber<T> extends Subscriber<HttpResult<T>> {
    private T t;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        _onError(9999, e, t);
        onCompleted();
    }

    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        if (tHttpResult.error_code == 0) {
            onSuccess(tHttpResult.result, tHttpResult.reason);
        } else {
            this.t = tHttpResult.result;
            _onError(tHttpResult.error_code, new Throwable(tHttpResult.reason), tHttpResult.result);
        }
    }

    protected abstract void onSuccess(T result, String reason);

    protected abstract void _onError(int code, Throwable throwable, T result);
}
