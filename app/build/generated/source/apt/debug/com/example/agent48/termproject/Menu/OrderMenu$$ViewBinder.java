// Generated code from Butter Knife. Do not modify!
package com.example.agent48.termproject.Menu;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class OrderMenu$$ViewBinder<T extends com.example.agent48.termproject.Menu.OrderMenu> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493006, "field 'orderListSwipe'");
    target.orderListSwipe = finder.castView(view, 2131493006, "field 'orderListSwipe'");
    view = finder.findRequiredView(source, 2131492978, "field 'buttonRefresh'");
    target.buttonRefresh = finder.castView(view, 2131492978, "field 'buttonRefresh'");
  }

  @Override public void unbind(T target) {
    target.orderListSwipe = null;
    target.buttonRefresh = null;
  }
}
