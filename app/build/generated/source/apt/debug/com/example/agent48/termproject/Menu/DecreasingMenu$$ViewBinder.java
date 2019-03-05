// Generated code from Butter Knife. Do not modify!
package com.example.agent48.termproject.Menu;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DecreasingMenu$$ViewBinder<T extends com.example.agent48.termproject.Menu.DecreasingMenu> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492975, "field 'stockListSwipe'");
    target.stockListSwipe = finder.castView(view, 2131492975, "field 'stockListSwipe'");
    view = finder.findRequiredView(source, 2131492976, "field 'refreshTableButton'");
    target.refreshTableButton = finder.castView(view, 2131492976, "field 'refreshTableButton'");
  }

  @Override public void unbind(T target) {
    target.stockListSwipe = null;
    target.refreshTableButton = null;
  }
}
