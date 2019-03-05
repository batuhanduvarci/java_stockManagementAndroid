// Generated code from Butter Knife. Do not modify!
package com.example.agent48.termproject.Menu;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DatabaseMenu$$ViewBinder<T extends com.example.agent48.termproject.Menu.DatabaseMenu> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492968, "field 'stocksButton'");
    target.stocksButton = finder.castView(view, 2131492968, "field 'stocksButton'");
    view = finder.findRequiredView(source, 2131492969, "field 'decreasingButton'");
    target.decreasingButton = finder.castView(view, 2131492969, "field 'decreasingButton'");
    view = finder.findRequiredView(source, 2131492970, "field 'requestsButton'");
    target.requestsButton = finder.castView(view, 2131492970, "field 'requestsButton'");
    view = finder.findRequiredView(source, 2131492971, "field 'ordersButton'");
    target.ordersButton = finder.castView(view, 2131492971, "field 'ordersButton'");
    view = finder.findRequiredView(source, 2131492972, "field 'logsButton'");
    target.logsButton = finder.castView(view, 2131492972, "field 'logsButton'");
  }

  @Override public void unbind(T target) {
    target.stocksButton = null;
    target.decreasingButton = null;
    target.requestsButton = null;
    target.ordersButton = null;
    target.logsButton = null;
  }
}
