package com.jiang.thinkindler.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiang on 2017/5/23.
 */

public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> compose() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    // TODO: 2017/5/23 网络出错
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}



//    @Override
//    public ObservableSource<T> apply(Observable<T> upstream) {
//        return upstream
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(@NonNull Disposable disposable) throws Exception {
//                        // TODO: 2017/5/23 网络出错
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread());
//    }