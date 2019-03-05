// Generated code from Butter Knife. Do not modify!
package com.example.agent48.termproject.Menu;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainMenu$$ViewBinder<T extends com.example.agent48.termproject.Menu.MainMenu> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492981, "field 'loginButton'");
    target.loginButton = finder.castView(view, 2131492981, "field 'loginButton'");
    view = finder.findRequiredView(source, 2131492982, "field 'exitButton'");
    target.exitButton = finder.castView(view, 2131492982, "field 'exitButton'");
    view = finder.findRequiredView(source, 2131492979, "field 'userNameText'");
    target.userNameText = finder.castView(view, 2131492979, "field 'userNameText'");
    view = finder.findRequiredView(source, 2131492980, "field 'passWordText'");
    target.passWordText = finder.castView(view, 2131492980, "field 'passWordText'");
  }

  @Override public void unbind(T target) {
    target.loginButton = null;
    target.exitButton = null;
    target.userNameText = null;
    target.passWordText = null;
  }
}
