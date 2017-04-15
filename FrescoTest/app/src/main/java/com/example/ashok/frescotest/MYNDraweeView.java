package com.example.ashok.frescotest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;

import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by deepak.sharma .
 */

public class MYNDraweeView extends SimpleDraweeView {

  private ControllerListener<Object> mListener;

  public MYNDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
    super(context, hierarchy);
    init();
  }

  public MYNDraweeView(Context context) {
    super(context);
    init();
  }

  public MYNDraweeView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MYNDraweeView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    mListener = new BaseControllerListener<Object>() {
      @Override
      public void onSubmit(String id, Object callerContext) {
        Log.d("*****", "onSubmit");
      }
      @Override
      public void onFinalImageSet(
        String id,Object imageInfo, Animatable animatable) {
        Log.d("*****", "onFinalImageSet");
      }
      @Override
      public void onFailure(String id, Throwable throwable) {
        Log.d("*****", "onFailure");
      }
      @Override
      public void onRelease(String id) {
        Log.d("*****", "onRelease");
      }
    };
  }

  @Override
  public void onDraw(final Canvas canvas) {
    super.onDraw(canvas);
    /* May Need this in future if we need to know the status of particular image .*/
   //    mInstrumentation.onDraw(canvas);
  }

  @Override
  public void setImageURI(Uri uri, Object callerContext) {
    SimpleDraweeControllerBuilder controllerBuilder = getControllerBuilder()
        .setUri(uri)
        .setCallerContext(callerContext)
        .setOldController(getController());
    if (controllerBuilder instanceof AbstractDraweeControllerBuilder) {
      ((AbstractDraweeControllerBuilder<?,?,?,?>) controllerBuilder)
          .setControllerListener(mListener);
    }
    setController(controllerBuilder.build());
  }

  public ControllerListener<Object> getListener() {
    return mListener;
  }
}
