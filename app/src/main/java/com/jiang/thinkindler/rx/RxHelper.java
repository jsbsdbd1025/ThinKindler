package com.jiang.thinkindler.rx;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jiang on 2017/4/24.
 */

public class RxHelper {

    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<ResponseBean<T>, T> handleResult() {

        return new Observable.Transformer<ResponseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<ResponseBean<T>> tObservable) {
                return tObservable.flatMap(new Func1<ResponseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(ResponseBean<T> result) {
                        if (result.isSuccess()) {
                            return createData(result.getBody());
                        } else {
                            return Observable.error(new RuntimeException(result.getMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }



    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
//                    Gson gson = new Gson();
//                    T data = gson.fromJson(object, new TypeToken<T>() {
//                            }.getType());
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }
}
