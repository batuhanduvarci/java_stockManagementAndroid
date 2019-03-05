// Generated code from Butter Knife. Do not modify!
package com.example.agent48.termproject.Menu;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddProduct$$ViewBinder<T extends com.example.agent48.termproject.Menu.AddProduct> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492967, "field 'addProductButton'");
    target.addProductButton = finder.castView(view, 2131492967, "field 'addProductButton'");
    view = finder.findRequiredView(source, 2131492958, "field 'productCode'");
    target.productCode = finder.castView(view, 2131492958, "field 'productCode'");
    view = finder.findRequiredView(source, 2131492959, "field 'productName'");
    target.productName = finder.castView(view, 2131492959, "field 'productName'");
    view = finder.findRequiredView(source, 2131492961, "field 'productionDate'");
    target.productionDate = finder.castView(view, 2131492961, "field 'productionDate'");
    view = finder.findRequiredView(source, 2131492963, "field 'consumeDate'");
    target.consumeDate = finder.castView(view, 2131492963, "field 'consumeDate'");
    view = finder.findRequiredView(source, 2131492964, "field 'quantity'");
    target.quantity = finder.castView(view, 2131492964, "field 'quantity'");
    view = finder.findRequiredView(source, 2131492966, "field 'price'");
    target.price = finder.castView(view, 2131492966, "field 'price'");
  }

  @Override public void unbind(T target) {
    target.addProductButton = null;
    target.productCode = null;
    target.productName = null;
    target.productionDate = null;
    target.consumeDate = null;
    target.quantity = null;
    target.price = null;
  }
}
